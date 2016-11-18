import {Component} from "@angular/core";
import {HttpService} from "../../../../shared/services/http/http.service";

@Component({
    selector: "security-login",
    templateUrl: "./login.template.html"
})
export class SecurityLoginComponent {

    http: HttpService;

    constructor(http: HttpService) {
        this.http = http;
    }

    onSubmitFormLogin(event) {
        if (event.valid) {
            this.http.post("/api/security/login", event.values).subscribe(res => {
                if (res.success) {
                    console.log(res.data);
                } else {
                    console.log(res.error.message);
                }
            })
        }
    }

    validators = {
        fields: {
            login: 'empty',
            password: 'empty'
        }
    }

}