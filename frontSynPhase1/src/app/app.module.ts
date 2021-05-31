import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { DemandePermisComponent } from './components/demande-permis/demande-permis.component';
import { Error404Component } from './components/error404/error404.component';
import { LoginComponent } from './components/login/login.component';
import { LogoutComponent } from './components/logout/logout.component';
import { SubscribeComponent } from './components/subscribe/subscribe.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { SubscribeEnfantComponent } from './components/subscribe-enfant/subscribe-enfant.component';
import { DashboardEnfantComponent } from './components/dashboard-enfant/dashboard-enfant.component';
import { HomeComponent } from './components/home/home.component';
import { RenewPermitEnfantComponent } from './components/renew-permit-enfant/renew-permit-enfant.component';
import { RenewPermitAdulteComponent } from './components/renew-permit-adulte/renew-permit-adulte.component';
import { AfficherPermitComponent } from './components/afficher-permit/afficher-permit.component';


@NgModule({
  declarations: [
    AppComponent,
    DemandePermisComponent,
    Error404Component,
    LoginComponent,
    LogoutComponent,
    SubscribeComponent,
    DashboardComponent,
    SubscribeEnfantComponent,
    DashboardEnfantComponent,
    HomeComponent,
    RenewPermitEnfantComponent,
    RenewPermitAdulteComponent,
    AfficherPermitComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
