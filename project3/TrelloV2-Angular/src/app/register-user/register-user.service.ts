import { Injectable } from '@angular/core';
import { Http, HttpModule, Response} from '@angular/http';
import 'rxjs/add/operator/map'
import { ProfileUser } from '../profile/profile-user';

@Injectable()
export class RegisterService {
  http: Http;
  returnProfileStatus:Object = [];
  private registerURL = 'profile/register';

  constructor(public _http: Http) { 
    this.http = _http;
  }

  postUpdate(user : ProfileUser){
    return this.http.post(this.registerURL, user, {
    })
    .map(res => res.json())
  }

  private handleError(error: any): Promise<any>{
    console.error('An error occurred', error);
    return Promise.reject(error.message || error);
  }

}
