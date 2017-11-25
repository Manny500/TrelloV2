import { Component, OnInit } from '@angular/core';
import { Board } from '../home/board-display.interface';
import { Router } from '@angular/router';
import { BoardDisplayService } from '../home/board-display.service'; 

@Component({
  selector: 'app-user-home',
  templateUrl: './user-home.component.html',
  styleUrls: ['./user-home.component.css']
})

export class UserHomeComponent implements OnInit {

  Boards: Board[];

  constructor(private route: Router, private boardDisplayService: BoardDisplayService) { }

  ngOnInit() {
    // this.displayBoards();
  }

  // displayBoards(): void{
  //   this.boardDisplayService.getUserBoards().subscribe(result => {
  //     this.Boards = result;
  //   })
  // }

  // storeBoardId(id): void{
  //   localStorage.setItem("currentBoardId", JSON.stringify(id))
  // }

}
