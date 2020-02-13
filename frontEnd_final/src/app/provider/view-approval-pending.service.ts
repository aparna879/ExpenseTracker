import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ViewApprovalPendingService {

  constructor(public http: HttpClient) { }

  // Used to fetch the Pending Approvals for a specific Employee

  getPendingApproval(empId, empDesignation, empEmail): Observable<any> {    
    return this.http.get("/reimburseit/pending/getstatus?empId=" + empId +"&empDesig="+empDesignation +"&empEmail=" + empEmail);
  } 

  sendRequestAccepted(expReqId, empEmail): Observable<any> {    
    return this.http.get("/reimburseit/approval/accept?ExpReqId="+expReqId+ "&ApproverEmail="+ empEmail + 
      "&ExpStatus=Approved");
  }
  
  sendRequestRejected(expReqId, empEmail): Observable<any> {    
    return this.http.get("/reimburseit/approval/reject?ExpReqId="+expReqId+ "&ApproverEmail="+ empEmail + 
      "&ExpStatus=Rejected");
  }  
}
