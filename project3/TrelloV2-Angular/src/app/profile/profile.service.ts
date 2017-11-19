import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import { ProfileUser } from './profile-user';

@Injectable()
export class ProfileService {

  private profileURL = 'profile/userInfo';
  private updateURL = 'profile/updateInfo';

  constructor(private http: Http) { }

  getInfo(){
      return this.http.get(this.profileURL)
      .map(response => <ProfileUser[]> response.json())
      .do(data => console.log(data))
      .catch(this.handleError);
  }

  private handleError(error: any): Promise<any>{
    console.error('An error occurred', error);
    return Promise.reject(error.message || error);
  }
  postUpdate(user : ProfileUser){
    console.log('sending post request to java');
    return this.http.post(this.updateURL, user, {})
    .map(res => res.json());
  }
}
