import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DonorCompleteComponent } from './donor-complete.component';

describe('DonorCompleteComponent', () => {
  let component: DonorCompleteComponent;
  let fixture: ComponentFixture<DonorCompleteComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DonorCompleteComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DonorCompleteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
