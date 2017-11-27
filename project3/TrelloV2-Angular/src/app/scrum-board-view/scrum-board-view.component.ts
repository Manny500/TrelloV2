import { Component, OnInit, Input } from '@angular/core';
import { LaneDisplayService } from './lane-display.service';
import { Lane } from './lane-display.interface';
import { Card } from './card-display.interface';
import { Task } from './task-display.interface';

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
  constructor(private laneDislayService: LaneDisplayService) { }

  Tasks: Task[];
  //two way binding
  @Input() cardCreate: Card;
  @Input() laneCreate: Lane;

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

  //
  public showCard = true;

  ngOnInit() {
    this.displayLanes();
    this.displayCards();

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

    })

    this.showCard = true;
  }

  //this is storing the id of the card that was clicked as well as displaying the tasks.
  displayTasks(cardId): void {
    localStorage.setItem("currentCardId", JSON.stringify(cardId));
    this.currentCardId = JSON.parse(localStorage.getItem("currentCardId"));
    this.laneDislayService.getTasks().subscribe(result => {
      this.Tasks = result;
    })
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

      this.laneDislayService.addCard(this.cardCreate).subscribe(
        data => console.log(this.responseStatus = data),
        err => console.log(err),
        () => console.log('request completed')
      )
      setTimeout(function () {
        this.displayCards();
      }.bind(this), 1000);

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
}