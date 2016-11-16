import {NgModule} from "@angular/core";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {CommonModule} from "@angular/common";
import {RouterModule} from "@angular/router";
import {MyUiModule} from "my-ui";
import {CommonHomeComponent} from "./home/home.component";
import {CommonProfileComponent} from "./profile/profile.component";
import {CommonComponent} from "./common.component";
import {SharedModule} from "../../../shared/shared.module";

const routes = [
    {
        path: 'common', component: CommonComponent,
        children: [
            {path: '', redirectTo: 'home'},
            {path: 'home', component: CommonHomeComponent},
            {path: 'profile', component: CommonProfileComponent}
        ]
    }
];

const components = [
    CommonComponent,
    CommonHomeComponent,
    CommonProfileComponent
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
export class AppCommonModule {
}