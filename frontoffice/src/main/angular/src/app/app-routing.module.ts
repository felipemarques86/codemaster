import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {CeaComponent} from "./components/cea/cea.component";
import {ConfigActivityComponent} from "./components/config-activity/config-activity.component";

const routes: Routes = [
  {
    path: 'cea/:id/:userId',
    component: CeaComponent
  },
  {
    path: 'cea/new',
    component: ConfigActivityComponent
  },
  {
    path: 'cea/:id',
    component: ConfigActivityComponent
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
