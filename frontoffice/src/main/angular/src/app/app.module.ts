import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {ConfigActivityComponent} from './components/config-activity/config-activity.component';
import {AnalyticsActivityComponent} from './components/analytics-activity/analytics-activity.component';
import {CeaComponent} from './components/cea/cea.component';
import {HttpClientModule} from '@angular/common/http';
import {CodemirrorModule} from '@ctrl/ngx-codemirror';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import 'codemirror/mode/javascript/javascript';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import { CodeeditorComponent } from './components/codeeditor/codeeditor.component';
import { PageNotFoundComponent } from './components/page-not-found/page-not-found.component';
import { TestConfigActivityComponent } from './components/test-config-activity/test-config-activity.component';

@NgModule({
  declarations: [
    AppComponent,
    ConfigActivityComponent,
    AnalyticsActivityComponent,
    CeaComponent,
    CodeeditorComponent,
    PageNotFoundComponent,
    TestConfigActivityComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    HttpClientModule,
    CodemirrorModule,
    NgbModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
