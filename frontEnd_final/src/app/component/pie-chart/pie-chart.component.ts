import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { ChartType, ChartOptions } from 'chart.js';
import { SingleDataSet, Label, monkeyPatchChartJsLegend, monkeyPatchChartJsTooltip } from 'ng2-charts';

@Component({
  selector: 'app-pie-chart',
  templateUrl: './pie-chart.component.html',
  styleUrls: ['./pie-chart.component.scss']
})
export class PieChartComponent implements OnInit {

  public pieChartLabels: Label[];
  public pieChartData: SingleDataSet = [30, 50, 20];
  public pieChartType: ChartType = 'pie';
  public pieChartLegend = true;
  public pieChartPlugins = [];

  myLabels = [];
  myExpense = [];

  public pieChartOptions: ChartOptions = {
    responsive: true,
  };

  constructor(public http: HttpClient) {
    monkeyPatchChartJsTooltip();
    monkeyPatchChartJsLegend();
  }

  ngOnInit() {
    this.http.get('reimburseit/piechart').subscribe((obj) => {
      const strMap = new Map<string, number>();

      for (const k of Object.keys(obj)) {
        strMap.set(k, obj[k]);
        this.myLabels.push(k);
        this.myExpense.push(obj[k]);
      }
    
      this.pieChartLabels = this.myLabels;
      this.pieChartData = this.myExpense;
    });
  }
}
