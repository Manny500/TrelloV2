import { Component, OnInit } from '@angular/core';
import { ProfileUser } from '../profile/profile-user';
import { Http } from '@angular/http';

@Component({
  selector: 'app-register-user',
  templateUrl: './register-user.component.html',
  styleUrls: ['./register-user.component.css']
})
export class RegisterUserComponent implements OnInit {
  http: Http;
  private registerURL = 'profile/register';

  constructor() { }

  ngOnInit() {
  }

  submit(user : ProfileUser){
    return this.http.post(this.registerURL, user, {
    })
    .map(res => res.json())
  }

}
