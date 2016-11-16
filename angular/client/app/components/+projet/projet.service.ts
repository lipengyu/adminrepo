import {Injectable} from "@angular/core";
import {HttpService} from "shared";

@Injectable()
export class ProjetService {

    http: HttpService;

    constructor(http: HttpService) {
        this.http = http;
    }

    getProjects() {
        return this.http.get("projects");
    }

    getInformations(project) {
        let repo = project.urlGithub;
        repo = repo.replace("github.com", "api.github.com/repos");
        return this.http.getSimpleHttp().get(repo).map(res => res.json());
    }

    getOwnerInformations(informations) {
        return this.http.getSimpleHttp().get(informations.owner.url).map(res => res.json());
    }

    getPackageJson(project) {
        return this.http.get("projects/package_json", {label : project.label});
    }

    executeScript(project, script) {
        return this.http.post("projects/execute_script", {project: project, script: script});
    }

}
