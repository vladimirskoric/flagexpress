import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DonorItemsComponent } from './donor-items.component';

describe('DonorItemsComponent', () => {
  let component: DonorItemsComponent;
  let fixture: ComponentFixture<DonorItemsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DonorItemsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DonorItemsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
