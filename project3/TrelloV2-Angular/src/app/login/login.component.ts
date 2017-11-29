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

  tv2user: TV2User = {userId: null, userName: '', password: '', teamId: null, roleType: null, email: null, firstName: null, lastName: null}
  constructor(private tv2userService: TV2UserService, private router: Router) {}

  ngOnInit() {
  }

  onSubmit(){
    this.tv2userService.createTV2User(this.tv2user).subscribe(
      response => {
          this.router.navigateByUrl('/home');
          localStorage.setItem('currentUser', JSON.stringify(response));
      });
  }

}
