import {platformBrowserDynamic} from "@angular/platform-browser-dynamic";
import {enableProdMode} from "@angular/core";
import {AppModule} from "./app";

if (process.env.ENV === "production") {
    enableProdMode();
}

platformBrowserDynamic().bootstrapModule(AppModule).then(() => console.log("Application bootstrapped.")).catch(err => console.error(err));
