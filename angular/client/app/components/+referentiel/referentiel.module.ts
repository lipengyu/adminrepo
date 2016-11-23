import {NgModule, CUSTOM_ELEMENTS_SCHEMA} from "@angular/core";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {CommonModule} from "@angular/common";
import {RouterModule} from "@angular/router";
import {MyUiModule} from "my-ui";
import {ReferentielUserComponent} from "./user/user.component";
import {ReferentielComponent} from "./referentiel.component";
import {SharedModule} from "../../../shared/shared.module";
import {AppComponentSharedModule} from "../shared/app-component-shared.module";

const routes = [
    {
        path: 'referentiel', component: ReferentielComponent,
        children: [
            {path: '', redirectTo: 'user'},
            {path: 'user', component: ReferentielUserComponent},
        ]
    }
];

const components = [
    ReferentielComponent,
    ReferentielUserComponent
];

@NgModule({
    declarations: [
        ...components
    ],
    imports: [
        CommonModule,
        FormsModule,
        ReactiveFormsModule,
        SharedModule,
        AppComponentSharedModule,
        RouterModule.forChild(routes)
    ],
    exports: [
        ...components
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AppReferentielModule {
}