import { MatInputModule, MatButtonModule, MatSelectModule, MatIconModule, MatNativeDateModule } from '@angular/material';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatListModule } from '@angular/material/list';
import { MatTableModule } from '@angular/material/table';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatSortModule } from '@angular/material/sort';
import { MatDialogModule } from '@angular/material/dialog';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatStepperModule } from '@angular/material/stepper';
import { MatProgressBarModule } from '@angular/material/progress-bar';
import { MatCardModule } from '@angular/material/card';
import { MatChipsModule } from '@angular/material/chips';
import { MatTabsModule } from '@angular/material/tabs';


import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { DatePipe } from '@angular/common';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AngularWebStorageModule } from 'angular-web-storage';
import { ChartsModule } from 'ng2-charts';


import { LoginComponent } from './component/login/login.component';
import { PageNotFoundComponent } from './component/page-not-found/page-not-found.component';
import { ViewComponent } from './component/view/view.component';
import { ExpenseDetailComponent } from './component/expense-detail/expense-detail.component';
import { ViewPendingApprovalsComponent } from './component/view-pending-approvals/view-pending-approvals.component';
import { ExpenseTrackerComponent } from './component/expense-tracker/expense-tracker.component';
import { AnalyticsReportComponent } from './component/analytics-report/analytics-report.component';
import { BootstrapAddComponent } from './component/bootstrap-add/bootstrap-add.component';
import { PieChartComponent } from './component/pie-chart/pie-chart.component';
import { LineChartComponent } from './component/line-chart/line-chart.component';
import { BarChartComponent } from './component/bar-chart/bar-chart.component';





@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    PageNotFoundComponent,
    ViewComponent,
    ExpenseDetailComponent,    
    ViewPendingApprovalsComponent,
    ExpenseTrackerComponent,
    AnalyticsReportComponent,
    BarChartComponent,
    LineChartComponent,
    PieChartComponent,
    BootstrapAddComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    FormsModule,
    MatInputModule,
    MatButtonModule,
    MatSelectModule,
    MatIconModule,
    MatSidenavModule,
    MatToolbarModule,
    MatListModule,
    MatTableModule,
    MatPaginatorModule,
    MatSortModule,
    MatDialogModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatSelectModule,
    MatStepperModule,
    MatProgressBarModule,
    MatCardModule,
    MatChipsModule,
    MatTabsModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
    AngularWebStorageModule,
    ChartsModule
  ],
  entryComponents: [ExpenseDetailComponent],
  providers: [DatePipe],
  bootstrap: [AppComponent]
})

export class AppModule { }