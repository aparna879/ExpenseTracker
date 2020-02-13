import { TestBed } from '@angular/core/testing';

import { ViewRequestDetailService } from './view-request-detail.service';

describe('ViewRequestDetailService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ViewRequestDetailService = TestBed.get(ViewRequestDetailService);
    expect(service).toBeTruthy();
  });
});
