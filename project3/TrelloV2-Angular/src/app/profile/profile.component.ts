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
  
  // myData: ProfileUser;

  //only for display purpose
   userId: number;
   firstName: String;
   lastName: String;
   userName: String;
   password: String;
   team: number;
   roleType: number;
   email: String;
  
   temp: ProfileUser;
  

  constructor(private profileService: ProfileService) { }
  
  @Input() user:ProfileUser;
  responseStatus:Object= [];
  status:boolean;

  ngOnInit() {
    this.temp = JSON.parse(localStorage.getItem("currentUser"));

    this.profileService.getInfo(this.temp).subscribe(result =>{
      this.user = result;
      
      this.userId = this.user.userId;
      this.firstName = this.user.firstName;
      this.lastName = this.user.lastName;
      this.userName = this.user.userName;
      this.password = this.user.password;
      this.team = this.user.team;
      this.roleType = this.user.roleType;
      this.email = this.user.email;
    });
  
  }

  public showUpdate = true;
  public showTable = false;
  public showDone = false;


  updateInfo(){  //press update button 
    
    this.showUpdate = false;
    this.showTable = true;
    this.showDone = true;
    console.log(this.showTable);
    
  //   setTimeout(function() {
  //     this.edited = false;
  //     console.log(this.edited);
  //   }.bind(this), 3000);
   }
   done(){
     console.log('done() is called: ');
     console.log(this.user.firstName);
     this.showTable = false;
     this.showDone = false;
     this.showUpdate = true;

     this.profileService.postUpdate(this.user).subscribe(
       data => console.log(this.responseStatus = data),
       err => console.log(err),
       () => console.log('request completed')
     );
     this.status = true;
     
     this.userId = this.user.userId;
     this.firstName = this.user.firstName;
     this.lastName = this.user.lastName;
     this.userName = this.user.userName;
     this.password = this.user.password;
     this.team = this.user.team;
     this.roleType = this.user.roleType;
     this.email = this.user.email;
 
   }

}
