import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ScrumBoardViewComponent } from './scrum-board-view.component';

describe('ScrumBoardViewComponent', () => {
  let component: ScrumBoardViewComponent;
  let fixture: ComponentFixture<ScrumBoardViewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ScrumBoardViewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ScrumBoardViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
