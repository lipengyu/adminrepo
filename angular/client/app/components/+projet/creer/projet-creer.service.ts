import {Injectable} from "@angular/core";
import {Observable} from "rxjs";
import {HttpService} from "shared";

@Injectable()
export class ProjetCreerService {

    http: HttpService;

    constructor(http: HttpService) {
        this.http = http;
    }

    creerProjet(project: any): Observable<any> {
        return this.http.post("/api/projects/with_filesystem", project).map(res => res.json());
    }

    getInformations(url: string) {
        url = url.replace("github.com", "api.github.com/repos");
        return this.http.http.get(url).map(res => res.json());
    }
}
