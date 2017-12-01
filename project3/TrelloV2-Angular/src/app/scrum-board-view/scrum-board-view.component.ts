import { Component, OnInit, Input } from '@angular/core';
import { LaneDisplayService } from './lane-display.service';
import { Lane } from './lane-display.interface';
import { Card } from './card-display.interface';
import { Task } from './task-display.interface';
import { BurndownDto } from './burndown-dto.interface';
import { forEach } from '@angular/router/src/utils/collection';
import { Activity } from './activity-display.interface';
import { CardDto } from './card-dto.interface';

@Component({
  selector: 'app-scrum-board-view',
  templateUrl: './scrum-board-view.component.html',
  styleUrls: ['./scrum-board-view.component.css']
})

export class ScrumBoardViewComponent implements OnInit {

  
  currentBoardId: number;
  currentCardId: number;
  currentLaneId: number;
  
  Lanes: Lane[];
  Cards: Card[];
  Tasks: Task[];
  Activities: Activity[];
  
  showTaskBtns: boolean;
  boardCards: Card[] = [];

  carddto: CardDto = {cId: 0, cVerify: 0, cWorth: 0, bId: 0, bTotal: 0, lId: 0, cTitle: "", cDescription: "", tv2Id: 0, bTitle: "", tv2Team: 0};

  activity: Activity;
 
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

  //hide/show cards
  public showCard = true;

  //activity

  taskStatus: number;

  verifyShow: boolean;

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
      this.Lanes = this.Lanes.filter(item => item.bId == this.currentBoardId);
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

  displayTasks(cardId): void {
    
    localStorage.setItem("currentCardId", JSON.stringify(cardId));
    this.currentCardId = JSON.parse(localStorage.getItem("currentCardId"));
    this.laneDislayService.getTasks().subscribe(result => {
      this.Tasks = result;
    })
  }

  displayActivity(): void {
    this.Activities = null;

    this.laneDislayService.getActivity().subscribe(result => {
      this.Activities = result;
      this.Activities = this.Activities.filter(item => item.bId == this.currentBoardId);
    })
    console.log('displayActivity++');
  }


  addTask(): void{

    this.activityToService('Added a Task', this.inputedTaskInfo);

    this.taskCreate = {
      taskId: 0,
      cardId : this.currentCardId,
      status: 0,
      info: this.inputedTaskInfo
    }

    this.laneDislayService.postTask(this.taskCreate).subscribe();

    this.Tasks.push(this.taskCreate)

    this.inputedTaskInfo="";
  }

  done(condition: number) {

    if (condition == 1) { //add Card
      
      this.activityToService('Added a Card', this.cTitle);

      this.cardCreate = {
        cId: 0, //sql sequece will change this to appropriate number
        lId: this.currentLaneId,
        cVerify: 0,
        cWorth: this.cWorth,
        cTitle: this.cTitle,
        cDescription: this.cDescription
      }

      this.Cards.push(this.cardCreate)
      this.laneDislayService.addCard(this.cardCreate).subscribe();

      this.cWorth = null;
      this.cTitle = "";
      this.cDescription = "";

    }

    if (condition == 2) { //add Lane
      
      this.activityToService('Added a Lane', this.laneTitle);

      // this.showCard = false;
      this.laneCreate = {
        laneId: 0,
        bId: this.currentBoardId,
        laneTitle: this.laneTitle
      }

      this.Lanes.push(this.laneCreate)
      
      this.laneDislayService.addLane(this.laneCreate).subscribe();
      
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

  removeCardFromArray(id: number){
    this.boardCards = this.boardCards.filter(item => item.cId !== id);
  }

  switchLane(currentCard: Card, lane: number) {
    const currentLane: Lane = this.Lanes[lane];
    currentCard.lId = currentLane.laneId;

    this.laneDislayService.switchLane(currentCard).subscribe();
  
  }

  removeLane(laneToRemove, lId : number, laneTitle: string){

    if(confirm("Are you sure to delete \" "+ laneTitle +"\" ?" 
              +"\nThe cards will also be deleted at the same time!")) {
               
      this.activityToService('Removed a Lane', laneTitle);

      this.laneDislayService.deleteLane(laneToRemove).subscribe();

      this.Lanes = this.Lanes.filter(item => item.laneId !== lId);
    }

   
  }

  currentCard(card){
    localStorage.setItem("currentCard", JSON.stringify(card));
    if(card.cVerify == 0){
      this.verifyShow = true;
    }else{
      this.verifyShow = false;
    }
  }

  verify(){
    if(JSON.parse(localStorage.getItem("currentCard")).cVerify == 0){
    this.carddto.bId = JSON.parse(localStorage.getItem("currentBoard")).bId;
    this.carddto.cId = JSON.parse(localStorage.getItem("currentCard")).cId;
    this.carddto.cVerify = 1;
    this.carddto.cWorth = JSON.parse(localStorage.getItem("currentCard")).cWorth;
    this.carddto.bTotal = JSON.parse(localStorage.getItem("currentBoard")).bTotal;
    this.carddto.cDescription = JSON.parse(localStorage.getItem("currentCard")).cDescription;
    this.carddto.cTitle = JSON.parse(localStorage.getItem("currentCard")).cTitle;
    this.carddto.lId = JSON.parse(localStorage.getItem("currentCard")).lId;
    this.carddto.tv2Id = JSON.parse(localStorage.getItem("currentBoard")).tv2Id;
    this.carddto.bTitle = JSON.parse(localStorage.getItem("currentBoard")).bTitle;
    this.carddto.tv2Team = JSON.parse(localStorage.getItem("currentBoard")).tv2Team;
    
    
    this.laneDislayService.verifyCard(this.carddto).subscribe();
    
    var index = this.Cards.findIndex(item => item.cId == this.carddto.cId);
    
    this.Cards[index].cVerify = this.carddto.cVerify;

    
    }
    
  }
 
  removeCard(currentCard: Card, cTitle: string){
    this.activityToService('Removed a Card', cTitle);

      this.laneDislayService.deleteCard(currentCard).subscribe();

      this.Cards = this.Cards.filter(item => item.cId !== currentCard.cId);

      this.burndownCreate = {
        bId: this.currentBoardId,
        cards: this.boardCards,
        lanes: this.Lanes
      }
  
      this.laneDislayService.updateBurndownChart(this.burndownCreate).subscribe();
  }

  removeTask(taskToRemove, info){

    this.activityToService('Removed a Task', info);
    this.laneDislayService.deleteTask(taskToRemove).subscribe();

    this.Tasks = this.Tasks.filter(item => item !== taskToRemove);

  }

  toggleEditable(event, currentTaskId, currentTaskInfo){
    if(event.target.checked){
      this.taskStatus = 1;

    }else{
      this.taskStatus = 0;
    }

    this.taskCreate = {
      taskId: currentTaskId,
      cardId : this.currentCardId,
      status: this.taskStatus,
      info: currentTaskInfo
    }

    this.laneDislayService.postTask(this.taskCreate).subscribe();
    
  }

  activityToService(act: string, name: string){
    
    this.activity ={
      bId: this.currentBoardId,
      firstName: JSON.parse(localStorage.getItem("currentUser")).firstName,
      action: act+': " '+name+' "'
      }

    this.laneDislayService.sendActivity(this.activity).subscribe();
  }
}