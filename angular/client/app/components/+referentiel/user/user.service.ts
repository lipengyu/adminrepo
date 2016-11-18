import {Injectable} from "@angular/core";
import {Http} from "@angular/http";

@Injectable()
export class ReferentielUserService {

    http: Http;

    constructor(http: Http) {
        this.http = http;
    }

    getUsers() {
        return this.http.get("/api/users").map(res => res.json());
    }

}
