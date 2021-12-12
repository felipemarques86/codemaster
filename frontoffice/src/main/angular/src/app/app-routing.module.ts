import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {CeaComponent} from "./components/cea/cea.component";
import {ConfigActivityComponent} from "./components/config-activity/config-activity.component";
import {PageNotFoundComponent} from "./components/page-not-found/page-not-found.component";
import {TestConfigActivityComponent} from "./components/test-config-activity/test-config-activity.component";

const routes: Routes = [
  {
    path: 'activity/:id/user/:userId',
    component: CeaComponent
  },
  {
    path: 'config.html',
    component: ConfigActivityComponent
  },
  {
    path: 'test/config.html',
    component: TestConfigActivityComponent
  },
  { path: '**', pathMatch: 'full',
    component: PageNotFoundComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes, { useHash: true })],
  exports: [RouterModule]
})
export class AppRoutingModule { }
