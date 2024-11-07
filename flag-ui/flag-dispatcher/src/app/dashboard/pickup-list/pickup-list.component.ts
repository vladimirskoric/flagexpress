import { Component, OnInit } from '@angular/core';
import { DataTable } from '../../_shared/model/data-table';

@Component({
  selector: 'app-pickup-list',
  templateUrl: './pickup-list.component.html',
  styleUrls: ['./pickup-list.component.scss']
})
export class PickUpListComponent implements OnInit {

  tableData: DataTable[] = [
    {
      refNo: {
        id: 3,
        request: 'Approved'
      },
      pickupDay: {
        date: 'April 01,2020',
        schedule: 'Morning (8-12NN)'
      },
      donationItem: [
        { name: 'PPE', qty: '10', unit: 'Boxes' },
        { name: 'Gloves', qty: '5', unit: 'Boxes' },
      ],
      estimatedWeight: '3 kg',
      donor: {
        fullname: 'Big Bert',
        contact: '9231111111',
        email: 'test@email.com',
      },
      pickupLocation: {
        city: 'Makati City',
        address: '2021 Kalayaan Ave,Makati'
      },
      recipient: {
        fullname: 'Little John',
        contact: '9231111112'
      },
      dropoffLocation: {
        city: 'Makati City',
        address: '1227, 102 Valero, Makati, 1227 Kalakhang Maynila'
      }
    },
  ];

  constructor() { }

  ngOnInit(): void {
  }

}