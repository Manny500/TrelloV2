import { Component, OnInit, Input } from '@angular/core';
import { Board } from './board-display.interface';
import { Router } from '@angular/router';
import { BoardDisplayService } from './board-display.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  
  currentUserRoleType = JSON.parse(localStorage.getItem("currentUser")).roleType; 
  Boards: Board[];
  
  @Input() makeBoard: Board;

  //CreateBoard
  bId: number;
  tv2Id: number;
  bTotal: number;
  bTitle: string;
  tv2Team: number;

  constructor(private route: Router, private boardDisplayService: BoardDisplayService) { }

  ngOnInit() {
    this.displayBoards();
  }

  displayBoards(): void{
    this.boardDisplayService.getMasterBoards().subscribe(result => {
      this.Boards = result;
      this.Boards = this.Boards.filter(item => (item.tv2Id == JSON.parse(localStorage.getItem("currentUser")).userId || item.tv2Team == JSON.parse(localStorage.getItem("currentUser")).teamId));      
      localStorage.setItem('currentBoards', JSON.stringify(result))
    })
    this.tv2Id = JSON.parse(localStorage.getItem("currentUser")).userId;
    this.tv2Team = JSON.parse(localStorage.getItem("currentUser")).teamId;
  }

  //store the id of the board you click on as currentBoardId
  storeBoardId(board: Board): void{
    localStorage.setItem("currentBoard", JSON.stringify(board));
    localStorage.setItem("currentBoardId", JSON.stringify(board.bId))
  }

  done(condition:number) {
    if (condition == 1) {

      this.makeBoard = {
        bId: 0, //sql sequece will change this to appropriate number
        tv2Id: JSON.parse(localStorage.getItem("currentUser")).userId,
        bTotal: 0,
        bTitle: this.bTitle,
        tv2Team: JSON.parse(localStorage.getItem("currentUser")).teamId
      }
      
      this.boardDisplayService.addBoard(this.makeBoard).subscribe();
      this.displayBoards();
      //this.Boards.push(this.makeBoard);

    }
  }

  delete(id: number){

    if(confirm("Are you sure to delete?")) {

    this.makeBoard = {
      bId: id,
      tv2Id: JSON.parse(localStorage.getItem("currentUser")).userId,
      bTotal: 0,
      bTitle: "",
      tv2Team: JSON.parse(localStorage.getItem("currentUser")).team
    }
    
    this.boardDisplayService.deleteBoard(this.makeBoard).subscribe();
    this.Boards = this.Boards.filter(item => item.bId !== id);

   }
  }
}