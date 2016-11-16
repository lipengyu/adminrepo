import {Component, ElementRef, Input, Output, EventEmitter} from "@angular/core";

@Component({
    selector: '[sui-dropdown]',
    template: `<ng-content></ng-content>`
})
export class SuiDropdown {

    el: ElementRef;

    @Input() ngModel: any;

    @Output() ngModelChange = new EventEmitter();

    constructor(el: ElementRef) {
        this.el = el;
    }

    ngAfterViewInit() {
        $(this.el.nativeElement).dropdown({
            onChange: (value, text, choice) => {
                this.ngModel = {
                    value: value,
                    text: text
                };
                this.ngModelChange.emit(this.ngModel);
            }
        });
    }

}