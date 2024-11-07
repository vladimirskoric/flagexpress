import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MaterialModule } from '@shared/modules/material/material.module';

import { HomeRoutingModule } from './home-routing.module';

import { HomeComponent } from './home.component';
import { LandingComponent } from './landing/landing.component';
import { ApplicationComponent } from './application/application.component';
import { HeaderComponent } from '@shared/components/navigation/header/header.component';
import { SlickCarouselModule } from 'ngx-slick-carousel';
import { DonateDialogComponent } from '@shared/components/donate-dialog/donate-dialog.component';

@NgModule({
  declarations: [
    HomeComponent,
    LandingComponent,
    ApplicationComponent,
    HeaderComponent,
    DonateDialogComponent
  ],
  imports: [
    CommonModule,
    MaterialModule,
    HomeRoutingModule,
    SlickCarouselModule
  ]
})
export class HomeModule { }
