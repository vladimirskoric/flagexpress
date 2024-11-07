import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialog, MatDialogRef} from '@angular/material/dialog';
import {ConfirmationDialogComponent} from '../confirmation-dialog/confirmation-dialog.component';
import {DonationItem} from '../../../model/donation-item';


@Component({
  selector: 'app-view-details',
  templateUrl: './view-details.component.html',
  styleUrls: ['./view-details.component.scss']
})
export class ViewDetailsComponent implements OnInit {
  displayedColumns: string[] = ['name', 'qty'];
  dataSource: DonationItem[];

  actionOptions = [
    { value: 'approve', label: 'APPROVE'},
    { value: 'issue', label: 'MARK WITH ISSUES'},
    { value: 'generate', label: 'GENERATE PDF'},
  ];

  constructor(
    @Inject(MAT_DIALOG_DATA) public data: any,
    public dialogRef: MatDialogRef<ViewDetailsComponent>,
    private dialog: MatDialog
  ) {
    dialogRef.disableClose = true;
  }

  ngOnInit(): void {
    console.log(this.data);
    this.dataSource = this.data.details.donationItem;
  }

  public selectedConfirmationAction(details: any, action: any){
    console.log(action);

    switch (action.value) {
      case 'approve': {

        const config = {
          data: {
            title: 'Confirm',
            message: 'Are you sure you want to approved?',
            details: action,
          }
        };
        const dialogRef = this.dialog.open(ConfirmationDialogComponent, config);
        dialogRef.afterClosed().subscribe((confirm: any) => {
          if (confirm){
            this.dialogRef.close(confirm);
          }
        });
        break;
      }
      case 'issue': {

        break;
      }
      case 'generate': {

        break;
      }
      default: {
        break;
      };
    }


  }

}
