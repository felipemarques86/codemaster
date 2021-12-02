import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {CeaComponent} from "./components/cea/cea.component";

const routes: Routes = [
  {
    path: 'cea/:id/:userId',
    component: CeaComponent
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
