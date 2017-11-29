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
import {AuthGuard} from './auth-guard.service';
import { UserRequestsComponent } from './user-requests/user-requests.component';
 
const routes: Routes = [
  { path: '', redirectTo: '/welcome', pathMatch: 'full' },
  { path: 'welcome', component: WelcomeComponent},
  { path: 'login',  component: LoginComponent },
  { path: 'home',  component: HomeComponent,canActivate: [AuthGuard] },
  { path: 'trello',  component: ScrumBoardViewComponent,canActivate: [AuthGuard] },
  { path: 'chart',  component: BurndownChartComponent,canActivate: [AuthGuard] },
  { path: 'profile',  component: ProfileComponent,canActivate: [AuthGuard] },
  { path: 'viewUsers',  component: ViewUsersComponent,canActivate: [AuthGuard] },
  { path: 'activity',  component: ActivityComponent,canActivate: [AuthGuard] },
  { path: 'register',  component: RegisterUserComponent,canActivate: [AuthGuard] },
  { path: 'publicBoards',  component: CompanyBoardsComponent,canActivate: [AuthGuard] },
  { path: 'request',  component: UserRequestsComponent,canActivate: [AuthGuard]},
]; 
 
// using { useHash: true } lets us make changes to recompile angular source files again without getting whitelabel page thing
@NgModule({
  imports: [ RouterModule.forRoot(routes, { useHash: true }) ],
  // imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule {}