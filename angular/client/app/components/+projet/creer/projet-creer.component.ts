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
    informations: any;
    loading: boolean = false;

    constructor(projetCreerService: ProjetCreerService, sessionService: SessionService) {
        this.projetCreerService = projetCreerService;
        this.sessionService = sessionService;
    }

    onGetInformations(url: string) {
        this.projetCreerService.getInformations(url).toPromise().then(res => {
            console.log(res);
        });
    }

}