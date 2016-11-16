import {Injectable, APP_INITIALIZER} from "@angular/core";

@Injectable()
class StartupService {

    constructor() {
    }

    load(): Promise<any> {
        return new Promise(resolve => {
            resolve(true);
        });
    }
}

export const StartupServiceProvider = [
    StartupService,
    {
        provide: APP_INITIALIZER,
        useFactory: (startup: StartupService) => () => startup.load(),
        deps: [StartupService],
        multi: true
    }
];