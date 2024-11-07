import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RecipientDonatedOnComponent } from './recipient-donated-on.component';

describe('RecipientDonatedOnComponent', () => {
  let component: RecipientDonatedOnComponent;
  let fixture: ComponentFixture<RecipientDonatedOnComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RecipientDonatedOnComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RecipientDonatedOnComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
