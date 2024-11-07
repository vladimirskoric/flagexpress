import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ResultDonationStatusComponent } from './result-donation-status.component';

describe('ResultDonationStatusComponent', () => {
  let component: ResultDonationStatusComponent;
  let fixture: ComponentFixture<ResultDonationStatusComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ResultDonationStatusComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ResultDonationStatusComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
