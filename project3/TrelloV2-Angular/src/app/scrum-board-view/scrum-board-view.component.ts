import { Component, OnInit, Input } from '@angular/core';
import { LaneDisplayService } from './lane-display.service';
import { Lane } from './lane-display.interface';
import { Card } from './card-display.interface';
import { Task } from './task-display.interface';
import { BurndownDto } from './burndown-dto.interface';
import { forEach } from '@angular/router/src/utils/collection';
import { Activity } from './activity-display.interface';
import { CardDto } from './card-dto.interface';
import { race } from 'rxjs/observable/race';

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

    //display lanes and cards by default
    this.displayLanes();
    this.displayCards();

  }

  //add card into array
  push(card: Card){
    this.boardCards.push(card);
  }

  helloWorld(): string{
    return 'Hello world!';
  }


  //display lanes
  displayLanes(): void {
    //getting the current board's id (this value was set  in home.component.ts)
    this.currentBoardId = JSON.parse(localStorage.getItem("currentBoardId"));
    
    //call getLanes() in service
    this.laneDislayService.getLanes().subscribe(result => {
      this.Lanes = result;
      this.Lanes = this.Lanes.filter(item => item.bId == this.currentBoardId);
      //store current lane into local storage
      localStorage.setItem('currentLanes', JSON.stringify(result))
    })
    this.showCard = true;
  }

  //display cards
  displayCards(): void {
    this.Cards = null;

    //call getCards() in service
    this.laneDislayService.getCards().subscribe(result => {
      this.Cards = result;
      this.boardCards = this.Cards;
    })
  
    this.showCard = true;
  }

  //display tasks
  displayTasks(cardId): void {
    
    //store current cardId to local storage
    localStorage.setItem("currentCardId", JSON.stringify(cardId));
    this.currentCardId = JSON.parse(localStorage.getItem("currentCardId"));
    this.laneDislayService.getTasks().subscribe(result => {
      this.Tasks = result;
    })
  }

  //display activities
  displayActivity(): void {
    this.Activities = null;

    //call getActivity()
    this.laneDislayService.getActivity().subscribe(result => {
      this.Activities = result;
      this.Activities = this.Activities.filter(item => item.bId == this.currentBoardId);
    })
    
  }

  //add a task
  addTask(): void{

    //activity info
    this.activityToService('Added a Task', this.inputedTaskInfo);

    this.taskCreate = {
      taskId: 0,
      cardId : this.currentCardId,
      status: 0,
      info: this.inputedTaskInfo
    }

    //call postTask() in service
    this.laneDislayService.postTask(this.taskCreate).subscribe();

    //timeout: make sure data is updated before retrieved
    setTimeout(function () { 
      
     this.displayTasks(JSON.parse(localStorage.getItem("currentCardId")));
      
   }.bind(this), 3000); 

    this.inputedTaskInfo="";
  }

  //done button for add card or lane
  done(condition: number) {

    if (condition == 1) { //add Card
      //activity info
      this.activityToService('Added a Card', this.cTitle);

      this.cardCreate = {
        cId: 0, //sql sequece will change this to appropriate number
        lId: this.currentLaneId,
        cVerify: 0,
        cWorth: this.cWorth,
        cTitle: this.cTitle,
        cDescription: this.cDescription
      }

      this.boardCards.push(this.cardCreate);
      //call addCard() in service
      this.laneDislayService.addCard(this.cardCreate).subscribe();
     
      //timeout: make sure data is updated before retrieved
      setTimeout(function () { 
        
       this.displayCards(); 
        
     }.bind(this), 1000); 

      this.cWorth = null;
      this.cTitle = "";
      this.cDescription = "";

    }

    if (condition == 2) { //add Lane
      //activity info
      this.activityToService('Added a Lane', this.laneTitle);

      // this.showCard = false;
      this.laneCreate = {
        laneId: 0,
        bId: this.currentBoardId,
        laneTitle: this.laneTitle
      }

      // this.Lanes.push(this.laneCreate)
      //call addLane in service
      this.laneDislayService.addLane(this.laneCreate).subscribe();
      
      setTimeout(function () { 
        
       this.displayLanes(); 
        
     }.bind(this), 1000); 
    }

    this.burndownCreate = {
      bId: this.currentBoardId,
      cards: this.boardCards,
      lanes: this.Lanes
    }


    //call updateBurndownChart() in service
    this.laneDislayService.updateBurndownChart(this.burndownCreate).subscribe();
    
  }

  updatecurrentLane(lId: number) {
    this.currentLaneId = lId;
  }

  removeCardFromArray(id: number){
    this.boardCards = this.boardCards.filter(item => item.cId !== id);
  }

  //move cards between lanes
  switchLane(currentCard: Card, lane: number) {
    const currentLane: Lane = this.Lanes[lane];
    currentCard.lId = currentLane.laneId;

    //call switchLane() in service
    this.laneDislayService.switchLane(currentCard).subscribe();
  
  }

  //remove a lane
  removeLane(laneToRemove, lId : number, laneTitle: string){

    if(confirm("Are you sure to delete \" "+ laneTitle +"\" ?" 
              +"\nThe cards will also be deleted at the same time!")) {
               
      //activity info
      this.activityToService('Removed a Lane', laneTitle);
      //call deleteLane() in service
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

  //card verification
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
    
    //call verifyCard() in service
    this.laneDislayService.verifyCard(this.carddto).subscribe();
    
    var index = this.Cards.findIndex(item => item.cId == this.carddto.cId);
    
    this.Cards[index].cVerify = this.carddto.cVerify;

    
    }
    
  }
 
  //remove a card
  removeCard(currentCard: Card, cTitle: string){
    this.activityToService('Removed a Card', cTitle);
      //call deleteCard()
      this.laneDislayService.deleteCard(currentCard).subscribe();

      //remove the cards = cid from array
      this.Cards = this.Cards.filter(item => item.cId !== currentCard.cId);

      this.burndownCreate = {
        bId: this.currentBoardId,
        cards: this.Cards,
        lanes: this.Lanes
      }
      //call updateBurndownChart()
      this.laneDislayService.updateBurndownChart(this.burndownCreate).subscribe();
  }

  //remove a task
  removeTask(taskToRemove, info){
    //activity info
    this.activityToService('Removed a Task', info);
    this.laneDislayService.deleteTask(taskToRemove).subscribe();
    //remove the task from array
    this.Tasks = this.Tasks.filter(item => item !== taskToRemove);

  }

  // check/uncheck a task
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
    //call postTask()
    this.laneDislayService.postTask(this.taskCreate).subscribe();
    
  }

  //send activity info to service
  activityToService(act: string, name: string){
    
    this.activity ={
      bId: this.currentBoardId,
      firstName: JSON.parse(localStorage.getItem("currentUser")).firstName,
      action: act+': " '+name+' "'
      }
    //call sendActivity()
    this.laneDislayService.sendActivity(this.activity).subscribe();
  }
}