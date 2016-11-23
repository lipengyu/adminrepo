package com.lauparr.app.controller;

import com.lauparr.core.annotation.Restful;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.filefilter.FileFilterUtils;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by lauparr on 14/11/2016.
 */
@RequestMapping("/api/projects")
@RestController
public class ProjectController {

    @Autowired
    private Environment env;

    @Autowired
    private SimpMessagingTemplate template;

    @Restful
    @Secured("ROLE_UTILISATEUR")
    @RequestMapping("/repo/list")
    public Object getRepoList() {
        File folder = new File(env.getProperty("projects.directory"));
        return Arrays.stream(folder.listFiles((FileFilter) FileFilterUtils.directoryFileFilter())).map(File::getName).collect(Collectors.toList());
    }

    @Restful
    @RequestMapping("list")
    public Object getProjectList(@RequestParam("repo") String repo) {
        File folder = new File(FilenameUtils.normalize(env.getProperty("projects.directory") + "/" + repo));
        return Arrays.stream(folder.listFiles((FileFilter) FileFilterUtils.directoryFileFilter())).map(File::getName).collect(Collectors.toList());
    }

    @Restful
    @RequestMapping(value = "package_json", method = RequestMethod.POST)
    public Object getProjectPackageJson(@RequestBody Map body) throws Exception {
        File folder = new File(FilenameUtils.normalize(String.format("%s/%s/%s", env.getProperty("projects.directory"), body.get("repo"), body.get("project"))));
        File packageJson = new File(FilenameUtils.normalize(String.format("%s/%s", folder.getAbsolutePath(), "package.json")));
        if (packageJson.exists()) {
            return FileUtils.readFileToString(packageJson, "UTF-8");
        }
        return null;
    }

    @Restful
    @RequestMapping(value = "/execute_script", method = RequestMethod.POST)
    public Object executeScript(@RequestBody Map body) throws IOException {
        String command = "npm run " + body.get("script");
        File directory = new File(FilenameUtils.normalize(String.format("%s/%s/%s", env.getProperty("projects.directory"), body.get("repo"), body.get("project"))));
        executeCommand("npm run " + body.get("script"), directory, object -> {
            template.convertAndSend("/topic/project/log", object);
            return object;
        });
        return null;
    }

    @Restful
    @RequestMapping(value = "/import", method = RequestMethod.POST)
    public Object importProject(@RequestBody Map body) throws GitAPIException {
        File rootFolder = new File(env.getProperty("projects.directory"));
        File projectFolder = new File(FilenameUtils.normalize(String.format("%s/%s/%s", env.getProperty("projects.directory"), body.get("owner"), body.get("name"))));
        if (rootFolder.exists()) {
            if (projectFolder.exists()) {
                projectFolder.setWritable(true);
                if (projectFolder.delete()) {
                    System.out.println("Dossier " + projectFolder.getAbsolutePath() + " supprimé.");
                }
            }
            template.convertAndSend("/topic/project/import", "Clonage du dépôt " + body.get("name"));
            Git git = Git.cloneRepository()
                    .setURI(body.get("url") + ".git")
                    .setDirectory(projectFolder)
                    .call();

            if (new File(String.format("%s/%s", projectFolder.getAbsolutePath(), "package.json")).exists()) {
                template.convertAndSend("/topic/project/import", "Installation des packages");
                executeCommand("npm install", projectFolder, object -> {
                    template.convertAndSend("/topic/project/import", object);
                    return object;
                });
            }

            template.convertAndSend("/topic/project/import", "---");
        }
        return null;
    }

    private Process executeCommand(String command, File dir, Function callback) {
        try {

            Process proc = Runtime.getRuntime().exec(command, null, dir);
            InputStream inputStream = proc.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                callback.apply(line);
            }
            if (true) {
                proc.waitFor();
            }
            return proc;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
