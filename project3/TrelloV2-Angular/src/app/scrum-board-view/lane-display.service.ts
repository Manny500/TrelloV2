import {Injectable} from '@angular/core';
import { Headers, Http, RequestOptions } from '@angular/http';
import {Observable} from 'rxjs/Observable';

import {Lane} from './lane-display.interface';
import { Card } from './card-display.interface';
import { Task } from './task-display.interface';
import { BurndownDto } from './burndown-dto.interface';
import { Base64 } from 'js-base64';


@Injectable()
export class LaneDisplayService{

    private boardsUrl = 'board-display/trello';
    private cardsUrl = 'board-display/showCard';
    private addCardsUrl = 'board-update/addCard';
    private deleteCardUrl = 'board-update/deleteCard';
    private addTaskUrl = 'board-update/addTask';
    private deleteTaskUrl = 'board-update/deleteTask';
    private switchLaneUrl = 'board-update/updateCard';
    private tasksUrl = 'board-display/showTask';
    private addLanesUrl = 'board-update/addLane';
    private burndownUpdateUrl = 'board-update/updateBurndown';


    url: string;
    urlEndpoint: string;
    creds: String;
    updatedUser: string;

    headers = new Headers({
        "Content-Type": "application/json",
        'Authorization': 'Bearer ' + JSON.parse(localStorage.getItem('currentToken')).token
    });

    options = new RequestOptions({ headers: this.headers });

    constructor(private http: Http){}

    getLanes(){
        let a = localStorage.getItem("currentBoards");
        a = JSON.parse(a);

        return this.http.get(this.boardsUrl, this.options)
        .map(response => <Lane[]> response.json())
        .do(data => console.log(data))
        .catch(this.handleError);
    }
      
    getCards(){
        return this.http.get(this.cardsUrl, this.options) 
        .map(response => <Card[]> response.json())
        .do(data => console.log(data))
        .catch(this.handleError)
    }

    getTasks(){
        return this.http.get(this.tasksUrl, this.options) 
        .map(response => <Task[]> response.json())
        .do(data => console.log(data))
        .catch(this.handleError)
    }

    postTask(taskCreate: Task){
        return this.http.post(this.addTaskUrl, taskCreate, this.options)
       
    }

    addCard(cardCreate : Card){
        return this.http.post(this.addCardsUrl, cardCreate, this.options)
    }

    switchLane(cardCreate : Card){
        return this.http.post(this.switchLaneUrl, cardCreate, this.options)
    }

    addLane(laneCreate : Lane){
        return this.http.post(this.addLanesUrl, laneCreate, this.options)
    }
    deleteCard(card: Card){
        return this.http.post(this.deleteCardUrl,card)
    }

    deleteTask(task: Task){
        return this.http.post(this.deleteTaskUrl,task,this.options)
    }

    updateBurndownChart(burndownCreate: BurndownDto){
        return this.http.post(this.burndownUpdateUrl, burndownCreate,this.options)
    }

    private handleError(error: any): Promise<any> {
        console.error('An error occurred', error); // for demo purposes only
        return Promise.reject(error.message || error);
    }
} 
