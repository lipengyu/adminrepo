import {Component} from "@angular/core";
import {ProjetService} from "./projet.service";

@Component({
    selector: 'projet',
    templateUrl: "./projet.template.html",
    providers: [ProjetService]
})
export class ProjetComponent {

    projetService: ProjetService;
    projectList: any[] = [];
    projectSelected: any;
    scriptSelected: any;
    socket: WebSocket;
    loading: boolean = false;
    output: string[] = [];

    constructor(projetService: ProjetService) {
        this.projetService = projetService;
        this.getProjects();
        // this.socket = new WebSocket(`${process.env.websocket_api}/project_log`);
        // this.socket.onmessage = (e) => {
        //     this.output.push(e.data);
        // }
    }

    getProjects() {
        this.projetService.getProjects().subscribe(res => {
            this.projectList = res._embedded.projects;
        })
    }

    onProjectSelected(event) {
        this.loading = true;
        this.projectSelected = this.projectList[event.value];
        this.projetService.getInformations(this.projectSelected).subscribe(informations => {
            this.projetService.getOwnerInformations(informations).subscribe(owner => {
                this.projectSelected.informations = informations;
                this.projectSelected.owner = owner;
                this.projetService.getPackageJson(this.projectSelected).subscribe(packageJson => {
                    if (packageJson.scripts) {
                        this.projectSelected.scripts = Object.keys(packageJson.scripts);
                        this.loading = false;
                    }
                });
            })
        });
    }

    executeScript() {
        this.loading = true;
        this.projetService.executeScript(this.projectSelected, this.scriptSelected.text).subscribe(res => {
            this.output = res.stdout;
            this.loading = false;
        })
    }

}
