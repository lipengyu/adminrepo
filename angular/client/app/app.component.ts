import {Component} from "@angular/core";
import "./app.component.scss";

export interface MenuItem {
    route?: string[];
    label: string;
    subMenu?: MenuItem[];
}

@Component({
    selector: "app",
    templateUrl: "./app.template.html"
})
export class AppComponent {

    env = process.env;
    main_menu: MenuItem[] = [];

    constructor() {
        this.constructMainMenu();
    }

    constructMainMenu() {
        this.main_menu = [
            {
                label: "Commun", subMenu: [
                {
                    route: ["/common/home"], label: "Accueil"
                },
                {
                    route: ["/common/profile"], label: "Profil"
                }
            ]
            }, {
                label: "Projet", subMenu: [
                    {
                        route: ["/projet/index"], label: "Liste",
                    },
                    {
                        route: ["/projet/creer"], label: "Créer"
                    }
                ]
            }, {
                label: "Sécurité", subMenu: [
                    {
                        route: ["/security/login"], label: "Connexion"
                    }
                ]
            }, {
                label: "Référentiels", subMenu: [
                    {
                        route: ["/referentiel/user"], label: "Utilisateurs"
                    }
                ]
            }, {
                label: "Administration", subMenu: [
                    {
                        route: ["/administration/configuration"], label: "Configuration"
                    }
                ]
            }
        ]
    }

}