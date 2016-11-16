import {Component} from "@angular/core";
import {ProjetCreerService} from "./projet-creer.service";
import {SessionService} from "../../../../shared/services/session/session.service";

@Component({
    selector: "referentiel-user",
    templateUrl: "./projet-creer.template.html",
    providers: [ProjetCreerService]
})
export class ProjetCreerComponent {

    projetCreerService: ProjetCreerService;
    sessionService: SessionService;
    users = [];
    loading: boolean = false;

    constructor(projetCreerService: ProjetCreerService, sessionService: SessionService) {
        this.projetCreerService = projetCreerService;
        this.sessionService = sessionService;
    }

    submit(event) {
        try {
            if (event.valid) {
                this.loading = true;
                this.projetCreerService.creerProjet(event.values).toPromise().then((res: any) => {
                    this.loading = false;
                })
            }
        } catch (e) {
            console.log(e);
        }
    }

    validation = {
        fields: {
            url_github: 'empty'
        }
    }

}