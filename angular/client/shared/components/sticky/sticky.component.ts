import {Component, ElementRef, Input} from "@angular/core";

@Component({
    selector: '[sui-sticky]',
    template: `<ng-content></ng-content>`
})
export class SuiSticky {

    el: ElementRef;

    @Input("sui-sticky") context: string;

    @Input("sticky-offset") offset: number;

    @Input("sticky-observe") observeChanges: boolean;

    constructor(el: ElementRef) {
        this.el = el;
    }

    ngAfterViewInit() {
        let options: any = {};
        this.el.nativeElement.classList.add("ui", "sticky");
        if (this.context) {
            options.context = this.context;
        }
        options.offset = this.offset || 100;
        options.observeChanges = this.observeChanges || true;
        $(this.el.nativeElement).sticky(options);
    }

}
