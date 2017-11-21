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

  tv2user: TV2User = {userId: null, username: '', password: '',team:null, roleType:null}
  constructor(private tv2userService: TV2UserService, private router: Router) {}

  ngOnInit() {
  }

  onSubmit(){
    this.tv2userService.createTV2User(this.tv2user).subscribe(
      response => {
        // if(response.roleType == 1){
          this.router.navigateByUrl('/home');
          localStorage.setItem('currentUser', JSON.stringify(response));
          
        // }
        // else if(response.roleType == 2){
        //   this.router.navigateByUrl('/user-home')
        //   localStorage.setItem('currentUser', JSON.stringify(response));
        // }
      });
  }

}
