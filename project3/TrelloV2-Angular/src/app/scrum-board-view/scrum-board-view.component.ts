import { Component, OnInit, Input } from '@angular/core';
import { LaneDisplayService } from './lane-display.service';
import { Lane } from './lane-display.interface';
import { Card } from './card-display.interface';
import { Task } from './task-display.interface';
import { BurndownDto } from './burndown-dto.interface';
import { forEach } from '@angular/router/src/utils/collection';

@Component({
  selector: 'app-scrum-board-view',
  templateUrl: './scrum-board-view.component.html',
  styleUrls: ['./scrum-board-view.component.css']
})

export class ScrumBoardViewComponent implements OnInit {

  Lanes: Lane[];
  currentBoardId: number;
  currentCardId: number;
  currentLaneId: number;

  Cards: Card[];
  Tasks: Task[];

  showTaskBtns: boolean;
  boardCards: Card[] = [];
 
  constructor(private laneDislayService: LaneDisplayService) { }

  
  //two way binding
  @Input() taskCreate: Task;
  @Input() cardCreate: Card;
  @Input() laneCreate: Lane;
  @Input() burndownCreate: BurndownDto;

  responseStatus: Object = [];
  status: boolean;

  //addCard
  lId: number;
  cTitle: string;
  cVerify: number;
  cWorth: number;
  cDescription: string;

  //addLane
  laneTitle: string;

  //addTask
  inputedTaskInfo: string;

  //
  public showCard = true;

  ngOnInit() {
    this.displayLanes();
    this.displayCards();
    

  }

  push(card: Card){
    this.boardCards.push(card);
  }

  displayLanes(): void {
    //getting the current board's id (this value was set  in home.component.ts)
    this.currentBoardId = JSON.parse(localStorage.getItem("currentBoardId"));
    this.laneDislayService.getLanes().subscribe(result => {
      this.Lanes = result;
      localStorage.setItem('currentLanes', JSON.stringify(result))
    })
    this.showCard = true;
  }

  displayCards(): void {
    this.Cards = null;

    this.laneDislayService.getCards().subscribe(result => {
      this.Cards = result;
      this.boardCards = this.Cards;
      

    })

    this.showCard = true;
  }

  //this handles storing the id of the card that was clicked as well as displaying the tasks.
  displayTasks(cardId): void {
    
    localStorage.setItem("currentCardId", JSON.stringify(cardId));
    this.currentCardId = JSON.parse(localStorage.getItem("currentCardId"));
    this.laneDislayService.getTasks().subscribe(result => {
      this.Tasks = result;
    })
  }

  addTask(): void{
    this.taskCreate = {
      taskId: 0,
      cardId : this.currentCardId,
      status: 0,
      info: this.inputedTaskInfo
    }
    this.laneDislayService.postTask(this.taskCreate).subscribe(
      data => console.log(this.responseStatus = data),
      err => console.log(err),
      () => console.log('request completed')
    )
    setTimeout(function () {
      this.displayTasks(this.currentCardId);
    }.bind(this), 1000);

    this.inputedTaskInfo="";
  }

  done(condition: number) {
    if (condition == 1) {
      this.showCard = false;
      this.cardCreate = {
        cId: 0, //sql sequece will change this to appropriate number
        lId: this.currentLaneId,
        cVerify: 0,
        cWorth: this.cWorth,
        cTitle: this.cTitle,
        cDescription: this.cDescription
      }

      this.boardCards.push(this.cardCreate);
      this.laneDislayService.addCard(this.cardCreate).subscribe(
        data => console.log(this.responseStatus = data),
        err => console.log(err),
        () => console.log('request completed')
      )

      setTimeout(function () {
        this.displayCards();
      }.bind(this), 1000);

      this.cWorth = null;
      this.cTitle = "";
      this.cDescription = "";

    }

    if (condition == 2) {

      this.showCard = false;
      this.laneCreate = {
        laneId: 0,
        bId: this.currentBoardId,
        laneTitle: this.laneTitle
      }
      this.laneDislayService.addLane(this.laneCreate).subscribe(
        data => console.log(this.responseStatus = data),
        err => console.log(err),
        () => console.log('request completed')
      )

      
      setTimeout(function () {
        this.displayLanes();
      }.bind(this), 1000);
    }

    this.burndownCreate = {
      bId: this.currentBoardId,
      cards: this.boardCards,
      lanes: this.Lanes
    }

    this.laneDislayService.updateBurndownChart(this.burndownCreate).subscribe();
    
  }

  updatecurrentLane(lId: number) {
    this.currentLaneId = lId;
  }

  switchLane(currentCard: Card, lane: number) {
    const currentLane: Lane = this.Lanes[lane];
    currentCard.lId = currentLane.laneId;

    this.laneDislayService.switchLane(currentCard).subscribe(
      data => console.log(this.responseStatus = data),
      err => console.log(err),
      () => console.log('request completed')
    )
  
  }

  removeCardFromArray(id: number) {
    this.boardCards = this.boardCards.filter(item => item.cId !== id);
  }

  removeCard(currentCard: Card){
      this.laneDislayService.deleteCard(currentCard).subscribe(
        data => console.log(this.responseStatus = data),
        err => console.log(err),
        () => console.log('delete Card request completed')
      );

      this.Cards = this.Cards.filter(item => item.cId !== currentCard.cId);
     

      // setTimeout(function () {
      //   this.displayCards();
      // }.bind(this), 1000);


      this.burndownCreate = {
        bId: this.currentBoardId,
        cards: this.boardCards,
        lanes: this.Lanes
      }
  
      this.laneDislayService.updateBurndownChart(this.burndownCreate).subscribe();
  }

  removeTask(taskToRemove){
    this.laneDislayService.deleteTask(taskToRemove).subscribe(
      data => console.log(this.responseStatus = data),
      err => console.log(err),
      () => console.log('delete Task request completed')
    );
    setTimeout(function () {
      this.displayTasks(taskToRemove.cardId);
    }.bind(this), 1000);
    
  }
}