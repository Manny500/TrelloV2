import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { HttpModule } from '@angular/http';
import {HttpClientModule} from '@angular/common/http';
import {FormsModule} from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';

import { AppComponent } from './app.component';
import { WelcomeComponent } from './welcome/welcome.component';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { NavbarComponent } from './navbar/navbar.component';
import { ScrumBoardViewComponent } from './scrum-board-view/scrum-board-view.component';
import { BurndownChartComponent } from './burndown-chart/burndown-chart.component';
import { ProfileComponent } from './profile/profile.component';
import { ViewUsersComponent } from './view-users/view-users.component';
import { ActivityComponent } from './activity/activity.component';
import { RegisterUserComponent } from './register-user/register-user.component';
import { UserRequestsComponent } from './user-requests/user-requests.component';
import { CompanyBoardsComponent } from './company-boards/company-boards.component';
import { TV2UserService } from './login/tv2user.service';
import { ChartsModule } from 'ng2-charts';
import { BurndownChartService } from './burndown-chart/burndown-chart.service';
import { DatePipe } from '@angular/common';
import { ProfileService } from './profile/profile.service';
import {AuthGuard} from './auth-guard.service';
import { LoginNavbarComponent } from './login-navbar/login-navbar.component';
import { ViewUsersService } from './view-users/view-users.service';
import { BoardDisplayService } from './home/board-display.service';
import { LaneDisplayService } from './scrum-board-view/lane-display.service';
import { RegisterService } from './register-user/register-user.service';
import { NavbarModule } from './navbar/navbar.module';


@NgModule({
  declarations: [
    AppComponent,
    WelcomeComponent,
    LoginComponent,
    HomeComponent,
    NavbarComponent,
    ScrumBoardViewComponent,
    BurndownChartComponent,
    ProfileComponent,
    ViewUsersComponent,
    ActivityComponent,
    RegisterUserComponent,
    UserRequestsComponent,
    CompanyBoardsComponent,
    // UserHomeComponent,
    LoginNavbarComponent
  ],
  imports: [
    BrowserModule,
    RouterModule,
    FormsModule,
    HttpClientModule,
    AppRoutingModule,
    HttpModule,
    ChartsModule,
   // NavbarModule
  ],


  providers: [
    BurndownChartService,
    DatePipe,
    ProfileService,
    TV2UserService,
    BoardDisplayService,
    ViewUsersService,
    LaneDisplayService,
    RegisterService,
    AuthGuard
  ],

  bootstrap: [AppComponent]
})
export class AppModule { }
