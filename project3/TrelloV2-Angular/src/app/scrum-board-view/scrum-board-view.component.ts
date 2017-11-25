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
  currentBoardId : number;

  Cards: Card[];
  constructor(private laneDislayService: LaneDisplayService) { }
  //
  @Input() cardCreate: Card;
  responseStatus:Object= [];
  status:boolean;
  //
  lId: number;
  cTitle: string;
  cVerify: number;
  cWorth: number;
  cDescription: string;
  //

  ngOnInit() {
    this.displayLanes();
    this.displayCards();
  }

  displayLanes(): void{
    //getting the current board's id (this value was set  in home.component.ts)
    this.currentBoardId = JSON.parse(localStorage.getItem("currentBoardId"));
    this.laneDislayService.getLanes().subscribe(result => {
      this.Lanes = result;
      console.log("this.Lanes = " + this.Lanes)
      localStorage.setItem('currentLanes', JSON.stringify(result))
    })
  }

  displayCards(): void{
    console.log('displayCards()');    
    this.laneDislayService.getCards().subscribe(result => {
      this.Cards = result;
      console.log("this.Cards= " + this.Cards)
      //localStorage.setItem('currentLanes', JSON.stringify(result))
    })
  }

  done(){
    console.log('create card: done()');
    console.log(this.lId);
    console.log(this.cTitle);
    console.log(this.cDescription); 
    this.cardCreate ={
      cId: 0, //sql sequece will change this
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
  }

}
