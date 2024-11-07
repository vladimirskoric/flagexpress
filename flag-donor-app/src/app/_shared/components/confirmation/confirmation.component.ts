import { Component } from '@angular/core';
import {MatDialogRef} from '@angular/material/dialog';

@Component({
  selector: 'app-confirmation',
  templateUrl: './confirmation.component.html',
  styleUrls: ['./confirmation.component.scss']
})
export class ConfirmationComponent {

  constructor(
    private dialogRef: MatDialogRef<ConfirmationComponent>) {
  }
  public didConfirm(result): void {
    this.dialogRef.close(result);
  }
}
