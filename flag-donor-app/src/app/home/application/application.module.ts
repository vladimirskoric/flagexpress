import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RecaptchaModule, RECAPTCHA_SETTINGS, RecaptchaSettings, RecaptchaFormsModule } from 'ng-recaptcha';

import { MaterialModule } from '@shared/modules/material/material.module';
import { ApplicationRoutingModule } from './application-routing.module';
import { WelcomeComponent } from './welcome/welcome.component';
import { CheckDonationStatusComponent } from './check-donation-status/check-donation-status.component';
import { CheckInputComponent } from './check-donation-status/check-input/check-input.component';
import { StatusDeliveryDialogComponent } from 'src/app/_shared/components/status-delivery-dialog/status-delivery-dialog.component';
import { ChooseFlowUpdateComponent } from './choose-flow-update/choose-flow-update.component';
import { ResultDonationStatusComponent } from './check-donation-status/result-donation-status/result-donation-status.component';
import { DonorDetailsComponent } from './donation-form/donor-details/donor-details.component';
import { DonorLocationComponent } from './donation-form/donor-location/donor-location.component';
import { DonationFormComponent } from './donation-form/donation-form.component';
import { ChooseFlowComponent } from './choose-flow-update/choose-flow/choose-flow.component';
import { OriginComponent } from './choose-flow-update/origin/origin.component';
import { DonorItemsComponent } from './donation-form/donor-items/donor-items.component';
import { DonorItemsDetailsComponent } from './donation-form/donor-items-details/donor-items-details.component';
import { DonorReviewComponent } from './donation-form/donor-review/donor-review.component';
import { DonorCompleteComponent } from './donation-form/donor-complete/donor-complete.component';
import { DonorItemsDetailsDescriptionComponent } from './donation-form/donor-items-details-description/donor-items-details-description.component';
import { RecipientInfoComponent } from './recipient-info/recipient-info.component';
import { RecipientSentToComponent } from './recipient-info/recipient-sent-to/recipient-sent-to.component';
import { RecipientLocatedAtComponent } from './recipient-info/recipient-located-at/recipient-located-at.component';
import { RecipientDonatedOnComponent } from './recipient-info/recipient-donated-on/recipient-donated-on.component';
import {MatProgressBarModule} from "@angular/material/progress-bar";

@NgModule({
  declarations: [
    WelcomeComponent,
    CheckDonationStatusComponent,
    CheckInputComponent,
    StatusDeliveryDialogComponent,
    ChooseFlowUpdateComponent,
    ResultDonationStatusComponent,
    DonorDetailsComponent,
    DonorLocationComponent,
    DonationFormComponent,
    ChooseFlowComponent,
    OriginComponent,
    DonorItemsComponent,
    DonorItemsDetailsComponent,
    DonorReviewComponent,
    DonorCompleteComponent,
    DonorItemsDetailsDescriptionComponent,
    RecipientInfoComponent,
    RecipientSentToComponent,
    RecipientLocatedAtComponent,
    RecipientDonatedOnComponent,
  ],
    imports: [
        CommonModule,
        MaterialModule,
        FormsModule,
        ReactiveFormsModule,
        HttpClientModule,
        ApplicationRoutingModule,
        RecaptchaModule,
        RecaptchaFormsModule,
        MatProgressBarModule,
    ],
  providers: [{
    provide: RECAPTCHA_SETTINGS,
    useValue: {
      siteKey: '6LdsRAAVAAAAAJmIsyjhmVifOrpiGMU8PtF-Uttj',
    } as RecaptchaSettings,
  }]
})
export class ApplicationModule { }
