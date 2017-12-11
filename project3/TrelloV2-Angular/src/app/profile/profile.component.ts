import { Component, OnInit, Input } from '@angular/core';
import { ProfileService } from './profile.service';
import { ProfileUser } from './profile-user';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css'],
  providers: [ProfileService]
})
export class ProfileComponent implements OnInit {
  
  //only for display purpose
   userId: number;
   firstName: String;
   lastName: String;
   userName: String;
   password: String;
   teamId: number;
   roleType: number;
   email: String;
  
   temp: ProfileUser;
  
  constructor(private profileService: ProfileService) { }
  
  @Input() user:ProfileUser;
  status:boolean;

  ngOnInit() {

    //retrieve info from localStorage
    this.temp = JSON.parse(localStorage.getItem("currentUser"));

    //observable: call getInfo in service
    this.profileService.getInfo(this.temp).subscribe(result =>{

      this.user = result;
      
      this.userId = this.user.userId;
      this.firstName = this.user.firstName;
      this.lastName = this.user.lastName;
      this.userName = this.user.userName;
      this.password = this.user.password;
      this.teamId = this.user.teamId;
      this.roleType = this.user.roleType;
      this.email = this.user.email;
    });
  
  }

  //show and hide table
  public showUpdate = true;
  public showTable = false;
  public showDone = false;


  updateInfo(){  //press update button 
    
    this.showUpdate = false;
    this.showTable = true;
    this.showDone = true;

   }

   done(){ //press done button
     
    //hide and show
     this.showTable = false;
     this.showDone = false;
     this.showUpdate = true;

     //update profile, call postUpdate in service
     this.profileService.postUpdate(this.user).subscribe();
     this.status = true;
     
     this.userId = this.user.userId;
     this.firstName = this.user.firstName;
     this.lastName = this.user.lastName;
     this.userName = this.user.userName;
     this.password = this.user.password;
     this.teamId = this.user.teamId;
     this.roleType = this.user.roleType;
     this.email = this.user.email;
 
   }

}