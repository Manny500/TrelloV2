import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';

import {Lane} from './lane-display.interface';
import { Http } from '@angular/http';
import { Card } from './card-display.interface';

@Injectable()
export class LaneDisplayService{
    private boardsUrl = 'board-display/trello';
    private cardsUrl = 'board-display/showCard';
    private addCardsUrl = 'board-display/addCard';

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

    addCard(cardCreate : Card){
        return this.http.post(this.addCardsUrl, cardCreate, {
        })
        .map(res => res.json())
    }

    private handleError(error: any): Promise<any> {
        console.error('An error occurred', error); // for demo purposes only
        return Promise.reject(error.message || error);
    }
} 
