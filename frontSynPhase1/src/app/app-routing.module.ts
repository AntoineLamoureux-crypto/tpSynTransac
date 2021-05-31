import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AfficherPermitComponent } from './components/afficher-permit/afficher-permit.component';
import { DashboardEnfantComponent } from './components/dashboard-enfant/dashboard-enfant.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { DemandePermisComponent } from './components/demande-permis/demande-permis.component';
import { Error404Component } from './components/error404/error404.component';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { LogoutComponent } from './components/logout/logout.component';
import { RenewPermitAdulteComponent } from './components/renew-permit-adulte/renew-permit-adulte.component';
import { RenewPermitEnfantComponent } from './components/renew-permit-enfant/renew-permit-enfant.component';
import { SubscribeEnfantComponent } from './components/subscribe-enfant/subscribe-enfant.component';
import { SubscribeComponent } from './components/subscribe/subscribe.component';

//, canActivate:[GuardAuthService]
const routes: Routes = [
{path: 'dashboard', component: DashboardComponent},
{path: 'dashboardEnfant', component: DashboardEnfantComponent},
{path: 'renewChildPermit', component: RenewPermitEnfantComponent},
{path: 'renewAdultPermit', component: RenewPermitAdulteComponent},
{path: 'afficherPermit', component: AfficherPermitComponent},
{path: 'subscribeEnfant', component: SubscribeEnfantComponent},
{path: 'subscribe', component: SubscribeComponent},
{path: 'logout', component: LogoutComponent,},
{path: 'login', component: LoginComponent},
{path: 'demande', component: DemandePermisComponent},
{path: 'home', component: HomeComponent},
{path: '', redirectTo:'/home', pathMatch: 'full'},
{path: '**', component: Error404Component}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
