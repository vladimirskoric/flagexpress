import { Component, OnInit } from '@angular/core';
import { FormGroup, Validators, FormControl } from '@angular/forms';
import { DonationFormService } from '@shared/services/donation-form.service';
import { Router, ActivatedRoute } from '@angular/router';
import {WebStorageService} from '@shared/services/web-storage.service';

@Component({
  selector: 'app-origin',
  templateUrl: './origin.component.html',
  styleUrls: ['./origin.component.scss']
})
export class OriginComponent implements OnInit {

  originForm: FormGroup;
  origin: string;

  constructor(
    private donationService: DonationFormService,
    private router: Router,
    private route: ActivatedRoute,
    private webStorageService: WebStorageService) { }

  ngOnInit(): void {
    this.origin = this.webStorageService.getDonationOrigin();
    this.originForm = new FormGroup({
      origin: new FormControl(this.origin, Validators.required)
    });
  }

  next() {
    this.webStorageService.setDonationOrigin(this.originForm.get('origin').value);
    this.router.navigate(['../../donation-form'], {relativeTo: this.route});
  }

}
