import {ValidationService} from "./../../services/validation/validation.service";
import {Component, Host, Input} from "@angular/core";
import {AbstractControl, FormGroupDirective} from "@angular/forms";
import {ValidationUtils} from "./validation.utils";

@Component({
    selector: 'sui-validation-messages',
    template: `<small style="color: #9F3A38" *ngIf="errorMessage !== null">{{errorMessage}}</small>`
})
export class SuiValidationMessagesComponent {

    private control: AbstractControl;

    private errorMessage: string;

    @Input("control-name") controlName: string;

    constructor(@Host() private formGroup: FormGroupDirective) {
    }

    ngAfterViewInit() {
        this.control = this.formGroup.form.controls[this.controlName];
        this.control.statusChanges.subscribe(val => {
            this.errorMessage = this.getErrors();
        });
        this.formGroup.ngSubmit.subscribe(() => {
            setTimeout(() => {
                this.errorMessage = this.getErrors();
            })
        })
    }

    getErrors(): string {
        if (this.control) {
            for (let propertyName in this.control.errors) {
                if (this.control.errors.hasOwnProperty(propertyName) || this.control.touched) {
                    return ValidationUtils.getValidatorErrorMessage(propertyName, this.control.errors[propertyName]);
                }
            }
        }
        return null;
    }
}