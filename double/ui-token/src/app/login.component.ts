import { Component, OnInit } from '@angular/core';
import { AppService } from './app.service';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { Credentials } from './app.models';

@Component({
  templateUrl: './login.component.html'
})
export class LoginComponent {

  error = false
  credentials: Credentials = new Credentials;

  constructor(private app: AppService, private http: HttpClient, private router: Router) {
  }

  login(){
    this.app.authenticate(this.credentials, () => {
      this.router.navigateByUrl('/');
      this.error=false;
    });
    this.error = true;
    return false;
  }

}


