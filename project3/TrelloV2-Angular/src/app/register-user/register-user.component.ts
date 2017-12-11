import { Component, OnInit } from '@angular/core';
import { ProfileUser } from '../profile/profile-user';
import { Http } from '@angular/http';
import { RegisterService } from './register-user.service';

@Component({
  selector: 'app-register-user',
  templateUrl: './register-user.component.html',
  styleUrls: ['./register-user.component.css']
})
export class RegisterUserComponent implements OnInit {
  http: Http;
  private registerURL = 'profile/register';

  constructor(private registerService: RegisterService) { }

  ngOnInit() {
  }

  onClickSubmit(user : ProfileUser){

    //register new user, call postUpdate in service
    this.registerService.postUpdate(user).subscribe()
  }

  // submit(user : ProfileUser){
  //   return this.http.post(this.registerURL, user, {
  //   })
  //   .map(res => res.json())
  // }

}
