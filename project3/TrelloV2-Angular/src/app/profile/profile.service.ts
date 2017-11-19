import { Injectable } from '@angular/core';
import { Http, HttpModule, Response} from '@angular/http';
import 'rxjs/add/operator/map'
import { ProfileUser } from './profile-user'

@Injectable()
export class ProfileService {
  http: Http;
  returnProfileStatus:Object = [];
  private profileURL = 'profile/userInfo';
  private updateURL = 'profile/updateInfo';

  constructor(public _http: Http) { 
    this.http = _http;
  }

  getInfo(){
      return this.http.get(this.profileURL)
      .map(response => <ProfileUser[]> response.json())
      .do(data => console.log(data))
      .catch(this.handleError);
  }

  postUpdate(user : ProfileUser){
    console.log('sending post request to java');
    console.log(user.firstName);
    return this.http.post(this.updateURL, user, {
    })
    .map(res => res.json())
  }

  private handleError(error: any): Promise<any>{
    console.error('An error occurred', error);
    return Promise.reject(error.message || error);
  }

}
