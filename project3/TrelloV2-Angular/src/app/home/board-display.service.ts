import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';

import {Board} from './board-display.interface';
import { Http } from '@angular/http';

@Injectable()
export class BoardDisplayService{
    private masterBoardsUrl = 'board-display/home';
    // private userBoardsUrl = 'board-display/user-home'
    private makeBoardsUrl = 'board-display/updateBoard2';

    constructor(private http: Http){ }

    getMasterBoards(){
        return this.http.get(this.masterBoardsUrl)
        .map(response => <Board[]> response.json())
        .do(data => console.log(data))
        .catch(this.handleError);
    }


    addBoard(boardCreate: Board){
        return this.http.post(this.makeBoardsUrl, boardCreate, {
        })
        .map(res => res.json())
        
    }
    // getUserBoards(){
    //     return this.http.get(this.userBoardsUrl)
    //     .map(response => <Board[]> response.json())
    //     .do(data => console.log(data))
    //     .catch(this.handleError);
    // }

    private handleError(error: any): Promise<any> {
        console.error('An error occurred', error); // for demo purposes only
        return Promise.reject(error.message || error);
      }

    
}