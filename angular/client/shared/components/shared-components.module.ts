import {NgModule} from "@angular/core";
import {ReactiveFormsModule, FormsModule} from "@angular/forms";
import {BrowserModule} from "@angular/platform-browser";
import {SharedServicesModule} from "../services/shared-services.module";
import {SuiSidebarComponent} from "./sidebar/sidebar.component";
import {SuiFormComponent} from "./form/form.component";
import {SuiDropdown} from "./dropdown/dropdown.component";
import {SuiTab} from "./tab/tab.component";
import {SuiValidationInputComponent} from "./validation/validation-input.component";
import {SuiValidationMessagesComponent} from "./validation/validation-messages.component";
import {SuiSticky} from "./sticky/sticky.component";

export const components = [
    SuiDropdown,
    SuiFormComponent,
    SuiSidebarComponent,
    SuiSticky,
    SuiTab,
    SuiValidationInputComponent,
    SuiValidationMessagesComponent
];

@NgModule({
    imports: [BrowserModule, ReactiveFormsModule, FormsModule, SharedServicesModule],
    declarations: [...components],
    exports: [...components]
})
export class SharedComponentsModule {

}

export * from "./validation/validation.utils";