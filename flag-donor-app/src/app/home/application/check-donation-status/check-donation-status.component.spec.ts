import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CheckDonationStatusComponent } from './check-donation-status.component';

describe('CheckDonationStatusComponent', () => {
  let component: CheckDonationStatusComponent;
  let fixture: ComponentFixture<CheckDonationStatusComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CheckDonationStatusComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CheckDonationStatusComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
