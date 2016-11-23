import {Component} from "@angular/core";
import {ProjetCreerService} from "./projet-creer.service";
import {SessionService, SockClient} from "shared";

@Component({
    selector: "referentiel-user",
    templateUrl: "./projet-creer.template.html",
    providers: [ProjetCreerService]
})
export class ProjetCreerComponent {

    projetCreerService: ProjetCreerService;
    sessionService: SessionService;
    informations: any;
    repository: string;
    sockClient: SockClient;
    projects: any[] = [];
    loading: boolean = false;
    output: string[] = [];

    constructor(projetCreerService: ProjetCreerService, sessionService: SessionService) {
        this.projetCreerService = projetCreerService;
        this.sessionService = sessionService;
        this.sockClient = new SockClient("/ws/project", "/topic/project/import", (message) => {
            if (message && this.output) {
                this.output.push(message.body);
            }
        });
    }

    ngOnDestroy() {
        this.sockClient.disconnect();
    }

    importerProjet(project: any) {
        project.owner = this.repository;
        this.projetCreerService.importerProjet(project).subscribe(res => {
        })
    }

    onGetInformations(repository: string) {
        this.repository = repository;
        this.projetCreerService.getRepoInformations(repository).map(res => res.json()).subscribe((res: any) => {
            this.projects = res.map((project: any) => {
                return {
                    id: project.id,
                    name: project.name,
                    description: project.description,
                    language: project.language,
                    url: project.svn_url
                }
            });
        });
    }

}