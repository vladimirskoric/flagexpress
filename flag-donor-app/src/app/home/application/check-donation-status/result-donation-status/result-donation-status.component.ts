import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router, NavigationStart } from '@angular/router';

@Component({
  selector: 'app-result-donation-status',
  templateUrl: './result-donation-status.component.html',
  styleUrls: ['./result-donation-status.component.scss']
})
export class ResultDonationStatusComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
    let resolved = history.state.data;
    console.log("Resolved: ", resolved);
  }

}
