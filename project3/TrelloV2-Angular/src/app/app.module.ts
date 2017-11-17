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
    CompanyBoardsComponent
  ],
  imports: [
    BrowserModule,
    RouterModule,
    FormsModule,
    HttpClientModule,
    AppRoutingModule,
    HttpModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
