import {NgModule, APP_INITIALIZER} from "@angular/core";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {HttpModule} from "@angular/http";
import {BrowserModule} from "@angular/platform-browser";
import {SharedComponentsModule} from "./components/shared-components.module";
import {SharedServicesModule} from "./services/shared-services.module";

@NgModule({
    imports: [
        BrowserModule,
        ReactiveFormsModule,
        FormsModule,
        HttpModule,
        SharedComponentsModule,
        SharedServicesModule
    ],
    exports: [
        SharedComponentsModule,
        SharedServicesModule
    ],
    providers: [
        {
            provide: APP_INITIALIZER,
            useFactory: () => () => {
                // return configuration.init();
                return;
            },
            deps: [],
            multi: true
        }
    ]
})
export class SharedModule {

    constructor() {
    }

}