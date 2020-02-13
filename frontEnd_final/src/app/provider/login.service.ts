import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

let tempCredentials = {
  email :'',
  password :'',
};

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(public http: HttpClient) { }  

  // Used to validate the user.

  validateLogin(email, password) : Observable<any> {
    tempCredentials.email = email;
    tempCredentials.password = password;
    return this.http.post('reimburseit/reimburse/validate', tempCredentials);
  }
}
