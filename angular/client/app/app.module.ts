import {NgModule, CUSTOM_ELEMENTS_SCHEMA} from "@angular/core";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {HttpModule} from "@angular/http";
import {BrowserModule} from "@angular/platform-browser";
import {AppCommonModule} from "./components/+common/common.module";
import {AppComponent} from "./app.component";
import {AppRouting} from "./app.routing";
import {StartupServiceProvider} from "./services/startup.service";
import {SharedModule} from "../shared/shared.module";
import {AppSecurityModule} from "./components/+security/security.module";
import {AppAdministrationModule} from "./components/+administration/administration.module";
import {AppReferentielModule} from "./components/+referentiel/referentiel.module";
import {AppProjetModule} from "./components/+projet/projet.module";
import {SessionService} from "./services/session.service";
import {AppComponentSharedModule} from "./components/shared/app-component-shared.module";

@NgModule({
    imports: [
        BrowserModule,
        ReactiveFormsModule,
        FormsModule,
        HttpModule,
        AppRouting,
        SharedModule,
        AppCommonModule,
        AppProjetModule,
        AppSecurityModule,
        AppAdministrationModule,
        AppReferentielModule,
        AppComponentSharedModule
    ],
    declarations: [
        AppComponent
    ],
    providers: [
        SessionService,
        StartupServiceProvider
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA],
    bootstrap: [AppComponent]
})
export class AppModule {

    constructor() {
    }

}