import { Component, OnInit, Input } from '@angular/core';
import { LaneDisplayService } from './lane-display.service';
import { Lane } from './lane-display.interface';
import { Card } from './card-display.interface';


@Component({
  selector: 'app-scrum-board-view',
  templateUrl: './scrum-board-view.component.html',
  styleUrls: ['./scrum-board-view.component.css']
})
export class ScrumBoardViewComponent implements OnInit {

  Lanes: Lane[];
  currentBoardId: number;

  Cards: Card[];
  constructor(private laneDislayService: LaneDisplayService) { }
  //
  @Input() cardCreate: Card;
  responseStatus: Object = [];
  status: boolean;
  //addCard
  lId: number;
  cTitle: string;
  cVerify: number;
  cWorth: number;
  cDescription: string;
  //addLane
  lTitle: string;
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
      console.log("this.Lanes = " + this.Lanes)
      localStorage.setItem('currentLanes', JSON.stringify(result))
    })
  }

  displayCards(): void {
    this.Cards = null;
    console.log('displayCards()');
    console.log('showcards? ' + this.showCard);
    this.laneDislayService.getCards().subscribe(result => {
      this.Cards = result;
      console.log("this.Cards= " + this.Cards)
      //localStorage.setItem('currentLanes', JSON.stringify(result))
    })

    this.showCard = true;
  }

  done(condition:number) {
    if (condition == 1) {
      console.log('create card: done()');
      this.showCard = false;
      this.cardCreate = {
        cId: 0, //sql sequece will change this to appropriate number
        lId: this.lId,
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
    console.log('done end');
    setTimeout(function () {
      this.displayCards();
    }.bind(this), 1000);

    }
    if(condition == 2){
      console.log('create Lane: done()');
      console.log(this.lTitle);
      this.showCard = false;
      
    }
  }
}
