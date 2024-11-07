import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PickUpListComponent } from './pickup-list.component';

describe('PickUpListComponent', () => {
  let component: PickUpListComponent;
  let fixture: ComponentFixture<PickUpListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PickUpListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PickUpListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
