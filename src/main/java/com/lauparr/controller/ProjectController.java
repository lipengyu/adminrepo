package com.lauparr.controller;

import com.google.common.collect.Maps;
import com.lauparr.dto.output.CollectingLogOutputStream;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.PumpStreamHandler;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.QueryParam;
import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * Created by lauparr on 14/11/2016.
 */
@RequestMapping("/api/projects")
@RestController
public class ProjectController {

    @Autowired
    private Environment env;

    @RequestMapping("/package_json")
    public String getProjectPackageJson(@QueryParam(value = "label") String label) throws Exception {
        String folder = env.getProperty("projects.directory");
        File packageJson = new File(FilenameUtils.normalize(folder + "/" + label + "/package.json"));
        if (packageJson.exists()) {
            return FileUtils.readFileToString(packageJson, "UTF-8");
        }
        return null;
    }

    @RequestMapping(value = "/execute_script", method = RequestMethod.POST)
    public ResponseEntity executeScript(@RequestBody Map body) throws IOException {
        Map project = (Map) body.get("project");
        String command = "npm run " + body.get("script");
        File directory = new File(FilenameUtils.normalize(env.getProperty("projects.directory") + "/" + project.get("label")));
        DefaultExecutor executor = new DefaultExecutor();

        CollectingLogOutputStream stdout = new CollectingLogOutputStream();
        PumpStreamHandler psh = new PumpStreamHandler(stdout);
        executor.setStreamHandler(psh);

        executor.setWorkingDirectory(directory);
        executor.execute(CommandLine.parse(command));

        Map result = Maps.newHashMap();
        result.put("stdout", stdout.getLines());
        return ResponseEntity.ok(result);
    }

}
