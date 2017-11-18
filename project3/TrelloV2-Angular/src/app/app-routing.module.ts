import { NgModule }             from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { WelcomeComponent } from './welcome/welcome.component';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { ScrumBoardViewComponent } from './scrum-board-view/scrum-board-view.component';
import { BurndownChartComponent } from './burndown-chart/burndown-chart.component';
import { ProfileComponent } from './profile/profile.component';
import { ViewUsersComponent } from './view-users/view-users.component';
import { ActivityComponent } from './activity/activity.component';
import { RegisterUserComponent } from './register-user/register-user.component';
import { CompanyBoardsComponent } from './company-boards/company-boards.component';
import { UserHomeComponent } from './user-home/user-home.component';
 
const routes: Routes = [
  { path: '', redirectTo: '/welcome', pathMatch: 'full' },
  { path: 'welcome', component: WelcomeComponent},
  { path: 'login',  component: LoginComponent },
  { path: 'master-home',  component: HomeComponent },
  { path: 'user-home',  component: UserHomeComponent },
  { path: 'trello',  component: ScrumBoardViewComponent },
  { path: 'chart',  component: BurndownChartComponent },
  { path: 'profile',  component: ProfileComponent },
  { path: 'viewUsers',  component: ViewUsersComponent },
  { path: 'activity',  component: ActivityComponent },
  { path: 'register',  component: RegisterUserComponent },
  { path: 'publicBoards',  component: CompanyBoardsComponent },
];
 
@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule {}