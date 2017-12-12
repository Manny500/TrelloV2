import { Injectable } from '@angular/core';
import { Headers,HttpModule, Response, Http, RequestOptions } from '@angular/http';
import 'rxjs/add/operator/map'
import { ProfileUser } from '../profile/profile-user';

@Injectable()
export class RegisterService {
  returnProfileStatus:Object = [];

  private registerURL = 'profile/register';

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

  //register new user to backend
  postUpdate(user : ProfileUser){
    return this.http.post(this.registerURL, user, this.options)
    .map(res => res.json())
  }

  private handleError(error: any): Promise<any>{
    console.error('An error occurred', error);
    return Promise.reject(error.message || error);
  }

}
