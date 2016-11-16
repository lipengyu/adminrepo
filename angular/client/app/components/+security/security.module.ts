import {NgModule} from "@angular/core";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {CommonModule} from "@angular/common";
import {RouterModule} from "@angular/router";
import {MyUiModule} from "my-ui";
import {SecurityLoginComponent} from "./login/login.component";
import {SecurityComponent} from "./security.component";
import {SharedModule} from "../../../shared/shared.module";

const routes = [
    {
        path: 'security', component: SecurityComponent,
        children: [
            {path: '', redirectTo: 'login'},
            {path: 'login', component: SecurityLoginComponent},
        ]
    }
];

const components = [
    SecurityComponent,
    SecurityLoginComponent
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
export class AppSecurityModule {
}