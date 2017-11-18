import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import { ProfileUser } from './profile-user';

@Injectable()
export class ProfileService {

  private profileURL = 'profile/userInfo';

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
}
