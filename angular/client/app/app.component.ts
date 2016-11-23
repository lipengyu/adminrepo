import {Component} from "@angular/core";
import "./app.component.scss";
import {AlertService} from "shared";
import {HttpService} from "../shared/services/http/http.service";
import {StorageService} from "../shared/services/storage/storage.service";
import {SessionService} from "./services/session.service";

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

    private alert: AlertService;
    private http: HttpService;
    private storage: StorageService;
    public session: SessionService;
    main_menu: MenuItem[] = [];

    constructor(alert: AlertService, http: HttpService, storage: StorageService, session: SessionService) {
        this.alert = alert;
        this.http = http;
        this.storage = storage;
        this.session = session;
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
                label: "Sécurité", subMenu: [
                    {
                        route: ["/security/login"], label: "Connexion"
                    }
                ]
            }, {
                label: "Projet", subMenu: [
                    {
                        route: ["/projet/index"], label: "Liste",
                    },
                    {
                        route: ["/projet/creer"], label: "Import"
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

    confirmLogout() {
        this.alert.alert({
            title: 'Logout: Confirm',
            text: 'Etes-vous sûr de vouloir vous deconnecter ?',
            showCancelButton: true,
            cancelButtonText: "Annuler",
            confirmButtonText: "Déconnecter",
            closeOnConfirm: false
        }, (confirm) => {
            if (confirm) {
                this.http.post("/api/security/logout").subscribe(res => {
                    if (res.success) {
                        this.session.disconnect();
                        this.storage.removeItem('token');
                        this.alert.alert({title: "Disconnection : Successful", text: `Vous êtes maintenant déconnecté`, type: "success"});
                    } else {
                        this.alert.alert({title: "Disconnection : Failed", text: res.error.message, type: "error"});
                    }
                })
            }
        })
    }

}