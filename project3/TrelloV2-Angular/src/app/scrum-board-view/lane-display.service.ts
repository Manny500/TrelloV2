import {Injectable} from '@angular/core';
import { Headers, Http, RequestOptions } from '@angular/http';
import {Observable} from 'rxjs/Observable';

import {Lane} from './lane-display.interface';
import { Card } from './card-display.interface';
import { Task } from './task-display.interface';
import { BurndownDto } from './burndown-dto.interface';
import { Base64 } from 'js-base64';
import { Activity } from './activity-display.interface';
import { CardDto } from './card-dto.interface';



@Injectable()
export class LaneDisplayService{


    private boardsUrl = 'http://18.217.187.31:8765/board-display/trello';
    private cardsUrl = 'http://18.217.187.31:8765/board-display/showCard';
    private addCardsUrl = 'http://18.217.187.31:8765/board-update/addCard';
    private deleteCardUrl = 'http://18.217.187.31:8765/board-update/deleteCard';
    private addTaskUrl = 'http://18.217.187.31:8765/board-update/addTask';
    private deleteTaskUrl = 'http://18.217.187.31:8765/board-update/deleteTask';
    private switchLaneUrl = 'http://18.217.187.31:8765/board-update/updateCard';
    private tasksUrl = 'http://18.217.187.31:8765/board-display/showTask';
    private addLanesUrl = 'http://18.217.187.31:8765/board-update/addLane';
    private deleteLaneUrl = 'http://18.217.187.31:8765/board-update/deleteLane';
    private burndownUpdateUrl = 'http://18.217.187.31:8765/board-update/updateBurndown';
    private activityUrl = 'http://18.217.187.31:8765/permissions/sendActivity';
    private getActivityUrl = 'http://18.217.187.31:8765/permissions/getActivity';
    private verifyCardsUrl = 'http://18.217.187.31:8765/board-update/verifyCard';

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

    //get lanes from backend
    getLanes(){
        let a = localStorage.getItem("currentBoards");
        a = JSON.parse(a);

        return this.http.get(this.boardsUrl, this.options)
        .map(response => <Lane[]> response.json())
    }
    
    //get cards from backend
    getCards(){
        return this.http.get(this.cardsUrl, this.options) 
        .map(response => <Card[]> response.json())
        .catch(this.handleError)
    }

    //get tasks from backend
    getTasks(){
        return this.http.get(this.tasksUrl, this.options) 
        .map(response => <Task[]> response.json())
        .catch(this.handleError)
    }

    //add task to backend
    postTask(taskCreate: Task){
        return this.http.post(this.addTaskUrl, taskCreate, this.options)
        .catch(this.handleError)
    }

    //update verification status to backend
    verifyCard(cardVerify : CardDto){
        return this.http.post(this.verifyCardsUrl, cardVerify, this.options)
        .catch(this.handleError)
    }

    //add card to backend
    addCard(cardCreate : Card){
        return this.http.post(this.addCardsUrl, cardCreate, this.options)
        .catch(this.handleError)
    }

    //update card position(lane) to backend
    switchLane(cardCreate : Card){
        return this.http.post(this.switchLaneUrl, cardCreate, this.options)
        .catch(this.handleError)
    }

    //add lane
    addLane(laneCreate : Lane){
        return this.http.post(this.addLanesUrl, laneCreate, this.options)
        .catch(this.handleError)
    }

    //delete lane
    deleteLane(lane: Lane){
        return this.http.post(this.deleteLaneUrl,lane, this.options)
        .catch(this.handleError)
    }

    //delete card
    deleteCard(card: Card){
        return this.http.post(this.deleteCardUrl,card, this.options)
        .catch(this.handleError)
    }

    //delete task
    deleteTask(task: Task){
        return this.http.post(this.deleteTaskUrl,task,this.options)
        .catch(this.handleError)
    }

    //update burndown chart
    updateBurndownChart(burndownCreate: BurndownDto){
        return this.http.post(this.burndownUpdateUrl, burndownCreate,this.options)
        .catch(this.handleError)
    }

    //add an activity 
    sendActivity(activity : Activity){
        return this.http.post(this.activityUrl, activity,this.options)
        .catch(this.handleError)
    }

    //retrieve activities
    getActivity(){
        return this.http.get(this.getActivityUrl, this.options) 
        .map(response => <Activity[]> response.json())
        .catch(this.handleError)
    }

    private handleError(error: any): Promise<any> {
        console.error('An error occurred', error); // for demo purposes only
        return Promise.reject(error.message || error);
    }
} 
