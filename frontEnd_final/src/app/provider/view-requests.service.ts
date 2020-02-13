import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { SessionStorageService } from 'angular-web-storage';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ViewRequestsService {

  constructor(public http: HttpClient, public session: SessionStorageService) { }

  // Used to fetch Employee Requests

  getRequests(): Observable<any> {    
    return this.http.get("reimburseit/viewexpenses/view?empId=" + this.session.get("key").empId);
  }
}
