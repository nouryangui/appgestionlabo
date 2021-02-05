import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TooldetailComponent } from './tooldetail.component';

describe('TooldetailComponent', () => {
  let component: TooldetailComponent;
  let fixture: ComponentFixture<TooldetailComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TooldetailComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TooldetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
