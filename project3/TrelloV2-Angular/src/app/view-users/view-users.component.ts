import { Component, OnInit } from '@angular/core';
import { ViewUsersService } from './view-users.service';
import { ProfileUser } from '../profile/profile-user';

@Component({
  selector: 'app-view-users',
  templateUrl: './view-users.component.html',
  styleUrls: ['./view-users.component.css']
})
export class ViewUsersComponent implements OnInit {

  myData: ProfileUser[];

  constructor(private viewUsersService: ViewUsersService) { }

  ngOnInit() {
    
    this.viewUsersService.getInfo().subscribe(result =>{
      
            this.myData = result;
            console.log(this.myData);
      
          });
  }

}
