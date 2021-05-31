import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AfficherCitoyenAdulteComponent } from './component/afficher-citoyen-adulte/afficher-citoyen-adulte.component';
import { AfficherCitoyenEnfantComponent } from './component/afficher-citoyen-enfant/afficher-citoyen-enfant.component';
import { DeleteCitoyenAdulteComponent } from './component/delete-citoyen-adulte/delete-citoyen-adulte.component';
import { DeleteCitoyenEnfantComponent } from './component/delete-citoyen-enfant/delete-citoyen-enfant.component';
import { UpdateCitoyenAdulteComponent } from './component/update-citoyen-adulte/update-citoyen-adulte.component';
import { UpdateCitoyenEnfantComponent } from './component/update-citoyen-enfant/update-citoyen-enfant.component';

const routes: Routes = [
  {path: 'afficherAdulte', component: AfficherCitoyenAdulteComponent},
  {path: 'afficherEnfant', component: AfficherCitoyenEnfantComponent},
  {path: 'updateAdulte/:id', component: UpdateCitoyenAdulteComponent},
  {path: 'updateEnfant/:id', component: UpdateCitoyenEnfantComponent},
  {path: 'deleteAdulte', component: DeleteCitoyenAdulteComponent},
  {path: 'deleteEnfant', component: DeleteCitoyenEnfantComponent},
  {path: '', redirectTo:'/afficherAdulte', pathMatch: 'full'},
  {path: '**', component: AfficherCitoyenAdulteComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
