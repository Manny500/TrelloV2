import { Component, OnInit } from '@angular/core';
import { Board } from './board-display.interface';
import { Router } from '@angular/router';
import { BoardDisplayService } from './board-display.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  Boards: Board[];

  constructor(private route: Router, private boardDisplayService: BoardDisplayService) { }

  ngOnInit() {
    this.displayBoards();
  }

  displayBoards(): void{
    this.boardDisplayService.getBoards().subscribe(result => {
      this.Boards = result;
    })
  }

}
