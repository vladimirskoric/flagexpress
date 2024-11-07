import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DonorReviewComponent } from './donor-review.component';

describe('DonorReviewComponent', () => {
  let component: DonorReviewComponent;
  let fixture: ComponentFixture<DonorReviewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DonorReviewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DonorReviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
