import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ChooseFlowComponent } from './choose-flow.component';

describe('ChooseFlowComponent', () => {
  let component: ChooseFlowComponent;
  let fixture: ComponentFixture<ChooseFlowComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ChooseFlowComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ChooseFlowComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
