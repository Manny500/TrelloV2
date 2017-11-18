import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';

import {TV2User} from './tv2user.interface';

@Injectable()
export class TV2UserService{
    private static readonly POST_USER_URL = 'portal/login';
    private headers = new HttpHeaders({'Content-Type': 'application/json'});

    constructor(private http: HttpClient){}

    createTV2User(tv2user: TV2User): Observable<any> {
        return this.http.post(TV2UserService.POST_USER_URL, tv2user, {headers: this.headers});
    }
}