import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { BootstrapAddComponent } from './component/bootstrap-add/bootstrap-add.component';
import { LineChartComponent } from './component/line-chart/line-chart.component';
import { BarChartComponent } from './component/bar-chart/bar-chart.component';
import { PieChartComponent } from './component/pie-chart/pie-chart.component';
import { AnalyticsReportComponent } from './component/analytics-report/analytics-report.component';
import { ExpenseTrackerComponent } from './component/expense-tracker/expense-tracker.component';
import { ViewComponent } from './component/view/view.component';
import { LoginComponent } from './component/login/login.component';
import { PageNotFoundComponent } from './component/page-not-found/page-not-found.component';
import { ViewPendingApprovalsComponent } from './component/view-pending-approvals/view-pending-approvals.component';


const routes: Routes = [
  {
    path:'',
    redirectTo:'view',
    pathMatch: 'full'
  },  
  {
    path:'login',
    component: LoginComponent
   
  },  
  {
    path:'view',
    component: ViewComponent    
  },
  {
    path:'pending',
    component: ViewPendingApprovalsComponent
    
  },
  {
    path:'track',
    component: ExpenseTrackerComponent    
  },  
  {
    path: 'add',
    component: BootstrapAddComponent    
  },
  {
    path: 'report',
    component: AnalyticsReportComponent
  },  
  {
    path: 'piechart',
    component: PieChartComponent
  },
  {
    path: 'barchart',
    component: BarChartComponent
  },
  {
    path: 'linechart',
    component: LineChartComponent
  },
  {
    path:'**',
    component: PageNotFoundComponent
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }