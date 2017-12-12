import { Component, OnInit } from '@angular/core';
import { TV2User } from './tv2user.interface';
import { TV2UserService } from './tv2user.service';
import { Router } from '@angular/router';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  tv2user: TV2User = { userId: null, userName: '', password: '', teamId: null, roleType: null, email: null, firstName: null, lastName: null }
  constructor(private tv2userService: TV2UserService, private router: Router) { }

  ngOnInit() {
  }

  onSubmit() {
    //do authentication first
    this.tv2userService.authenticate(this.tv2user).map(res => res.json()).subscribe(
      response => {
        //store current token in local storage
        localStorage.setItem('currentToken', JSON.stringify({ userName: this.tv2user.userName, token: response.access_token }));

        //store user in local storage
        this.tv2userService.createTV2User(this.tv2user).subscribe(
          response => {

            var t: TV2User;

            t= JSON.parse(response._body);
 
            if(t.firstName){
              localStorage.setItem('currentUser', response._body);
              this.router.navigateByUrl('/home');
            }else{
              this.router.navigateByUrl('/login');
            }
          })

      });
  }

}
