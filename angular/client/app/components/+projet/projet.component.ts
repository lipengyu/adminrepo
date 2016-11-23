import {Component} from "@angular/core";
import {ProjetService} from "./projet.service";
import {SockClient, AlertService} from "shared";

@Component({
    selector: 'projet',
    templateUrl: "./projet.template.html",
    providers: [ProjetService]
})
export class ProjetComponent {

    projetService: ProjetService;
    alert: AlertService;
    lists: any = {};
    selections: any = {};
    sockClient: SockClient;
    loading: boolean = false;
    output: string[] = [];

    constructor(projetService: ProjetService, alert: AlertService) {
        this.projetService = projetService;
        this.alert = alert;
        this.getRepos();
        this.sockClient = new SockClient("/ws/project", "/topic/project/log", (message) => {
            if (message && this.output) {
                this.output.push(message.body);
            }
        });
    }

    ngOnDestroy() {
        // this.sockClient.disconnect();
    }

    getRepos() {
        this.projetService.getRepos().subscribe(res => {
            this.lists.repository = res.data;
        })
    }

    getProjects(event) {
        this.selections.repository = event.text;
        this.projetService.getProjects(this.selections.repository).subscribe(res => {
            this.lists.project = res.data;
        })
    }

    onProjectSelected(event) {
        this.loading = true;
        this.selections.project = {name: event.text};
        this.projetService.getInformations(this.selections).subscribe(informations => {
            this.projetService.getOwnerInformations(informations).subscribe(owner => {
                this.selections.project.informations = informations;
                this.selections.project.owner = owner;
                this.projetService.getPackageJson(this.selections).subscribe(pkg => {
                    if (pkg.success) {
                        let packageJson = JSON.parse(pkg.data);
                        if (packageJson.scripts) {
                            this.selections.project.scripts = Object.keys(packageJson.scripts);
                            this.loading = false;
                        }
                    }
                });
            })
        });
    }

    executeScript() {
        this.loading = true;
        this.output = [];
        this.projetService.executeScript(this.selections, this.selections.script.text).subscribe(res => {
            this.alert.alert({title: "Script : Successful", text: `Execution successful for script ${this.selections.script.text}`});
            this.loading = false;
        })
    }

}
