import {Injectable} from "@angular/core";
import {Http} from "@angular/http";
import {Observable} from "rxjs";

@Injectable()
export class ProjetCreerService {

    http: Http;

    constructor(http: Http) {
        this.http = http;
    }

    creerProjet(project: any): Observable<any> {
        return this.http.post(process.env.restful_api + "projects/with_filesystem", project).map(res => res.json());
    }

}
