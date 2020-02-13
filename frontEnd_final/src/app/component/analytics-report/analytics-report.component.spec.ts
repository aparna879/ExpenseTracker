import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AnalyticsReportComponent } from './analytics-report.component';

describe('AnalyticsReportComponent', () => {
  let component: AnalyticsReportComponent;
  let fixture: ComponentFixture<AnalyticsReportComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AnalyticsReportComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AnalyticsReportComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
