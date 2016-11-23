import {Component} from "@angular/core";
import {Validators, FormControl, FormBuilder, FormGroup} from "@angular/forms";
import {ValidationUtils, HttpService, AlertService, StorageService} from "shared";
import {SessionService} from "../../../services/session.service";

@Component({
    selector: "security-login",
    templateUrl: "./login.template.html"
})
export class SecurityLoginComponent {

    http: HttpService;
    alert: AlertService;
    storage: StorageService;
    session: SessionService;
    loginForm: FormGroup;

    constructor(http: HttpService, builder: FormBuilder, alert: AlertService, storage: StorageService, session: SessionService) {
        this.http = http;
        this.alert = alert;
        this.storage = storage;
        this.session = session;
        this.loginForm = builder.group({
            username: new FormControl('laurent.parrot@gmail.com', [Validators.required, ValidationUtils.emailValidator]),
            password: new FormControl('123', Validators.required)
        })
    }

    onSubmitFormLogin() {
        if (this.loginForm.valid) {
            let loginInfo = {
                username: this.loginForm.controls['username'].value,
                password: this.loginForm.controls['password'].value,
            };
            this.http.post("/api/security/authenticate", loginInfo).subscribe(res => {
                if (res.success) {
                    this.storage.setItem("token", res.data.token);
                    this.session.connect(res.data.user, res.data.token);
                    this.alert.alert({title: "Connection : Successful", text: `Vous êtes maintenant connecté avec le compte ${res.data.user.username}`, type: "success"});
                } else {
                    this.alert.alert({title: "Connection : Failed", text: res.error.message, type: "error"});
                }
            })
        }
    }

}