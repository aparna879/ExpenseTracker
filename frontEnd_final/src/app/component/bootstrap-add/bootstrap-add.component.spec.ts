import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BootstrapAddComponent } from './bootstrap-add.component';

describe('BootstrapAddComponent', () => {
  let component: BootstrapAddComponent;
  let fixture: ComponentFixture<BootstrapAddComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BootstrapAddComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BootstrapAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
