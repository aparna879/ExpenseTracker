import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators, FormBuilder } from '@angular/forms';
import { DatePipe } from '@angular/common';
import { SessionStorageService } from 'angular-web-storage';
import { Router } from '@angular/router';
import { AddExpenseRequestService } from 'src/app/provider/add-expense-request.service';

@Component({
  selector: 'app-bootstrap-add',
  templateUrl: './bootstrap-add.component.html',
  styleUrls: ['./bootstrap-add.component.scss']
})
export class BootstrapAddComponent implements OnInit {

  //Storage Variables
  tempName: string;
  tempCost: number;
  tempDate: Date;
  tempFile: string;
  dateVar: any;
  total = 0;

  //Form Handling
  fileForm: FormGroup;
  addExpenseForm: FormGroup;
  nestedForm: FormGroup;
  editForm: FormGroup;

  //DOM Manipulation
  sessionData = [];
  categoriesFromDB = [];
  editFlag = false;
  addExpenseFlag = false;
  recdOCR: ExpenseDetail[];
  recordsAuto = [];
  recordsManual = [];

  //Files
  fileToUpload: string;
  myFiles: string[] = [];


  constructor(public formBuilder: FormBuilder, public expenseService: AddExpenseRequestService,
    public datePipe: DatePipe, public session: SessionStorageService, public router: Router) {
  }

  ngOnInit() {
    // if (this.session.get('2')) {
    //   this.session.set('2', false);
    //   window.location.reload();
    // }
    if ((!this.session.get("key") && !this.session.get("2"))) {
      this.router.navigate(['/login']);

    }
    else if (this.session.get("key").empId == 0) {
      this.router.navigate(['/login']);
    }
    else {

      this.sessionData = this.session.get('key');

      this.expenseService.getCategories().subscribe((cats: string[]) => {
        for (let i = 0; i < cats.length; i++) {
          this.categoriesFromDB.push(cats[i]);
        }
      },
        (err) => {
          alert('Server Unreachable');
      });

      this.addExpenseForm = new FormGroup({
        category: new FormControl('', Validators.required),
        description: new FormControl('', Validators.required)
      });

      this.nestedForm = new FormGroup({
        nestedName: new FormControl('', Validators.required),
        nestedCost: new FormControl('', Validators.required),
        nestedDate: new FormControl('', Validators.required),
        nestedFile: new FormControl('', Validators.required)
      });

      this.fileForm = this.formBuilder.group({ });

      this.editForm = new FormGroup({
        editName: new FormControl('', Validators.required),
        editCost: new FormControl('', Validators.required),
        editDate: new FormControl('', Validators.required),
      });

      this.dateVar = this.datePipe.transform(new Date(), 'yyyy-MM-dd');
    }
  }

  onMultipleFileSelect(event) {
    if (this.myFiles.length > 0) {
      this.myFiles = [];
    }

    for (let i = 0; i < event.target.files.length; i++) {
      this.myFiles.push(event.target.files[i]);
    }
  }

  submitFiles() {
    const formData = new FormData();
    let cost: number = 0;

    for (let i = 0; i < this.myFiles.length; i++) {
      formData.append('fileToUpload[]', this.myFiles[i]);
    }

    if (this.myFiles.length > 0) {
      this.expenseService.sendFiles(formData).subscribe(
        (details: ExpenseDetail[]) => {
          this.recdOCR = details;

          for (let i = 0; i < this.recdOCR.length; i++) {
            const name: string = this.recdOCR[i].expensename as string;
            cost = this.recdOCR[i].expensecost as number;
            const date = this.datePipe.transform(this.recdOCR[i].expensedate, 'yyyy-MM-dd');
            const file: string = this.myFiles[i] as string;
            const tempExpense = new SendDetail(name, cost, date, file);

            this.recordsAuto.push(tempExpense);
          }

          this.myFiles = [];
          this.total = this.calculateTotal();
        },
        (err) => {
          alert('Server Unreachable');
        });
    }
    else {
      alert('No Files Selected');
    }
  }

  addExpense() {
    this.addExpenseFlag = true;
    this.fileToUpload = null;
  }

  deleteExpenseManual(item) {
    this.recordsManual.splice(item, 1);
    this.total = this.calculateTotal();
  }

  editExpenseAuto(item) {
    const temp: SendDetail = this.recordsAuto.splice(item, 1)[0] as SendDetail;

    this.tempName = temp.expensename;
    this.tempCost = temp.expensecost;
    this.tempDate = temp.expensedate;
    this.tempFile = temp.expensefile;
    this.editFlag = !this.editFlag;
  }

  editExpenseManual(item) {
    const temp: SendDetail = this.recordsManual.splice(item, 1)[0] as SendDetail;   

    this.tempName = temp.expensename;
    this.tempCost = temp.expensecost;
    this.tempDate = temp.expensedate;
    this.tempFile = temp.expensefile;
    this.editFlag = !this.editFlag;
  }


  editTable() {
    const name = this.editForm.value.editName;
    const cost = this.editForm.value.editCost;
    const date = this.editForm.value.editDate;
    const file = this.tempFile;

    const tempExpense = new SendDetail(name, cost, date, file);

    this.recordsManual.push(tempExpense);

    this.total = this.calculateTotal();

    this.tempFile = null;
    this.editFlag = !this.editFlag;
    this.editForm.reset();
  }

  calculateTotal() {
    let totalTemp = 0;

    for (const item of this.recordsAuto) {
      totalTemp += parseInt(item.expensecost);
    }

    for (const item of this.recordsManual) {
      totalTemp += parseInt(item.expensecost);
    }

    return totalTemp;
  }

  onSingleFileSelect(event) {
    this.fileToUpload = event.target.files[0];    
  }

  addToTable() {
    const name = this.nestedForm.value.nestedName;
    const cost = this.nestedForm.value.nestedCost;
    const date = this.nestedForm.value.nestedDate;
    const file = this.fileToUpload;

    let tempExpense = new SendDetail(name, cost, date, file);

    this.recordsManual.push(tempExpense);
    this.total = this.calculateTotal();   
    this.addExpenseFlag = !this.addExpenseFlag;
    this.nestedForm.reset();
  }

  submitForm() {
    const category = this.addExpenseForm.value.category;
    const date = this.dateVar;
    const desc = this.addExpenseForm.value.description;
    const amount = this.total;
    const arrExpenses = this.recordsAuto;
    const empId = this.session.get('key').empId;

    this.expenseService.addExpenseRequest(date, empId, amount, category, desc, arrExpenses)
      .subscribe((details) => console.log(details.valueOf()));

    this.addExpenseForm.reset();
    this.recordsAuto = [];
    this.recordsManual = [];
    this.total = 0;
  }
}

export class ExpenseDetail {
  public expensename: string;
  public expensecost: number;
  public expensedate: Date;

  constructor() { }

  setName(name: string) {
    this.expensename = name;
  }
  setCost(cost: number) {
    this.expensecost = cost;
  }
  setDate(date: Date) {
    this.expensedate = date;
  }
}

export class SendDetail {
  public expensename: string;
  public expensecost: number;
  public expensedate: Date;
  public expensefile: string;

  constructor(name, cost: number, date, file) {
    this.expensename = name;
    this.expensecost = cost;
    this.expensedate = date;
    this.expensefile = file;
  }
}