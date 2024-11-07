import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router} from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  signForm: FormGroup;
  processing: boolean;

  private errorMessages = {
    required: 'Required',
    maxlength: 'Maximum length exceeded',
  };
  constructor(
    private router: Router,
    private formBuilder: FormBuilder,
  ) { }

  ngOnInit(): void {
    this.signForm = this.formBuilder.group({
      username: ['', [
        Validators.required,
        Validators.maxLength(20),
      ]],
      password: ['', [
        Validators.required,
        Validators.maxLength(20),
      ]],
    });
  }

  async submit(): Promise<void> {
    if (!this.signForm.invalid){
      this.processing = true;
      setTimeout(() => {
        this.processing = false;
        this.router.navigateByUrl('/dashboard');
      }, 1000);
    }
  }

  public getError(controlName: string): string {
    const field = this.signForm.get(controlName);
    const errorKeys = Object.keys(field.errors || {});
    if (!errorKeys.length) {
      return null;
    }
    return field.errors[errorKeys[0]].message || this.errorMessages[errorKeys[0]];
  }

}
