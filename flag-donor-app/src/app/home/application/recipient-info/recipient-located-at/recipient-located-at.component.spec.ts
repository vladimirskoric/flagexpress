import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RecipientLocatedAtComponent } from './recipient-located-at.component';

describe('RecipientLocatedAtComponent', () => {
  let component: RecipientLocatedAtComponent;
  let fixture: ComponentFixture<RecipientLocatedAtComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RecipientLocatedAtComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RecipientLocatedAtComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
