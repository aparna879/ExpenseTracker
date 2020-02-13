import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

const tempExpenses = {
  currentdate: '',
  empid: '',
  totalcost: '',
  categoryname: '',
  description: '',
  listdetails: []
};

@Injectable({
  providedIn: 'root'
})

export class AddExpenseRequestService {

  constructor(public http: HttpClient) { }

  getCategories() {
    return this.http.get('reimburseit/category/sendcategory');
  }

  addExpenseRequest(date, empId, amount, category, desc, arrExpenses: SendDetail[]) {
    tempExpenses.currentdate = date;
    tempExpenses.empid = empId;
    tempExpenses.totalcost = amount;
    tempExpenses.categoryname = category;
    tempExpenses.description = desc;

    let object: any[] = [];
    let formData = new FormData();

    for (let i=0; i< arrExpenses.length; i++) {
      formData.append('filesToSubmit[]', arrExpenses[i].expensefile);
      object.push(new SendDetailWithoutFile(arrExpenses[i].expensename, arrExpenses[i].expensecost, arrExpenses[i].expensedate));
    }

    tempExpenses.listdetails = object;

    let data2: string = JSON.stringify(tempExpenses);
    

    formData.append('data', data2);

    return this.http.post('reimburseit/expense/add', formData);

  }

  sendFiles(formData) {
    return this.http.post<any>('reimburseit/ocr/scan', formData);
  }

}


export class SendDetail {
  public expensename: string;
  public expensecost: number;
  public expensedate: Date;
  public expensefile: string;

  constructor(name, cost, date, file) {
    this.expensename = name;
    this.expensecost = cost;
    this.expensedate = date;
    this.expensefile = file;
  }
}

export class SendDetailWithoutFile {
  public expensename: string;
  public expensecost: number;
  public expensedate: Date;

  constructor(name, cost, date) {
    this.expensename = name;
    this.expensecost = cost;
    this.expensedate = date;
  }
}