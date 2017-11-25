import { Component, OnInit } from '@angular/core';
import { LaneDisplayService } from './lane-display.service';
import { Lane } from './lane-display.interface';


@Component({
  selector: 'app-scrum-board-view',
  templateUrl: './scrum-board-view.component.html',
  styleUrls: ['./scrum-board-view.component.css']
})
export class ScrumBoardViewComponent implements OnInit {

  Lanes: Lane[];
  currentBoardId : number;
  constructor(private laneDislayService: LaneDisplayService) { }

  ngOnInit() {
    this.displayLanes();
  }

  displayLanes(): void{
    //getting the current board's id (this value was set  in home.component.ts)
    this.currentBoardId = JSON.parse(localStorage.getItem("currentBoardId"));
    this.laneDislayService.getLanes().subscribe(result => {
      this.Lanes = result;
      localStorage.setItem('currentLanes', JSON.stringify(result))
    })
  }

}
