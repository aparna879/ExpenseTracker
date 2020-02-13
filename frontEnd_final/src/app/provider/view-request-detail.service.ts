import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
HttpClient
@Injectable({
  providedIn: 'root'
})
export class ViewRequestDetailService {

  constructor(public http: HttpClient) {
    
  }
  // Used to fetch the details of The specific Request

  getExpenseDetails(expReqId): Observable<any> {
    return this.http.get("/reimburseit/expensedetails/views?expReqId=" + expReqId);
  }
}
