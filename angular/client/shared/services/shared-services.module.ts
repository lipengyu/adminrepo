import {NgModule} from "@angular/core";
import {HttpModule} from "@angular/http";
import {SessionService} from "./session/session.service";
import {StorageService} from "./storage/storage.service";
import {HttpService} from "./http/http.service";
import {AlertService} from "./alert/alert.service";

export const services = [
    HttpService,
    SessionService,
    StorageService,
    AlertService

];

@NgModule({
    imports: [
        HttpModule
    ],
    providers: [...services]
})
export class SharedServicesModule {
}

export * from "./session/session.service";
export * from "./storage/storage.service";
export * from "./http/http.service";
export * from "./alert/alert.service";