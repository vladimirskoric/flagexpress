import { AfterViewInit, Component, OnDestroy, OnInit, ViewChild, Output } from '@angular/core';
import { MatStepper } from '@angular/material/stepper';
import { StepperSelectionEvent } from '@angular/cdk/stepper';
import { MatDialog } from '@angular/material/dialog';
import { NavigationService } from '@shared/services/navigation.service';
import { CanComponentDeactivate } from '@shared/services/can-deactivate.service';
import { DonationFormService } from '@shared/services/donation-form.service';
import { StepperService } from '@shared/services/stepper.service';
import { ConfirmationComponent } from '@shared/components/confirmation/confirmation.component';
// import { DonorDetailsComponent } from './donor-details/donor-details.component';
// import { DonorItemsDetailsComponent } from '.././donation-form/donor-items-details/donor-items-details.component';
import { RecipientSentToComponent } from './recipient-sent-to/recipient-sent-to.component';

@Component({
  selector: 'app-recipient-info',
  templateUrl: './recipient-info.component.html',
  styleUrls: ['./recipient-info.component.scss']
})
export class RecipientInfoComponent implements OnInit, AfterViewInit, OnDestroy, CanComponentDeactivate {

  private isEditing: boolean;
  public isLinear = true;
  @ViewChild('stepper') stepper: MatStepper;
  @ViewChild(RecipientSentToComponent) stepRecipientComponent: RecipientSentToComponent;
  // @ViewChild(DonorItemsDetailsComponent) donorItemDetails: DonorItemsDetailsComponent;

  constructor(
    private navigationService: NavigationService,
    public donationFormService: DonationFormService,
    public stepperService: StepperService,
    private dialog: MatDialog,
  ) {

  }

  ngOnInit(): void {
    this.navigationService.setTitle({
      title: 'Donation Form',
      toolbar: true,
      isShowAppName: true
    });
    // this.donationFormService.setEditable(true);
    // this.isEditing = this.donationFormService.currentEditState;
  }

  ngAfterViewInit(): void {
    this.stepperService.stepperReady(this.stepper);
  }

  ngOnDestroy(): void {
    // this.donationFormService.setEditable(false);
  }

  public async canDeactivate(): Promise<boolean> {
    // if (this.isEditing) {
    //   if (this.stepRecipientComponent.donorDetailsForm.dirty) {
    //     return await this.unsavedChanges();
    //   }
    // }
    return true;
  }

  public async unsavedChanges(): Promise<boolean> {
    const dialogRef = this.dialog.open(ConfirmationComponent, {
      width: '450px'
    });
    return await dialogRef.afterClosed().toPromise();
  }

  public onStepChange(event: any) {
    let selectedIndex = event.selectedIndex;
    // let donorItemTypes = this.donationFormService.donationForm.value.donorItemTypes;
    // this.donorItemDetails.applyNewItemTypes(donorItemTypes);
  }

}
