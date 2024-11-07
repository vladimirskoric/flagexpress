import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { DonationFormService } from '@shared/services/donation-form.service';
import { Router, ActivatedRoute } from '@angular/router';
import {WebStorageService} from "@shared/services/web-storage.service";

@Component({
  selector: 'app-choose-flow',
  templateUrl: './choose-flow.component.html',
  styleUrls: ['./choose-flow.component.scss']
})
export class ChooseFlowComponent implements OnInit {

  chooseFlowForm: FormGroup;
  donorOption: string;

  constructor(
    private donationService: DonationFormService,
    private webStorageService: WebStorageService,
    private router: Router,
    private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.donorOption = this.webStorageService.getDonationOption();
    this.chooseFlowForm = new FormGroup({
      donorOption: new FormControl(this.donorOption, Validators.required),
      recaptcha: new FormControl('', Validators.required)
    });
  }


  next() {
    this.webStorageService.setDonationOption(this.chooseFlowForm.get('donorOption').value);
    this.router.navigate(['./origin'], {relativeTo: this.route});
  }

}
