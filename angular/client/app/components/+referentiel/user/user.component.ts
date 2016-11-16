import {Component} from "@angular/core";
import {ReferentielUserService} from "./user.service";

@Component({
    selector: "referentiel-user",
    templateUrl: "./user.template.html",
    providers: [ReferentielUserService]
})
export class ReferentielUserComponent {

    userService: ReferentielUserService;
    users = [];

    constructor(userService: ReferentielUserService) {
        this.userService = userService;
    }

    ngOnInit() {
        this.userService.getUsers().subscribe(res => {
            if (res.success) {
                this.users = res.data;
            }
        })
    }

}