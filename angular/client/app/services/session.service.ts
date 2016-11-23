import {Injectable} from "@angular/core";
import {HttpService} from "shared";

interface UserSessionOption {
    connected: boolean;
    username?: string;
    roles?: string[];
}

@Injectable()
export class SessionService {

    private http: HttpService;
    public user: UserSessionOption = {connected: false};

    constructor(http: HttpService) {
        this.http = http;
    }

    connect(data, token) {
        data.connected = true;
        this.user = Object.assign(this.user, data);
        if (token) {
            localStorage.setItem("token", token);
        }
    }

    disconnect() {
        this.user = {connected: false};
        localStorage.removeItem("token");
    }

    logout() {
        this.http.post("/api/security/logout").subscribe(res => {
            console.log(res);
            this.disconnect();
        })
    }

}
