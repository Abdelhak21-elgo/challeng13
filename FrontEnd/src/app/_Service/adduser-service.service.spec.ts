import { TestBed } from '@angular/core/testing';

import { AdduserServiceService } from './adduser-service.service';

describe('AdduserServiceService', () => {
  let service: AdduserServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AdduserServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
