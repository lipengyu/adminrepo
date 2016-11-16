import {NgModule} from "@angular/core";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {CommonModule} from "@angular/common";
import {RouterModule} from "@angular/router";
import {MyUiModule} from "my-ui";
import {ProjetCreerComponent} from "./creer/projet-creer.component";
import {ProjetComponent} from "./projet.component";
import {SharedModule} from "../../../shared/shared.module";

const routes = [
    {path: 'projet', redirectTo: 'projet/index', pathMatch: 'full'},
    {path: 'projet/index', component: ProjetComponent},
    {path: 'projet/creer', component: ProjetCreerComponent}
];

const components = [
    ProjetComponent,
    ProjetCreerComponent
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
export class AppProjetModule {
}