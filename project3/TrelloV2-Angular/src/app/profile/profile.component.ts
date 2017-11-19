import { Component, OnInit } from '@angular/core';
import { ProfileService } from './profile.service';
import { ProfileUser } from './profile-user';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  
  myData: ProfileUser;

  userId: number;
  firstName: String;
  lastName: String;
  userName: String;
  password: String;
  team: number;
  roleType: number;
  email: String;
  
  

  constructor(private profileService: ProfileService) { }

  ngOnInit() {

    this.profileService.getInfo().subscribe(result =>{

      this.myData = result;
      console.log(this.myData.email);
      this.userId = this.myData.userId;
      this.firstName = this.myData.firstName;
      this.lastName = this.myData.lastName;
      this.userName = this.myData.userName;
      this.password = this.myData.password;
      this.team = this.myData.team;
      this.roleType = this.myData.roleType;
      this.email = this.myData.email;

    });
  }
  public showUpdate = true;
  public showTable = false;
  public showDone = false;

  updateInfo(){
    
    this.showUpdate = false;
    this.showTable = true;
    this.showDone = true;
    console.log(this.showTable);
    // document.getElementById('updateBtn').style.visibility = 'hidden';
		// document.getElementById('profileForm').style.visibility = 'visible';
    
  //   setTimeout(function() {
  //     this.edited = false;
  //     console.log(this.edited);
  //   }.bind(this), 3000);
   }
   done(){
     this.showTable = false;
     this.showDone = false;
     this.showUpdate = true;
   }

}
