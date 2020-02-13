import { Router } from '@angular/router';
import { SessionStorageService } from 'angular-web-storage';
import { Component, ViewChild, ChangeDetectorRef } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { MatDialog, MatDialogRef, MatDialogConfig } from '@angular/material/dialog';

import { ViewApprovalPendingService } from 'src/app/provider/view-approval-pending.service';
import { ExpenseDetailComponent } from './../expense-detail/expense-detail.component';


@Component({
  selector: 'app-view-pending-approvals',
  templateUrl: './view-pending-approvals.component.html',
  styleUrls: ['./view-pending-approvals.component.scss']
})


export class ViewPendingApprovalsComponent {

  dialogRef: MatDialogRef<ExpenseDetailComponent>;
  displayedColumns: string[] = ['position', 'category', 'cost', 'details', 'dateOfExpenditure', 'options'];
  expenseApprovalItems : ExpenseApprovalItem[] = [];
  dataSource: any
  expReqId: any

  @ViewChild(MatPaginator, { static: true }) paginator: MatPaginator;
  @ViewChild(MatSort, { static: true }) sort: MatSort;

  constructor(public pendingApproval: ViewApprovalPendingService, public session: SessionStorageService,
    public dialog: MatDialog, public changeDetector: ChangeDetectorRef, public router: Router) {
  }



  ngOnInit() {

    if ((!this.session.get("key") && !this.session.get("2"))) {
      this.router.navigate(['/login']);
    }
    else if (this.session.get("key").empId == 0) {
      this.router.navigate(['/login']);
    }
    else {
      const empId = this.session.get("key").empId;
      const empDesignation = this.session.get("key").empDesignation;
      const empEmail = this.session.get("key").empEmail;

      this.pendingApproval.getPendingApproval(empId, empDesignation, empEmail).subscribe(details => {
        for (let i = 0; i < details.length; i++) {
          this.expenseApprovalItems.push({
            position: i + 1,
            category: details[i].category,
            cost: details[i].total,
            details: 'Show Expense Details',
            dateOfExpenditure: details[i].date,
            options: ["approve", "reject"],
            id: details[i].expReqId
          });
        }
        this.dataSource = new MatTableDataSource<ExpenseApprovalItem>(this.expenseApprovalItems);
        this.dataSource.sort = this.sort;
        this.dataSource.paginator = this.paginator;
      });
    }
  }


  getExpense(event, obj) {
    const id = event.currentTarget.attributes[5].nodeValue;
    const dialogConfig = new MatDialogConfig();

    dialogConfig.width = '1000px';
    dialogConfig.data = {
      id
    }
    this.dialogRef = this.dialog.open(ExpenseDetailComponent, dialogConfig);
  }



  executeAccepted(event, obj) {
    console.log(status + "in execute");
    console.log("did something");
    const id = event.currentTarget.attributes[5].nodeValue;    
    // console.log(id - 1);
    const expReqId = this.expenseApprovalItems[id - 1].id;
    const empEmail = this.session.get("key").empEmail;
    this.pendingApproval.sendRequestAccepted(expReqId, empEmail).subscribe(details => {
      alert("Request Accepted ");
      this.expenseApprovalItems.splice(id, 1);
      // this.ngOnInit();
      this.changeDetector.detectChanges();
      console.log("changed committed");
      window.location.reload();
    })
  }

  executeRejected(event, obj) {    
    const id = event.currentTarget.attributes[5].nodeValue;    
    
    const expReqId = this.expenseApprovalItems[id - 1].id;
    const empEmail = this.session.get("key").empEmail;
    this.pendingApproval.sendRequestRejected(expReqId, empEmail).subscribe(details => {
      alert("Request Rejected" );
      this.expenseApprovalItems.splice(id, 1);
      // this.ngOnInit();
      this.changeDetector.detectChanges();
      console.log("changed committed");
      window.location.reload();
    })
  }

}

export interface ExpenseApprovalItem {
  position: number,
  category: string;
  cost: number;
  details: string;
  dateOfExpenditure: string;
  options: any;
  id: number;
}



