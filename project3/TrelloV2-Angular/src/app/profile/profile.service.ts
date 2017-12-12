import { Injectable } from '@angular/core';
import { Http, RequestOptions,Headers, Response} from '@angular/http';
import 'rxjs/add/operator/map'
import { ProfileUser } from './profile-user'

@Injectable()
export class ProfileService {
  returnProfileStatus:Object = [];

  private profileURL = 'profile/userInfo';
  private updateURL = 'profile/updateInfo';
  
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

  //get data from backend
  getInfo(user: ProfileUser){
      return this.http.post(this.profileURL, user, this.options)
      .map(response => response.json())
    }

  //update data to backend
  postUpdate(user : ProfileUser){
    return this.http.post(this.updateURL, user, this.options)
    .map(response => response.json())
  }

  private handleError(error: any): Promise<any>{
    console.error('An error occurred', error);
    return Promise.reject(error.message || error);
  }

}