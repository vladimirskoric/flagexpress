import { Component } from '@angular/core';
import {MatDialogRef} from '@angular/material/dialog';

@Component({
  selector: 'app-cancel-dialog',
  templateUrl: './cancel-dialog.component.html',
  styleUrls: ['./cancel-dialog.component.scss']
})
export class CancelDialogComponent {

  constructor(
    private dialogRef: MatDialogRef<CancelDialogComponent>) {
  }
  public didConfirm(result): void {
    this.dialogRef.close(result);
  }
}
