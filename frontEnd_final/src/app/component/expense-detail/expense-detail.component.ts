import { Component, OnInit, Inject } from '@angular/core';

import {MatTableDataSource } from '@angular/material/table';
import {MAT_DIALOG_DATA} from "@angular/material";

import { ViewRequestDetailService } from './../../provider/view-request-detail.service';

@Component({
  selector: 'app-expense-detail',
  templateUrl: './expense-detail.component.html',
  styleUrls: ['./expense-detail.component.scss']
})

export class ExpenseDetailComponent implements OnInit {
  expenseDetailItems: ExpenseDetailItem[] = [];
  displayedColumns: string[] = ['position', 'name', 'cost', 'dateOfExpenditure'];
  
  dataSource: any;
  expReqId: any;
  
  constructor(@Inject(MAT_DIALOG_DATA) data, public reqDetail: ViewRequestDetailService) {
    this.expReqId = data.id;
   }
  
  ngOnInit() {    
    this.reqDetail.getExpenseDetails(this.expReqId).subscribe(details => {
      for(let i = 0; i < details.length; i++) {   
        console.log(details[i]);     
        this.expenseDetailItems.push({          
          position: i + 1,
          name: details[i].expensename,
          cost: details[i].expensecost,        
          dateOfExpenditure: details[i].expensedate,        
        });        
      }
      this.dataSource = new MatTableDataSource<ExpenseDetailItem>(this.expenseDetailItems);
    });
  }

}



export interface ExpenseDetailItem {
  position: number;
  name: string;
  cost: number;
  dateOfExpenditure: number; 
}

