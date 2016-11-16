import {Component, ElementRef} from "@angular/core";

@Component({
    selector: '[sui-sidebar]',
    template: `<ng-content></ng-content>`
})
export class SuiSidebarComponent {

    el: ElementRef;

    constructor(el: ElementRef) {
        this.el = el;
    }

    ngAfterViewInit() {
        $(this.el.nativeElement).sidebar();
        this.el.nativeElement.querySelectorAll("a.item.link").forEach((element) => element.addEventListener('click', () => {
            this.toggle();
        }))
    }

    toggle() {
        $(this.el.nativeElement).sidebar('toggle');
    }

}