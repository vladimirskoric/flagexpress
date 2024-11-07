import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DonorItemsDetailsDescriptionComponent } from './donor-items-details-description.component';

describe('DonorItemsDetailsDescriptionComponent', () => {
  let component: DonorItemsDetailsDescriptionComponent;
  let fixture: ComponentFixture<DonorItemsDetailsDescriptionComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DonorItemsDetailsDescriptionComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DonorItemsDetailsDescriptionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
