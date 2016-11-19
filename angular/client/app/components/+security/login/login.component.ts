import {Component} from "@angular/core";
import {HttpService} from "../../../../shared/services/http/http.service";
import {Validators, FormControl, FormBuilder, FormGroup} from "@angular/forms";
import {ValidationUtils} from "../../../../shared/components/validation/validation.service";
import {AlertService} from "../../../../shared/services/alert/alert.service";

@Component({
    selector: "security-login",
    templateUrl: "./login.template.html"
})
export class SecurityLoginComponent {

    http: HttpService;
    alert: AlertService;
    loginForm: FormGroup;

    constructor(http: HttpService, builder: FormBuilder, alert: AlertService) {
        this.http = http;
        this.alert = alert;
        this.loginForm = builder.group({
            login: new FormControl('', [Validators.required, ValidationUtils.emailValidator]),
            password: new FormControl('', Validators.required)
        })
    }

    onSubmitFormLogin() {
        if (this.loginForm.valid) {
            let loginInfo = {
                login: this.loginForm.controls['login'].value,
                password: this.loginForm.controls['password'].value,
            };
            this.http.post("/api/security/login", loginInfo).subscribe(res => {
                if (res.success) {
                    this.alert.alert({title: "Connection : Successful", type: "success"});
                } else {
                    this.alert.alert({title: "Connection : Failed", text: res.error.message, type: "error"});
                }
            })
        }
    }

}