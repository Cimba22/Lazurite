import { Component } from '@angular/core';
import {RegistrationRequest} from "../../services/models/registration-request";
import {Router} from "@angular/router";
import {AuthenticationService} from "../../services/services/authentication.service";
import {CommonModule} from "@angular/common";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrl: './register.component.css',
  standalone:true,
  imports: [CommonModule, FormsModule, ReactiveFormsModule]
})
export class RegisterComponent {

  registerRequest: RegistrationRequest = {login: '', email: '', passwordHash: ''}
  errorMsg: Array<string> = [];

  constructor(
    private router: Router,
    private authService: AuthenticationService
  ) {
  }

  register() {
    this.errorMsg = [];
    this.authService.register({
      body: this.registerRequest
    }).subscribe({
        next: () => {
          this.router.navigate(['activate-account']);
        },
        error: (err) => {
          this.errorMsg = err.error.validationErrors;
        }
      });
  }

  login() {
      this.router.navigate(['login'])
  }
}
