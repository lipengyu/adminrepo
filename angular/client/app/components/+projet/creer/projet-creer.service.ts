import {Injectable} from "@angular/core";
import {Observable} from "rxjs";
import {HttpService} from "shared";

@Injectable()
export class ProjetCreerService {

    http: HttpService;

    constructor(http: HttpService) {
        this.http = http;
    }

    importerProjet(project: any): Observable<any> {
        return this.http.post("/api/projects/import", project);
    }

    getRepoInformations(repo: string) {
        return Observable.fromPromise(this.http.getSimpleHttp().get(`http://api.github.com/users/${repo}/repos`).toPromise());
    }
}
