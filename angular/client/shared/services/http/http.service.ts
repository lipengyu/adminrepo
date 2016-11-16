import {Injectable} from "@angular/core";
import {Http, Headers, RequestMethod, URLSearchParams, Request, RequestOptions} from "@angular/http";
import {Observable} from "rxjs";
import {StorageService} from "../storage/storage.service";

@Injectable()
export class HttpService {

    http: Http;
    storage: StorageService;
    restful_api: string;

    constructor(http: Http, storage: StorageService) {
        this.http = http;
        this.storage = storage;
        this.restful_api = process.env.restful_api;
    }

    getSimpleHttp(): Http {
        return this.http;
    }

    /**
     * Retourne la méthode GET de la route
     * @param url Url de la route
     * @param params Paramètres a envoyer a la requete
     * @returns {Observable<Response>} Réponse de la méthode GET de la route
     */
    get(url: string, params?: Object): Observable<any> {
        // Récupération du token dans le localstorage
        let token = this.storage.getItem("token");

        // Entête HTTP pour dire qu'on va lui envoyer du json
        let httpHeaders = new Headers({'Content-Type': 'application/json'});

        if (token) {
            // Ajout du token en entête
            httpHeaders.append("Authorization", token);
        }

        // Création des options de la requête
        let options = new RequestOptions({
            method: RequestMethod.Get,
            url: this.restful_api + url,
            headers: httpHeaders
        });

        // Création des paramètres d'url à envoyer
        if (params) {
            options.search = new URLSearchParams();
            Object.keys(params).forEach((key) => {
                options.search.set(key, params[key]);
            });
        }

        // Création de la requête
        let request = new Request(options);

        // Envoi de la requête
        return this.http.request(request).map(res => res.json()).catch(err => {
            return Observable.throw(err || 'Une erreur technique est survenue');
        });
    }

    /**
     * Retourne la méthode POST de la route
     * @param url Url de la route
     * @param body Corps de la requête avec les paramètres a envoyer au format JSON
     * @returns {Observable<Response>} Réponse de la méthode POST de la route
     */
    post(url: string, body: any): Observable<any> {
        // Récupération du token dans le localstorage
        let token = this.storage.getItem("token");

        // Entête HTTP pour dire qu'on va lui envoyer du json
        let httpHeaders = new Headers({'Content-Type': 'application/json'});

        // Création du corps de la requête
        let httpBody = JSON.stringify(body);

        if (token) {
            // Ajout du token en entête
            httpHeaders.append("Authorization", token);
        }

        // Création des options de la requête
        let options = new RequestOptions({
            method: RequestMethod.Post,
            url: this.restful_api + url,
            headers: httpHeaders,
            body: httpBody
        });

        // Création de la requête
        let request = new Request(options);

        // Envoi de la requête
        return this.http.request(request).map((res:any) => {
            try {
                return res.json();
            } catch (e) {
                return res;
            }
        }).catch(err => {
            return Observable.throw(err || 'Une erreur technique est survenue');
        });
    }

}
