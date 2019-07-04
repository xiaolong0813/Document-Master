import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ModalTransComponent } from './modal-trans.component';

describe('ModalTransComponent', () => {
  let component: ModalTransComponent;
  let fixture: ComponentFixture<ModalTransComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ModalTransComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ModalTransComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
