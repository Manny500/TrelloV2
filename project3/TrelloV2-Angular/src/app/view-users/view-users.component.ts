import { Component, OnInit } from '@angular/core';
import { ViewUsersService } from './view-users.service';
import { ProfileUser } from '../profile/profile-user';
import { Router } from '@angular/router';

@Component({
  selector: 'app-view-users',
  templateUrl: './view-users.component.html',
  styleUrls: ['./view-users.component.css']
})
export class ViewUsersComponent implements OnInit {

  myData: ProfileUser[];

  constructor(private viewUsersService: ViewUsersService, private router: Router) { }

  ngOnInit() {
    //view all users
    this.viewUsersService.getInfo().subscribe(result => {

      this.myData = result;

    });
  }

  gotoRequests(user: ProfileUser): void {
    this.viewUsersService.setPass(user.userId);
    this.router.navigate(['/request']);
  }

}