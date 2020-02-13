import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { ChartOptions, ChartType, ChartDataSets } from 'chart.js';
import { Label } from 'ng2-charts';



@Component({
  selector: 'app-bar-chart',
  templateUrl: './bar-chart.component.html',
  styleUrls: ['./bar-chart.component.scss']
})

export class BarChartComponent implements OnInit {

  barChartOptions: ChartOptions = {
    responsive: true,
  };
  
  barChartLabels: Label[] = ['Apple', 'Banana', 'Kiwifruit', 'Blueberry', 'Orange', 'Grapes'];
  barChartType: ChartType = 'bar';
  barChartLegend = true;
  barChartPlugins = [];
  myLabels = [];
  myExpense = [];

  barChartData: ChartDataSets[] = [
    { data: [45, 37, 60, 70, 46, 33], label: 'Best Fruits' }
  ];

  constructor(public http: HttpClient) { }

  ngOnInit() {    

    this.http.get('reimburseit/barchart').subscribe((obj: BarData[]) => {

      for (const item of obj) {
        const temp: BarData = item as BarData;
        this.myLabels.push(temp.firstName + ' ' + temp.lastName);
        this.myExpense.push(temp.totalCost);
      }      

      this.barChartLabels = this.myLabels;
      this.barChartData = [{ data: this.myExpense, label: 'Expense Per Employee' }];
    });
  }
  
}

export class BarData {
  public firstName: string;
  public lastName: string;
  public totalCost: number;

  public constructor(fname: string, lname: string, cost: number) {
    this.firstName = fname;
    this.lastName = lname;
    this.totalCost = cost;
  }
}
