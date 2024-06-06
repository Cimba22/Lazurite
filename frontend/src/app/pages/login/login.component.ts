import { Component } from '@angular/core';
import {Router} from '@angular/router';
import {AuthenticationRequest} from "../../services/models/authentication-request";
import {AuthenticationService} from "../../services/services/authentication.service";
import {TokenService} from "../../services/token/token.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  authRequest: AuthenticationRequest = {email: '', passwordHash: ''};
  errorMsg: Array<string> = [];

  constructor(
    private router: Router,
    private authService: AuthenticationService,
    private tokenService: TokenService

  ) {

  }

  login() {
    this.errorMsg = [];
    this.authService.authenticate({
      body: this.authRequest
    }).subscribe({
      next:(res)=> {
        this.tokenService.token = res.token as string;
        this.router.navigate(['wishlists']);
      },
      error: (err) => {
        console.log(err);
        if (err.error.validationErrors){
          this.errorMsg = err.error.validationErrors;
        }else {
          this.errorMsg.push(err.error.error);
        }
      }
    });
  }

  register() {
    this.router.navigate(['register']);

  }
}
