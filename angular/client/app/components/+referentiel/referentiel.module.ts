import {NgModule} from "@angular/core";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {CommonModule} from "@angular/common";
import {RouterModule} from "@angular/router";
import {MyUiModule} from "my-ui";
import {ReferentielUserComponent} from "./user/user.component";
import {ReferentielComponent} from "./referentiel.component";
import {SharedModule} from "../../../shared/shared.module";

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
        RouterModule.forChild(routes)
    ],
    exports: [
        ...components
    ]
})
export class AppReferentielModule {
}