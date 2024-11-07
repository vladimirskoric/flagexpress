import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ChooseFlowUpdateComponent } from './choose-flow-update.component';

describe('ChooseFlowUpdateComponent', () => {
  let component: ChooseFlowUpdateComponent;
  let fixture: ComponentFixture<ChooseFlowUpdateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ChooseFlowUpdateComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ChooseFlowUpdateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
