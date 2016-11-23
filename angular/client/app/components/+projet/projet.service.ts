import {Injectable} from "@angular/core";
import {HttpService} from "shared";

@Injectable()
export class ProjetService {

    http: HttpService;

    constructor(http: HttpService) {
        this.http = http;
    }

    getRepos() {
        return this.http.get("/api/projects/repo/list");
    }

    getProjects(repo: string) {
        return this.http.get("/api/projects/list", {repo: repo});
    }

    getPackageJson(selections) {
        return this.http.post("/api/projects/package_json", {repo: selections.repository, project: selections.project.name});
    }

    getInformations(selections) {
        let url = `http://api.github.com/repos/${selections.repository}/${selections.project.name}`;
        return this.http.http.get(url).map(res => res.json());
    }

    getOwnerInformations(informations) {
        return this.http.http.get(informations.owner.url).map(res => res.json());
    }

    executeScript(selections, script) {
        return this.http.post("/api/projects/execute_script", {repo: selections.repository, project: selections.project.name, script: script});
    }

}
