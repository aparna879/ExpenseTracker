import { TestBed } from '@angular/core/testing';

import { AddExpenseRequestService } from './add-expense-request.service';

describe('AddExpenseRequestService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: AddExpenseRequestService = TestBed.get(AddExpenseRequestService);
    expect(service).toBeTruthy();
  });
});
