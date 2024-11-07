import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {DonationFormService} from '@shared/services/donation-form.service';
import {ApiService} from '@shared/services/api.service';
import {UtilityService} from '@shared/services/utility.service';
import {WebStorageService} from '@shared/services/web-storage.service';
import {MatCheckbox} from '@angular/material/checkbox';
import {PrivacyPolicyComponent} from '@shared/components/privacy-policy/privacy-policy.component';
import {MatDialog} from '@angular/material/dialog';

@Component({
  selector: 'app-donor-items-details-description',
  templateUrl: './donor-items-details-description.component.html',
  styleUrls: ['./donor-items-details-description.component.scss']
})
export class DonorItemsDetailsDescriptionComponent implements OnInit {

  public donorItemTypes: any;
  public donorItemRemarks: any;
  public donorItemRemarkForm!: FormGroup;
  public tcAccepted: boolean;
  public mntnc = {
    itemTypes: []
  };

  constructor(
    private formBuilder: FormBuilder,
    private donationService: DonationFormService,
    private router: Router,
    private route: ActivatedRoute,
    private apiService: ApiService,
    private util: UtilityService,
    private webStorageService: WebStorageService,
    public dialog: MatDialog
  ) {

  }

  ngOnInit(): void {
    this.mntnc.itemTypes = this.apiService.GetItemTypes();
    this.donorItemTypes = this.donationService.donorItemTypes;
    this.initForm();
  }

  public btnNext() {
    this.donorItemRemarks = this.donorItemRemarkForm.value;
    this.donationService.setDonorRemark(this.donorItemRemarks);
    // this.router.navigate(['../donation-form'], { relativeTo: this.route })
  }

  private initForm() {
    let formControls = {};

    if (this.donationService.donorRemark) {
      formControls = this.donationService.donorRemark;
    } else {
      formControls = {
        describe: [null],
      };
    }

    this.donorItemRemarkForm = this.formBuilder.group(formControls);
    this.donationService.stepReady(this.donorItemRemarkForm, 'donorRemark');
  }

  public tcChanged(event: MatCheckbox) {
    this.donationService.donationForm.controls.donorTcAccepted.setValue(event.checked);
  }

  showPrivacyPolicy() {
    const fullScreenConfig = {
      minWidth: '100%',
      height: '100%',
      panelClass: 'custom-dialog-container',
    };
    this.dialog.open(PrivacyPolicyComponent, fullScreenConfig);
  }

  submitForm(): void {
    this.donationService.submitDonationForm().subscribe((result) => {
      const ref_code = result.ref_code;
      this.webStorageService.setReferenceCode(ref_code);
      this.webStorageService.setDonationEditing(false);
      this.router.navigate(['../complete'], {relativeTo: this.route});
    });
  }

  get isComplete(){
    return this.webStorageService.getDonationOption() === 'ILL_DELIVER_TO_OCD'
  }

}
