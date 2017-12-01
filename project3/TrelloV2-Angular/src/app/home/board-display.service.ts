import {Injectable} from '@angular/core';
import { Headers, Http, RequestOptions } from '@angular/http';
import {Observable} from 'rxjs/Observable';

import {Board} from './board-display.interface';

@Injectable()
export class BoardDisplayService{
    private masterBoardsUrl = 'board-display/home';
    // private userBoardsUrl = 'board-display/user-home'
    private makeBoardsUrl = 'board-update/addBoard';

    private deleteBoardsUrl = 'board-update/deleteBoard';

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