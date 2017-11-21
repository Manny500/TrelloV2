import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ViewUsersService } from '../view-users/view-users.service';

@Component({
  selector: 'app-user-requests',
  templateUrl: './user-requests.component.html',
  styleUrls: ['./user-requests.component.css']
})
export class UserRequestsComponent implements OnInit {
  idTest;

  constructor(private viewUsersService: ViewUsersService) { }

  ngOnInit() {
    this.idTest = this.viewUsersService.getPass();
  }

  ngOnDestroy() {
    
  }

}
