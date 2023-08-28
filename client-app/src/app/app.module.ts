import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { SidebarComponent } from './sidebar/sidebar.component';
import { ClientMainComponent } from './client-main/client-main.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { ClientFormComponent } from './client-form/client-form.component';

@NgModule({
  declarations: [
    AppComponent,
    SidebarComponent,
    ClientMainComponent,
    ClientFormComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    NgbModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
