import {Injectable} from "@angular/core";

@Injectable()
export class AlertService {

    constructor() {
    }

    alert(options: {title: string, text?: string, type?: string}, callback?: Function) {
        swal(options, callback);
    }

}
