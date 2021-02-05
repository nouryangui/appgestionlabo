import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MembertoolformComponent } from './membertoolform.component';

describe('MembertoolformComponent', () => {
  let component: MembertoolformComponent;
  let fixture: ComponentFixture<MembertoolformComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MembertoolformComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MembertoolformComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
