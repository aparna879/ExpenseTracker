import { TestBed } from '@angular/core/testing';

import { ViewApprovalPendingService } from './view-approval-pending.service';

describe('ViewApprovalPendingService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ViewApprovalPendingService = TestBed.get(ViewApprovalPendingService);
    expect(service).toBeTruthy();
  });
});
