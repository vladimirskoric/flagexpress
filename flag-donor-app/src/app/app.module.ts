import { HttpClientModule } from '@angular/common/http';
import { BrowserModule, Title } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ServiceWorkerModule } from '@angular/service-worker';
import { environment } from '@env/environment';
import { MaterialModule } from '@shared/modules/material/material.module';
import { ToolbarComponent } from '@shared/components/toolbar/toolbar.component';
import { PrivacyPolicyComponent } from '@shared/components/privacy-policy/privacy-policy.component';
import {ConfirmationComponent} from '@shared/components/confirmation/confirmation.component';
import { CancelDialogComponent } from '@shared/components/cancel-dialog/cancel-dialog.component';
import {DONATION_SERVICE_STORAGE, WebStorageService} from '@shared/services/web-storage.service';
import {SESSION_STORAGE} from 'ngx-webstorage-service';

@NgModule({
  declarations: [
    AppComponent,
    ToolbarComponent,
    PrivacyPolicyComponent,
    ConfirmationComponent,
    CancelDialogComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MaterialModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    ServiceWorkerModule.register('ngsw-worker.js', {enabled: environment.production})
  ],
  providers: [
    {
      provide: DONATION_SERVICE_STORAGE, useExisting: SESSION_STORAGE
    },
    WebStorageService,
    Title
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
