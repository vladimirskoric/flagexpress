import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DonorItemsDetailsComponent } from './donor-items-details.component';

describe('DonorItemsDetailsComponent', () => {
  let component: DonorItemsDetailsComponent;
  let fixture: ComponentFixture<DonorItemsDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DonorItemsDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DonorItemsDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
