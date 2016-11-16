import {NgModule} from "@angular/core";
import {SharedServicesModule} from "../services/shared-services.module";
import {SuiSidebarComponent} from "./sidebar/sidebar.component";
import {SuiFormComponent} from "./form/form.component";
import {SuiDropdown} from "./dropdown/dropdown.component";
import {SuiTab} from "./tab/tab.component";

export const components = [
    SuiDropdown,
    SuiFormComponent,
    SuiSidebarComponent,
    SuiTab
];

@NgModule({
    imports: [SharedServicesModule],
    declarations: [...components],
    exports: [...components]
})
export class SharedComponentsModule {

}