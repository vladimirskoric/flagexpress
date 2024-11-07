import { AfterViewInit, Component, OnDestroy, OnInit, ViewChild, Output } from '@angular/core';
import { NavigationService } from '@shared/services/navigation.service';
import { DonationFormService } from '@shared/services/donation-form.service';
import { ConfirmationComponent } from '@shared/components/confirmation/confirmation.component';
import { MatDialog } from '@angular/material/dialog';
import { CanComponentDeactivate } from '@shared/services/can-deactivate.service';
import { MatStepper } from '@angular/material/stepper';
import { StepperService } from '@shared/services/stepper.service';
import { DonorDetailsComponent } from './donor-details/donor-details.component';
import { DonorItemsDetailsComponent } from './donor-items-details/donor-items-details.component';
import { WebStorageService } from '@shared/services/web-storage.service';
import {ThemePalette} from '@angular/material/core';
import {ProgressBarMode} from '@angular/material/progress-bar';

@Component({
  selector: 'app-donation-form',
  templateUrl: './donation-form.component.html',
  styleUrls: ['./donation-form.component.scss'],
  providers: [DonationFormService]
})
export class DonationFormComponent implements OnInit, AfterViewInit, OnDestroy, CanComponentDeactivate {

  color: ThemePalette = 'primary';
  mode: ProgressBarMode = 'determinate';
  value = 0;
  public isLinear = true;
  public isDomestic = false;
  @ViewChild('stepper') stepper: MatStepper;
  @ViewChild(DonorDetailsComponent) stepDonorDetailsComponent: DonorDetailsComponent;
  @ViewChild(DonorItemsDetailsComponent) donorItemDetails: DonorItemsDetailsComponent;

  constructor(
    private navigationService: NavigationService,
    private dialog: MatDialog,
    private webStorageService: WebStorageService,
    public donationFormService: DonationFormService,
    public stepperService: StepperService,
  ) {

  }

  ngOnInit(): void {
    this.navigationService.setTitle({
      title: 'Donation Form',
      toolbar: true,
      isShowAppName: true
    });
    this.webStorageService.setDonationEditing(true);
    this.stepperService.progressBarValue.subscribe(value => {
      this.value = value;
    });

    const origin = this.webStorageService.getDonationOrigin();
    this.isDomestic = origin == 'Domestic';
  }

  ngAfterViewInit(): void {
    this.stepperService.stepperReady(this.stepper);
  }

  ngOnDestroy(): void {
    this.webStorageService.setDonationEditing(false);
  }

  get isEditing() {
    return this.webStorageService.getDonationEditing();
  }

  public async canDeactivate(): Promise<boolean> {
    if (this.isEditing) {
      if (this.stepDonorDetailsComponent.donorDetailsForm.dirty) {
        return await this.unsavedChanges();
      }
    }
    return true;
  }

  public async unsavedChanges(): Promise<boolean> {
    const dialogRef = this.dialog.open(ConfirmationComponent, {
      width: '450px'
    });
    return await dialogRef.afterClosed().toPromise();
  }

  public onStepChange(event: any) {
    const selectedIndex = event.selectedIndex;
    const donorItemTypes = this.donationFormService.donationForm.value.donorItemTypes;
    this.donorItemDetails.applyNewItemTypes(donorItemTypes);
  }

}
