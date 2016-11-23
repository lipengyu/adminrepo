import {ReactiveFormsModule, FormsModule} from "@angular/forms";
import {NgModule, CUSTOM_ELEMENTS_SCHEMA} from "@angular/core";
import {AppTitleComponent} from "./title/app-title.component";
import {CommonModule} from "@angular/common";

export const components = [
    AppTitleComponent
];

@NgModule({
    imports: [CommonModule, ReactiveFormsModule, FormsModule],
    declarations: [...components],
    exports: [...components],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AppComponentSharedModule {

}
