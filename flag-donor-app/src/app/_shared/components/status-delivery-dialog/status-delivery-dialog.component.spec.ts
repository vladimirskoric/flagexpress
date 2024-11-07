import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { StatusDeliveryDialogComponent } from './status-delivery-dialog.component';

describe('StatusDeliveryDialogComponent', () => {
  let component: StatusDeliveryDialogComponent;
  let fixture: ComponentFixture<StatusDeliveryDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ StatusDeliveryDialogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(StatusDeliveryDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
