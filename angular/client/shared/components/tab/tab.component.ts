import {Component, ElementRef, Input} from "@angular/core";

@Component({
    selector: '[sui-tab]',
    template: `<ng-content></ng-content>`
})
export class SuiTab {

    @Input('sui-tab') tab_id;

    el: ElementRef;

    constructor(el: ElementRef) {
        this.el = el;
    }

    ngAfterViewInit() {
        $(this.el.nativeElement).tab();
        this.el.nativeElement.dataset.tab = this.tab_id;
    }

}
