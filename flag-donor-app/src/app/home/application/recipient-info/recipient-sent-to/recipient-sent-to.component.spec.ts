import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RecipientSentToComponent } from './recipient-sent-to.component';

describe('RecipientSentToComponent', () => {
  let component: RecipientSentToComponent;
  let fixture: ComponentFixture<RecipientSentToComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RecipientSentToComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RecipientSentToComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
