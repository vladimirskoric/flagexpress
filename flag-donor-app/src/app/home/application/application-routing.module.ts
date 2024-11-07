import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CanDeactivateGuard } from '@shared/services/can-deactivate.service';
import { WelcomeComponent } from './welcome/welcome.component';
import { CheckDonationStatusComponent } from './check-donation-status/check-donation-status.component';
import { ChooseFlowUpdateComponent } from './choose-flow-update/choose-flow-update.component';
import { CheckInputComponent } from './check-donation-status/check-input/check-input.component';
import { ResultDonationStatusComponent } from './check-donation-status/result-donation-status/result-donation-status.component';
import { DonationFormComponent } from './donation-form/donation-form.component';
import { OriginComponent } from './choose-flow-update/origin/origin.component';
import { ChooseFlowComponent } from './choose-flow-update/choose-flow/choose-flow.component';
import {DONATION_SERVICE_STORAGE, WebStorageService} from '@shared/services/web-storage.service';
import { RecipientInfoComponent } from './recipient-info/recipient-info.component';
import { RecipientSentToComponent } from './recipient-info/recipient-sent-to/recipient-sent-to.component';
import { RecipientLocatedAtComponent } from './recipient-info/recipient-located-at/recipient-located-at.component';
import { RecipientDonatedOnComponent } from './recipient-info/recipient-donated-on/recipient-donated-on.component';
import {SESSION_STORAGE} from 'ngx-webstorage-service';
import { DonorCompleteComponent } from './donation-form/donor-complete/donor-complete.component';

const routes: Routes = [
  {
    path: '',
    redirectTo: 'choose-flow-update',
  }, {
    path: 'check-donation-status', component: CheckDonationStatusComponent,
    children: [
      {
        path: '',
        component: CheckInputComponent,
        data: { title: 'Check Donation Status', toolbar: true, isShowAppName: false, hideCancel: true },
      }, {
        path: 'result-status',
        component: ResultDonationStatusComponent,
        data: { title: 'Result Donation Status', toolbar: true, isShowAppName: false, hideCancel: true },
      }
    ]
  },
  {
    path: 'choose-flow-update', component: ChooseFlowUpdateComponent,
    children: [
      {
        path: '',  component: ChooseFlowComponent,
        data: { title: 'Choose Action', toolbar: true, isShowAppName: true, hideCancel: true },
      },
      {
        path: 'origin',  component: OriginComponent,
        data: { title: 'Donation Origin', toolbar: true, isShowAppName: true, hideCancel: true },
      }
    ],
  },
  {
    path: 'donation-form', component: DonationFormComponent,
    data: { title: 'Donation Form', toolbar: true, isShowAppName: true, hideCancel: false },
    canDeactivate: [CanDeactivateGuard],
  },
  {
    path: 'complete', component: DonorCompleteComponent,
    data: { title: 'Donation Form', toolbar: true, isShowAppName: true, hideCancel: true },
  },
  {
    path: 'recipient-info', component: RecipientInfoComponent,
    children: [
      {
        path: '',  component: RecipientInfoComponent,
        data: { title: 'Recipient Info', toolbar: true, isShowAppName: true, hideCancel: true },
      },
      {
        path: 'sent-to',  component: RecipientSentToComponent,
        data: { title: 'Recipient Sent To', toolbar: true, isShowAppName: true, hideCancel: true },
      },
      {
        path: 'located-at',  component: RecipientLocatedAtComponent,
        data: { title: 'Recipient Located At', toolbar: true, isShowAppName: true, hideCancel: true },
      },
      {
        path: 'donoate-on',  component: RecipientDonatedOnComponent,
        data: { title: 'Recipient Donate On', toolbar: true, isShowAppName: true, hideCancel: true },
      }
    ],
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class ApplicationRoutingModule { }
