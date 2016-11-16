import {NgModule} from "@angular/core";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {CommonModule} from "@angular/common";
import {RouterModule} from "@angular/router";
import {MyUiModule} from "my-ui";
import {AdministrationConfigurationComponent} from "./configuration/configuration.component";
import {AdministrationComponent} from "./administration.component";
import {SharedModule} from "../../../shared/shared.module";

const routes = [
    {
        path: 'administration', component: AdministrationComponent,
        children: [
            {path: '', redirectTo: 'configuration'},
            {path: 'configuration', component: AdministrationConfigurationComponent},
        ]
    }
];

const components = [
    AdministrationComponent,
    AdministrationConfigurationComponent
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
export class AppAdministrationModule {
}