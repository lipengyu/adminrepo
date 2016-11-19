import {Directive, ElementRef, Host, Input} from "@angular/core";
import {AbstractControl, FormGroupDirective} from "@angular/forms";

@Directive({
    selector: "[sui-validation-input]"
})
export class SuiValidationInputComponent {

    private control: AbstractControl;
    @Input() formControlName: string;

    constructor(@Host() private formGroup: FormGroupDirective, private elementRef: ElementRef) {
    }

    ngAfterViewInit() {
        this.control = this.formGroup.form.controls[this.formControlName];
        if (this.control == undefined) {
            throw new Error(`Field '${this.formControlName}' doesn't exist.`);
        }
        this.formGroup.ngSubmit.subscribe(() => {
            this.controlStatus(this.control.status);
        });
        this.control.statusChanges.subscribe((val) => {
            this.controlStatus(val);
        })
    }

    controlStatus(status: string) {
        setTimeout(() => {
            if (status == 'INVALID') {
                this.elementRef.nativeElement.parentElement.classList.add('error');
            } else {
                this.elementRef.nativeElement.parentElement.classList.remove('error');
            }
        })
    }

}