import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ConfigActivityComponent } from './components/config-activity/config-activity.component';
import { AnalyticsActivityComponent } from './components/analytics-activity/analytics-activity.component';
import { CeaComponent } from './components/cea/cea.component';
import { HttpClientModule } from '@angular/common/http';
import { CodemirrorModule } from '@ctrl/ngx-codemirror';
import {FormsModule} from "@angular/forms";
import 'codemirror/mode/javascript/javascript';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { CommentsComponent } from './components/comments/comments.component';

@NgModule({
  declarations: [
    AppComponent,
    ConfigActivityComponent,
    AnalyticsActivityComponent,
    CeaComponent,
    CommentsComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    HttpClientModule,
    CodemirrorModule,
    NgbModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
