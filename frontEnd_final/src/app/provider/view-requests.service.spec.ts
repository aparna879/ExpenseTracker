import { TestBed } from '@angular/core/testing';

import { ViewRequestsService } from './view-requests.service';

describe('ViewRequestsService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ViewRequestsService = TestBed.get(ViewRequestsService);
    expect(service).toBeTruthy();
  });
});
