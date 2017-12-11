import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  //retrieve info from local storage
  currentUserRoleType = JSON.parse(localStorage.getItem("currentUser")).roleType;
  
  constructor() { }

  ngOnInit() {
  }

}
