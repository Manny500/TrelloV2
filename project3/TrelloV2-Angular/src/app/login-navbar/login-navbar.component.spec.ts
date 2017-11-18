import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LoginNavbarComponent } from './login-navbar.component';

describe('LoginNavbarComponent', () => {
  let component: LoginNavbarComponent;
  let fixture: ComponentFixture<LoginNavbarComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LoginNavbarComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LoginNavbarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
