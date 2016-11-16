import {ModuleWithProviders} from "@angular/core";
import {RouterModule, Routes} from "@angular/router";

const appRoutes: Routes = [
    {path: "", redirectTo: "common/home", pathMatch: "full"},
    {path: "common", loadChildren: "./components/+common/common.module#AppCommonModule"},
    {path: "security", loadChildren: "./components/+security/security.module#AppSecurityModule"},
    {path: "projet", loadChildren: "./components/+projet/projet.module#AppProjetModule"},
    {path: "referentiel", loadChildren: "./components/+referentiel/referentiel.module#AppReferentielModule"},
    {path: "administration", loadChildren: "./components/+administration/administration.module#AppAdministrationModule"}
];

export const AppRouting: ModuleWithProviders = RouterModule.forRoot(appRoutes, {useHash: true});
