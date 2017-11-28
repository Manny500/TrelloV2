import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';

import {Lane} from './lane-display.interface';
import { Http } from '@angular/http';
import { Card } from './card-display.interface';
import { Task } from './task-display.interface';

@Injectable()
export class LaneDisplayService{

    private boardsUrl = 'board-display/trello';
    private cardsUrl = 'board-display/showCard';
    private addCardsUrl = 'board-display/addCard';
    private addTaskUrl = 'board-display/addTask';
    private switchLaneUrl = 'board-display/updateCard';
    private tasksUrl = 'board-display/showTask';
    //private addLanessUrl = 'board-update/addLane'  <= change to this once we know how to sync database
    private addLanesUrl = 'board-display/addLane';


    constructor(private http: Http){}

    getLanes(){
        let a = localStorage.getItem("currentBoards");
        a = JSON.parse(a);
        console.log(a);
        return this.http.get(this.boardsUrl)
        .map(response => <Lane[]> response.json())
        .do(data => console.log(data))
        .catch(this.handleError);
    }
      
    getCards(){
        console.log('inside getCards()');
        return this.http.get(this.cardsUrl) 
        .map(response => <Card[]> response.json())
        .do(data => console.log(data))
        .catch(this.handleError)
    }

    getTasks(){
        console.log('inside getTasks()');
        return this.http.get(this.tasksUrl) 
        .map(response => <Task[]> response.json())
        .do(data => console.log(data))
        .catch(this.handleError)
    }

    postTask(taskCreate: Task){
        return this.http.post(this.addTaskUrl, taskCreate, {
        })
        .map(res => res.json())
    }

    addCard(cardCreate : Card){
        return this.http.post(this.addCardsUrl, cardCreate, {
        })
        .map(res => res.json())
    }

    switchLane(cardCreate : Card){
        return this.http.post(this.switchLaneUrl, cardCreate, {
        })
        .map(res => res.json())
    }

    addLane(laneCreate : Lane){
        return this.http.post(this.addLanesUrl, laneCreate, {
        })
        .map(res => res.json())
    }

    private handleError(error: any): Promise<any> {
        console.error('An error occurred', error); // for demo purposes only
        return Promise.reject(error.message || error);
    }
} 
