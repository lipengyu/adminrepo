import {NgModule, CUSTOM_ELEMENTS_SCHEMA} from "@angular/core";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {CommonModule} from "@angular/common";
import {RouterModule} from "@angular/router";
import {MyUiModule} from "my-ui";
import {CommonHomeComponent} from "./home/home.component";
import {CommonProfileComponent} from "./profile/profile.component";
import {CommonComponent} from "./common.component";
import {SharedModule} from "../../../shared/shared.module";
import {AppComponentSharedModule} from "../shared/app-component-shared.module";

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
        AppComponentSharedModule,
        RouterModule.forChild(routes)
    ],
    exports: [
        ...components
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AppCommonModule {
}