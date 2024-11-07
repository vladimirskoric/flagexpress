import { Component, OnInit } from '@angular/core';
import { DataTable } from '../../_shared/model/data-table';

@Component({
  selector: 'app-request-list',
  templateUrl: './request-list.component.html',
  styleUrls: ['./request-list.component.scss']
})
export class RequestListComponent implements OnInit {

  tableData: DataTable[] = [
    {
      refNo: {
        id: 1,
        request: 'Request'
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
    {
      refNo: {
        id: 2,
        request: 'Request'
      },
      pickupDay: {
        date: 'April 01,2020',
        schedule: 'Morning (8-12NN)'
      },
      donationItem: [
        { name: 'Food', qty: '10', unit: 'Boxes' },
      ],
      estimatedWeight: '30 kg',
      donor: {
        fullname: 'Little John',
        contact: '9231111112',
        email: 'test@email.com',
      },
      pickupLocation: {
        city: 'Makati City',
        address: '1227, 102 Valero, Makati, 1227 Kalakhang Maynila'
      },
      recipient: {
        fullname: 'Jane Doe',
        contact: '9231111113'
      },
      dropoffLocation: {
        city: 'Makati City',
        address: '16796 Ayala Ave, Legazpi Village, Makati, 1229 Metro Manila'
      }
    },
    {
      refNo: {
        id: 3,
        request: 'Request'
      },
      pickupDay: {
        date: 'April 01,2020',
        schedule: 'Morning (8-12NN)'
      },
      donationItem: [
        { name: 'Test Kits', qty: '10', unit: 'Boxes' },
      ],
      estimatedWeight: '3 kg',
      donor: {
        fullname: 'Jane Doe',
        contact: '9231111113',
        email: 'test@email.com',
      },
      pickupLocation: {
        city: 'Makati City',
        address: '6796 Ayala Ave, Legazpi Village, Makati, 1229 Metro Manila'
      },
      recipient: {
        fullname: 'John Doe',
        contact: '9231111114'
      },
      dropoffLocation: {
        city: 'Makati City',
        address: '690 Makati Ave, Makati, 1200 Metro Manila'
      }
    },
    {
      refNo: {
        id: 4,
        request: 'Request'
      },
      pickupDay: {
        date: 'April 02,2020',
        schedule: 'Morning (8-12NN)'
      },
      donationItem: [
        { name: 'Water', qty: '10', unit: 'Boxes' },
      ],
      estimatedWeight: '30 kg',
      donor: {
        fullname: 'Jane Doe',
        contact: '9231111113',
        email: 'test@email.com',
      },
      pickupLocation: {
        city: 'Makati City',
        address: '6796 Ayala Ave, Legazpi Village, Makati, 1229 Metro Manila'
      },
      recipient: {
        fullname: 'Sherlock holmes',
        contact: '9231111115'
      },
      dropoffLocation: {
        city: 'Makati City',
        address: '690 Makati Ave, Makati, 1200 Metro Manila'
      }
    },
    {
      refNo: {
        id: 5,
        request: 'Request'
      },
      pickupDay: {
        date: 'April 02,2020',
        schedule: 'Morning (8-12NN)'
      },
      donationItem: [
        { name: 'Medical Supplies', qty: '10', unit: 'Boxes' },
      ],
      estimatedWeight: '6 kg',
      donor: {
        fullname: 'Sherlock holmes',
        contact: '9231111115',
        email: 'test@email.com',
      },
      pickupLocation: {
        city: 'Makati City',
        address: 'St. corner, 746 J.P. Rizal, Antipolo, Makati, Metro Manila'
      },
      recipient: {
        fullname: 'John Watson',
        contact: '9231111116'
      },
      dropoffLocation: {
        city: 'Makati City',
        address: '3293 N Evangelista, Makati, 1204 Metro Manila'
      }
    },
    {
      refNo: {
        id: 6,
        request: 'Request'
      },
      pickupDay: {
        date: 'April 02,2020',
        schedule: 'Morning (8-12NN)'
      },
      donationItem: [
        { name: 'PPE', qty: '10', unit: 'Boxes' },
      ],
      estimatedWeight: '3 kg',
      donor: {
        fullname: 'John Watson',
        contact: '9231111116',
        email: 'test@email.com',
      },
      pickupLocation: {
        city: 'Makati City',
        address: '3293 N Evangelista, Makati, 1204 Metro Manila'
      },
      recipient: {
        fullname: 'Harry Potter',
        contact: '9231111117'
      },
      dropoffLocation: {
        city: 'Makati City',
        address: '3 Humabon, Makati, 1232 Kalakhang Maynila'
      }
    },
    {
      refNo: {
        id: 7,
        request: 'Request'
      },
      pickupDay: {
        date: 'April 03,2020',
        schedule: 'Morning (8-12NN)'
      },
      donationItem: [
        { name: 'Food', qty: '10', unit: 'Boxes' },
      ],
      estimatedWeight: '30 kg',
      donor: {
        fullname: 'Harry Potter',
        contact: '9231111117',
        email: 'test@email.com',
      },
      pickupLocation: {
        city: 'Makati City',
        address: '3 Humabon, Makati, 1232 Kalakhang Maynila'
      },
      recipient: {
        fullname: 'Ron Weasley',
        contact: '9231111118'
      },
      dropoffLocation: {
        city: 'Makati City',
        address: '1212 Sampaguita St, Makati, 1217 Metro Manila'
      }
    },
    {
      refNo: {
        id: 8,
        request: 'Request'
      },
      pickupDay: {
        date: 'April 03,2020',
        schedule: 'Morning (8-12NN)'
      },
      donationItem: [
        { name: 'Test Kits', qty: '10', unit: 'Boxes' },
      ],
      estimatedWeight: '3 kg',
      donor: {
        fullname: 'Ron Weasley',
        contact: '9231111118',
        email: 'test@email.com',
      },
      pickupLocation: {
        city: 'Makati City',
        address: '1212 Sampaguita St, Makati, 1217 Metro Manila'
      },
      recipient: {
        fullname: 'Bilbo Baggins',
        contact: '9231111119'
      },
      dropoffLocation: {
        city: 'Makati City',
        address: '35 J.P Rizal Street, Makati, 1204 Metro Manila'
      }
    },
    {
      refNo: {
        id: 9,
        request: 'Request'
      },
      pickupDay: {
        date: 'April 03,2020',
        schedule: 'Morning (8-12NN)'
      },
      donationItem: [
        { name: 'Water', qty: '10', unit: 'Boxes' },
      ],
      estimatedWeight: '30 kg',
      donor: {
        fullname: 'Bilbo Baggins',
        contact: '9231111119',
        email: 'test@email.com',
      },
      pickupLocation: {
        city: 'Makati City',
        address: '35 J.P Rizal Street, Makati, 1204 Metro Manila'
      },
      recipient: {
        fullname: 'Terry Crews',
        contact: '9231111120'
      },
      dropoffLocation: {
        city: 'Makati City',
        address: 'McKinley Exchange Corporate Center, McKinley Rd, corner Epifanio de los Santos Ave, Makati, Metro Manila'
      }
    },
    {
      refNo: {
        id: 10,
        request: 'Request'
      },
      pickupDay: {
        date: 'April 04,2020',
        schedule: 'Morning (8-12NN)'
      },
      donationItem: [
        { name: 'Medical Supplies', qty: '10', unit: 'Boxes' },
      ],
      estimatedWeight: '6 kg',
      donor: {
        fullname: 'Terry Crews',
        contact: '9231111120',
        email: 'test@email.com',
      },
      pickupLocation: {
        city: 'Makati City',
        address: 'McKinley Exchange Corporate Center, McKinley Rd, corner Epifanio de los Santos Ave, Makati, Metro Manila'
      },
      recipient: {
        fullname: 'Big Bert',
        contact: '9231111111'
      },
      dropoffLocation: {
        city: 'Makati City',
        address: '2021 Kalayaan Ave,Makati'
      }
    },
  ];

  constructor() { }

  ngOnInit(): void {
  }

}
