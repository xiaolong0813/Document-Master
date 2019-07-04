import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TransDetailsComponent } from './trans-details.component';

describe('TransDetailsComponent', () => {
  let component: TransDetailsComponent;
  let fixture: ComponentFixture<TransDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TransDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TransDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
