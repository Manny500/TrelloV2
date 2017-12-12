import { Injectable } from '@angular/core';
import { Headers, Http, RequestOptions } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import { Base64 } from 'js-base64';
import { TV2User } from './tv2user.interface';

@Injectable()
export class TV2UserService {

    url: string;
    urlEndpoint: string;
    headers: Headers;
    options: RequestOptions;
    creds: String;
    updatedUser: string;


    private POST_USER_URL = 'portal/login';
    private POST_AUTH_URL = 'auth-service/oauth/token';
    

    constructor(private http: Http) { }

    //OAuth2: get token; get user from database
    createTV2User(tv2user: TV2User): Observable<any> {

        this.headers = new Headers({
            "Content-Type": "application/json",
            'Authorization': 'Bearer ' + JSON.parse(localStorage.getItem('currentToken')).token
        });

        this.options = new RequestOptions({ headers: this.headers });

        return this.http.post(this.POST_USER_URL, tv2user, this.options);
    }
    //OAuth2 : authentication
    authenticate(user: TV2User): Observable<any> {

        this.headers = new Headers({
            "Content-Type": "application/x-www-form-urlencoded",
            "Authorization": "Basic " + Base64.encode("ClientId" + ':' + "secret")
        });

        this.options = new RequestOptions({ headers: this.headers });
        this.creds = 'grant_type=client_credentials';

        return this.http.post(this.POST_AUTH_URL, this.creds, this.options);
    }
}