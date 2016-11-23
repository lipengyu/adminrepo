import {Injectable, APP_INITIALIZER} from "@angular/core";
import {HttpService} from "../../shared/services/http/http.service";
import {AlertService} from "../../shared/services/alert/alert.service";
import {SessionService} from "./session.service";

@Injectable()
class StartupService {

    private http: HttpService;
    private alert: AlertService;
    private session: SessionService;

    constructor(http: HttpService, alert: AlertService, session: SessionService) {
        this.http = http;
        this.alert = alert;
        this.session = session;
    }

    load(): Promise<any> {
        return new Promise(resolve => {
            this.http.callbackError = (err) => {
                this.alert.alert({title: "Oops, une erreur est survenue", text: err.error, type: "error"})
            };
            this.http.post("/api/security/already_connected").subscribe(res => {
                if (res.success) {
                    this.session.connect(res.data.user, null);
                } else {
                    this.session.disconnect();
                }
                resolve(true);
            });
        });
    }
}

export const StartupServiceProvider = [
    StartupService,
    SessionService,
    {
        provide: APP_INITIALIZER,
        useFactory: (startup: StartupService) => () => startup.load(),
        deps: [StartupService, HttpService, AlertService, SessionService],
        multi: true
    }
];