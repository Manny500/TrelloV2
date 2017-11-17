import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CompanyBoardsComponent } from './company-boards.component';

describe('CompanyBoardsComponent', () => {
  let component: CompanyBoardsComponent;
  let fixture: ComponentFixture<CompanyBoardsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CompanyBoardsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CompanyBoardsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
