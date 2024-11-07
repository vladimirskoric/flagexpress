import { Component, OnInit, Input } from '@angular/core';
import { DonationFormService } from '@shared/services/donation-form.service';
import { WebStorageService } from '@shared/services/web-storage.service';

@Component({
  selector: 'app-donor-complete',
  templateUrl: './donor-complete.component.html',
  styleUrls: ['./donor-complete.component.scss']
})
export class DonorCompleteComponent implements OnInit {

  ref_code: string;
  constructor(private webStorageService: WebStorageService) { }

  ngOnInit(): void {
    this.ref_code = this.webStorageService.getReferenceCode();
  }

}
