import {AfterViewInit, Component, Input, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import { Title } from '@angular/platform-browser';
import { Location } from '@angular/common';
import { NavigationService } from '@shared/services/navigation.service';
import {Router} from '@angular/router';
import {DonationFormService} from '@shared/services/donation-form.service';
import {StepperService} from '@shared/services/stepper.service';
import {MatDialog} from '@angular/material/dialog';
import {CancelDialogComponent} from '@shared/components/cancel-dialog/cancel-dialog.component';
import {WebStorageService} from '@shared/services/web-storage.service';

@Component({
  selector: 'app-toolbar',
  templateUrl: './toolbar.component.html',
  styleUrls: ['./toolbar.component.scss'],

})
export class ToolbarComponent {

  public pageTitle: string;
  public showToolbar: boolean;
  public transparentToolbar: boolean;
  public isShowAppName: boolean;
  public hideCancel: boolean;

  constructor(
    private navigationService: NavigationService,
    private titleService: Title,
    private location: Location,
    private router: Router,
    private dialog: MatDialog,
    private donationFormService: DonationFormService,
    private webStorageService: WebStorageService,
    private stepperService: StepperService) {

    this.navigationService.currentTitle.subscribe(data => {
      if (data) {
        this.pageTitle = data.title || 'FLAGexpress.ph';
        this.showToolbar = data.toolbar;
        this.transparentToolbar = !this.pageTitle || !this.pageTitle.length;
        this.isShowAppName = data.isShowAppName;
        this.hideCancel = data.hideCancel;
      }
    });
  }

  cancel() {
    const dialogRef = this.dialog.open(CancelDialogComponent, {
      width: '450px'
    });
    dialogRef.afterClosed().subscribe( result => {
      if (result) {
        this.location.back();
      }
    });
  }

  public back(): void {
    const stepperIndex = this.stepperService.matStepper;
    if (stepperIndex && stepperIndex.selectedIndex > 0) {
      this.stepperService.setProgressBarValue(20, false);
      this.stepperService.matStepper.previous();
    } else {
      this.location.back();
    }
  }

}
