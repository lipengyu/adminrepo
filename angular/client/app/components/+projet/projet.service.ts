import {Injectable} from "@angular/core";
import {HttpService} from "shared";

@Injectable()
export class ProjetService {

    http: HttpService;

    constructor(http: HttpService) {
        this.http = http;
    }

    getProjects() {
        return this.http.get("/api/projects");
    }

    getInformations(project) {
        let repo = project.urlGithub;
        repo = repo.replace("github.com", "api.github.com/repos");
        return this.http.http.get(repo).map(res => res.json());
    }

    getOwnerInformations(informations) {
        return this.http.http.get(informations.owner.url).map(res => res.json());
    }

    getPackageJson(project) {
        return this.http.get("/apiprojects/package_json", {label: project.label});
    }

    executeScript(project, script) {
        return this.http.post("/api/projects/execute_script", {project: project, script: script});
    }

}
