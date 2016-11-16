import {Component, ElementRef, Input, Output, EventEmitter, HostListener} from "@angular/core";

@Component({
    selector: '[sui-form]',
    template: `<ng-content></ng-content>`
})
export class SuiFormComponent {

    el: ElementRef;

    @Input() validation: any = {};

    @Output() onFormSubmit = new EventEmitter();

    constructor(el: ElementRef) {
        this.el = el;
    }

    ngAfterViewInit() {
        $(this.el.nativeElement).form(this.validation);
    }

    @HostListener('submit', ['$event'])
    submit(event) {
        let values = this.getValues();
        let res: any = {
            valid: this.isValid()
        };
        if (res.valid) {
            res.values = this.getValues();
        }
        this.onFormSubmit.emit(res);
    }

    isValid() {
        return $(this.el.nativeElement).form('is valid');
    }

    getValues() {
        return $(this.el.nativeElement).form('get values');
    }
}