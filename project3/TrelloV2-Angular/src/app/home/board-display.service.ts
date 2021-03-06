import {Injectable} from '@angular/core';
import { Headers, Http, RequestOptions } from '@angular/http';
import {Observable} from 'rxjs/Observable';

import {Board} from './board-display.interface';
import { ProfileUser } from '../profile/profile-user';

@Injectable()
export class BoardDisplayService{

    private masterBoardsUrl = 'http://18.217.187.31:8765/board-display/home';
    // private userBoardsUrl = 'board-display/user-home'
    private makeBoardsUrl = 'http://18.217.187.31:8765/board-update/addBoard';

    private deleteBoardsUrl = 'http://18.217.187.31:8765/board-update/deleteBoard';
  
    private circuitUrl = 'board-display/circuit';
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

    getMasterBoards(){
        return this.http.get(this.masterBoardsUrl,this.options)
        .map(response => <Board[]> response.json())
    }

    circuitTest(){
        return this.http.get(this.circuitUrl,this.options)
        .map(response => <ProfileUser[]> response.json())
    }


    addBoard(boardCreate: Board){
        return this.http.post(this.makeBoardsUrl, boardCreate, this.options)
        
    }

    deleteBoard(boardDelete: Board){
        return this.http.post(this.deleteBoardsUrl, boardDelete, this.options)
        
    }

    private handleError(error: any): Promise<any> {
        console.error('An error occurred', error); // for demo purposes only
        return Promise.reject(error.message || error);
      }

    
}