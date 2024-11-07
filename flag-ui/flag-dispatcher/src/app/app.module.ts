import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { MaterialModule } from './_shared/material/material.module';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ReactiveFormsModule } from '@angular/forms';
import { ConfirmationDialogComponent } from './_shared/components/action-dialog/confirmation-dialog/confirmation-dialog.component';
import { ApproveComponent } from './_shared/components/action-dialog/approve/approve.component';
import { DeliveryComponent } from './_shared/components/action-dialog/delivery/delivery.component';
import { IssueComponent } from './_shared/components/action-dialog/issue/issue.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    ConfirmationDialogComponent,
    ApproveComponent,
    DeliveryComponent,
    IssueComponent,
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    MaterialModule,
    ReactiveFormsModule,
  ],
  providers: [],
  exports: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
