import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { SessionStorageService } from 'angular-web-storage';

import { LoginService } from 'src/app/provider/login.service';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})

export class LoginComponent implements OnInit {

  credentials: boolean = false;

  constructor(public loginService: LoginService, public session: SessionStorageService,
    public router: Router) { }


  loginForm = new FormGroup({
    email: new FormControl('', Validators.required),
    password: new FormControl('', Validators.required),
  });


  ngOnInit() { }

  submitForm() {
    const email = this.loginForm.value.email;
    const password = this.loginForm.value.password;
    this.loginService.validateLogin(email, password).subscribe(details => {
      if (details.empId == 0)
        this.credentials = true;
      else {
        this.session.set('key', details);
        this.session.set('2', true);

        this.router.navigate(['/view'])
          .then(() => {
            window.location.reload();
          });
      }
    });
  }
  
}
