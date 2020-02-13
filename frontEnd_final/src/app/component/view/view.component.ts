import { SessionStorageService } from 'angular-web-storage';
import { Router } from '@angular/router';
import { Component, ViewChild } from '@angular/core';

import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { MatDialog, MatDialogRef, MatDialogConfig } from '@angular/material/dialog';

import { ViewRequestsService } from './../../provider/view-requests.service';
import { ExpenseTrackerComponent } from './../expense-tracker/expense-tracker.component';
import { ExpenseDetailComponent } from './../expense-detail/expense-detail.component';




@Component({
  selector: 'app-view',
  templateUrl: './view.component.html',
  styleUrls: ['./view.component.scss']
})


export class ViewComponent {

  dialogRef: MatDialogRef<ExpenseDetailComponent>;
  dialogRef1: MatDialogRef<ExpenseTrackerComponent>;
  expenseRequestItems: ExpenseRequestItem[] = [];
  displayedColumns: string[] = ['position', 'category', 'cost', "status", "options", "date", "track"];
  dataSource


  constructor(public dialog: MatDialog, public session: SessionStorageService, public viewRequestService: ViewRequestsService, public router: Router) { }

  @ViewChild(MatPaginator, { static: true }) paginator: MatPaginator;
  @ViewChild(MatSort, { static: true }) sort: MatSort;


  ngOnInit() {
    if ((!this.session.get("key") && !this.session.get("2"))) {
      this.router.navigate(['/login']);

    }
    else if (this.session.get("key").empId == 0) {
      this.router.navigate(['/login']);
    }
    else {
      this.viewRequestService.getRequests().subscribe(details => {        
        for (let i = 0; i < details.length; i++) {          
          this.expenseRequestItems.push({
            expReqId: details[i].expreqid,
            position: i,
            category: details[i].categoryname,
            cost: details[i].totalcost,
            status: this.mapper(details[i].status),
            passingStatus: details[i].status,
            options: 'Show Expense Details',
            date: details[i].currentdate,
            track: "Track Expense"
          });
        }        
        this.dataSource = new MatTableDataSource<ExpenseRequestItem>(this.expenseRequestItems);
        this.dataSource.paginator = this.paginator;
        this.dataSource.sort = this.sort;
      });
    }
  }

  mapper(number) {
    switch (number) {
      case 1: {
        return 'Initiated';
      }
      case 2: {
        return 'Approved by PM'
      }
      case 3: {
        return 'Approved by HR'
      }
      case 4: {
        return 'Approved by BU'
      }
      case 5: {
        return 'Approved'
      }
      case 6: {
        return 'Reject by PM'
      }
      case 7: {
        return 'Reject by HR'
      }
      case 8: {
        return 'Reject by BUH'
      }
      case 9: {
        return 'Reject by FT'
      }
    }
  }




  getExpenses(event, obj) {
    const id = event.currentTarget.attributes[5].nodeValue;
    const expReqId = obj.expenseRequestItems[id].expReqId;
    const dialogConfig = new MatDialogConfig();

    dialogConfig.width = '1000px';
    dialogConfig.data = {
      id: expReqId
    }

    this.dialogRef = this.dialog.open(ExpenseDetailComponent, dialogConfig);    
  }


  showTracker(event, obj) {
    const id = event.currentTarget.attributes[5].nodeValue;    
    const status = obj.expenseRequestItems[id].passingStatus;
    
    const dialogConfig = new MatDialogConfig();
    dialogConfig.width = "1000px";
    dialogConfig.data = { status }
    this.dialogRef1 = this.dialog.open(ExpenseTrackerComponent, dialogConfig);
  }

}


export interface ExpenseRequestItem {
  expReqId: number,
  position: number;
  category: string;
  cost: number;
  status: string;
  passingStatus: number;
  options: string;
  date: string;
  track: string
}

