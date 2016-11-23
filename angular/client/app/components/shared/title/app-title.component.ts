import {Component, Input, ElementRef} from "@angular/core";

@Component({
    selector: 'app-title',
    template: `
        <h1 class="ui header">{{text}}
            <div class="sub header"><ng-content></ng-content></div>
            <div class="ui divider"></div>
        </h1>`
})
export class AppTitleComponent {

    el: ElementRef;

    @Input() text: string;

    constructor(el: ElementRef) {
        this.el = el;
    }

}
