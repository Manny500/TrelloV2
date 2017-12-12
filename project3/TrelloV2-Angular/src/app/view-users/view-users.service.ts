import { Injectable } from '@angular/core';
import { Http, RequestOptions,Headers, Response} from '@angular/http';
import { ProfileUser } from '../profile/profile-user';


@Injectable()
export class ViewUsersService {

  passData;

  private profileURL = 'permissions/viewAll';

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

  getInfo(){
      return this.http.get(this.profileURL, this.options)
      .map(response => <ProfileUser[]> response.json())
      .catch(this.handleError);
  }

  private handleError(error: any): Promise<any>{
    console.error('An error occurred', error);
    return Promise.reject(error.message || error);
  }

  getPass(){
    return this.passData;

  }

  setPass(id){
    this.passData = id;
  }
}
