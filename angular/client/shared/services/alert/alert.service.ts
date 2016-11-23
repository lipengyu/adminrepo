import {Injectable} from "@angular/core";

interface SwalOptions {
    title?: string,
    text?: string,
    type?: string,
    allowEscapeKey?: boolean,
    customClass?: string,
    allowOutsideClick?: boolean,
    showCancelButton?: boolean,
    showConfirmButton?: boolean,
    confirmButtonText?: string,
    confirmButtonColor?: string,
    cancelButtonText?: string,
    closeOnConfirm?: boolean,
    closeOnCancel?: boolean,
    imageUrl?: string,
    imageSize?: string,
    timer?: number,
    html?: boolean,
    animation?: boolean,
    inputType?: string,
    inputPlaceholder?: string,
    inputValue?: string,
    showLoaderOnConfirm?: boolean
}

@Injectable()
export class AlertService {

    constructor() {
    }

    alert(options: SwalOptions, callback?: Function) {
        if (options) {
            swal(options, callback);
        }
    }

}
