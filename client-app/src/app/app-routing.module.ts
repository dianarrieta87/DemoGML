import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ClientMainComponent } from './client-main/client-main.component';

const routes: Routes = [
  { path: '', component: ClientMainComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
