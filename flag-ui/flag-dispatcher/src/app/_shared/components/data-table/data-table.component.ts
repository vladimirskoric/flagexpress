import {Component, Input, OnInit, ViewChild} from '@angular/core';
import {MatTableDataSource} from '@angular/material/table';
import {MatSort} from '@angular/material/sort';
import {SelectionModel} from '@angular/cdk/collections';
import {MatPaginator} from '@angular/material/paginator';
import {FormBuilder, FormGroup} from '@angular/forms';
import {MatDialog} from '@angular/material/dialog';
import {ViewDetailsComponent} from '../action-dialog/view-details/view-details.component';
import {ApproveComponent} from '../action-dialog/approve/approve.component';
import {DeliveryComponent} from '../action-dialog/delivery/delivery.component';
import {IssueComponent} from '../action-dialog/issue/issue.component';
import pdfMake from 'pdfmake/build/pdfmake';
import pdfFonts from 'pdfmake/build/vfs_fonts';
import * as _ from 'lodash';

pdfMake.vfs = pdfFonts.pdfMake.vfs;

@Component({
  selector: 'app-data-table',
  templateUrl: './data-table.component.html',
  styleUrls: ['./data-table.component.scss']
})
export class DataTableComponent implements OnInit{

  filterForm: FormGroup;
  @Input() tableData;
  @ViewChild(MatSort, { static: true }) sort: MatSort;
  @ViewChild(MatPaginator, { static: true }) paginator: MatPaginator;

  public selectedContent = new MatTableDataSource<any>();
  public selectedRows = [];

  public rows = new MatTableDataSource<any>();
  public selection = new SelectionModel<any>(true, []);
  public paginatorOption = [5, 10, 25, 100];
  public objectKeys = Object.keys;
  public columnHeader = {
    select: 'select',
    refNo: 'Ref No.',
    pickupDay: 'Pick-up Day',
    donationItem: 'Donation Item/s',
    donor: 'Donor',
    pickupLocation: 'Pick-up Location',
    recipient: 'Recipient',
    dropoffLocation: 'Drop-off Location',
    action: 'action',
  };
  filterOptions = [
    { value: 'refNo', label: 'Ref No.'},
    { value: 'pickupDay', label: 'Pick-up Day'},
    { value: 'donor', label: 'Donor'},
    { value: 'donationItem', label: 'Donation Item/s'},
    { value: 'pickupLocation', label: 'Pick-up Location'},
    { value: 'recipient', label: 'Recipient'},
    { value: 'dropoffLocation', label: 'Drop-off Location'},
  ];

  actionOptions = [
    { value: 'view', label: 'VIEW DETAILS', inAction: false},
    { value: 'approve', label: 'APPROVE', inAction: true},
    { value: 'issue', label: 'MARK WITH ISSUES', inAction: true},
    { value: 'generate', label: 'GENERATE PDF', inAction: true},
  ];

  public maxDate = new Date();

  public constructor(
    private formBuilder: FormBuilder,
    private dialog: MatDialog) {}

  ngOnInit(): void {
    this.filterForm = this.formBuilder.group({
      filterBy: [''],
      date: [''],
    });
    this.updateTable();
    this.selection.clear();
  }

  public filterBy(event: any): void {
    const filterBy: string = event.target.value;
    this.rows.filter = filterBy;
  }

  public isAllSelected() {
    const numSelected = this.selection.selected.length;
    const numRows = this.rows.data.length;
    return numSelected === numRows;
  }

  public masterToggle() {
    if (this.isAllSelected()){
      this.selection.clear();
      this.selectedRows = [];
      this.rows.data.forEach(row => {
        row.highlighted = !row.highlighted;
      });
    }else{
      this.rows.data.forEach(row => {
        this.selection.select(row);
        const isInArray = this.selectedRows.find((item) => item === row) !== undefined;
        if (!isInArray) { this.selectedRows.push(row); }
        row.highlighted ? row.highlighted = true : row.highlighted = !row.highlighted;
      });
    }
  }

  public checkboxLabel(row?: any): string {

    return (!row)
      ? `${this.isAllSelected() ? 'select' : 'deselect'} all`
      : `${this.selection.isSelected(row) ? 'deselect' : 'select'} row ${row.position + 1}`;
  }

  private updateRows(): void {
    this.rows = new MatTableDataSource<any>(this.tableData);
    this.rows.sortingDataAccessor = (item, property) => {
      switch (property) {
        case 'refNo': return item.refNo.id;
        case 'pickupDay': return item.pickupDay.date;
        case 'donationItem': return item.donationItem;
        case 'donor': return item.donor.fullname;
        case 'pickupLocation': return item.pickupLocation.address;
        case 'recipient': return item.recipient.fullname;
        case 'dropoffLocation': return item.dropoffLocation.address;
        default: return item[property];
      }
    };
    this.rows.sort = this.sort;
    this.rows.paginator = this.paginator;
  }

  private updateTable(): void {
    if (this.tableData) {
      this.updateRows();
    }
  }

  public selectRow(row: any){
    this.selection.toggle(row);
    row.highlighted = !row.highlighted;
    const isInArray = this.selectedRows.find((item) => item === row) !== undefined;
    isInArray ? this.selectedRows.splice(this.selectedRows.indexOf(row), 1) : this.selectedRows.push(row);
  }

  public selectedRowAction(row: any, action: any){
    const selectedRow = [];
    selectedRow.push(row);
    this.selectedContent = new MatTableDataSource<any>(selectedRow);
    const config = {
      minWidth: '60vw',
      height: '95vh',
      panelClass: 'custom-dialog-container',
      data: {
        details: row,
      }
    };
    switch (action.value) {
      case 'view': {
        const dialogRef = this.dialog.open(ViewDetailsComponent, config);
        dialogRef.afterClosed().subscribe((data: any) => {
          console.log(data);
        });
        break;
      }
      case 'approve': {

        const dialogRef = this.dialog.open(ApproveComponent, config);
        dialogRef.afterClosed().subscribe((data: any) => {
          console.log(data);
        });
        break;
      }
      case 'issue': {

        const dialogRef = this.dialog.open(IssueComponent, config);
        dialogRef.afterClosed().subscribe((data: any) => {
          console.log(data);
        });
        break;
      }
      case 'generate': {
        this.generatePdf('open', this.selectedContent);
        break;
      }
      default: {
        break;
      }
    }
  }

  public selectedAction(event: any){
    const action = event.value;
    const config = {
      minWidth: '60vw',
      height: '95vh',
      panelClass: 'custom-dialog-container',
      data: {
        details: this.selectedRows,
      }
    };
    switch (action.value) {
      case 'approve': {
        const dialogRef = this.dialog.open(ApproveComponent, config);
        dialogRef.afterClosed().subscribe((data: any) => {
          console.log(data);
        });
        break;
      }
      case 'issue': {
        const dialogRef = this.dialog.open(IssueComponent, config);
        dialogRef.afterClosed().subscribe((data: any) => {
          console.log(data);
        });
        break;
      }
      case 'generate': {
        this.selectedContent = new MatTableDataSource<any>(this.selectedRows);
        this.generatePdf('open', this.selectedContent);
        break;
      }
      default: {
        break;
      }
    }
  }

  private generatePdf(action = 'open', selectedData){
    const docDefinition = this.getDocDefinition(selectedData.data);

    switch (action) {
      case 'open': pdfMake.createPdf(docDefinition).open(); break;
      case 'print': pdfMake.createPdf(docDefinition).print(); break;
      case 'download': pdfMake.createPdf(docDefinition).download(); break;

      default: pdfMake.createPdf(docDefinition).open(); break;
    }
  }

  private getDocDefinition(selectedContentData){
    const donorItems = [];
    const donorItemsQty = [];
    selectedContentData.forEach( data => {
      data.donationItem.forEach( item => {
        donorItems.push(item.name);
        donorItemsQty.push(item.qty);
      });
    });
    return {
      pageOrientation: 'landscape',
      content: [
        {
          text: 'FLAG Dispatcher',
          bold: true,
          fontSize: 20,
          alignment: 'center',
          margin: [0, 0, 0, 20]
        },
        {
          layout: 'lightHorizontalLines',
          table: {
            headerRows: 1,
            widths: [ 'auto', 'auto', 'auto', 'auto', 'auto', 'auto', 'auto', 'auto', 'auto' ],
            body: [
              [
                { text: 'Ref No.', style: 'tableHeader'},
                { text: 'Status', style: 'tableHeader' },
                { text: 'Pick-up Day', style: 'tableHeader'},
                { text: 'Donation Item\'s', style: 'tableHeader'},
                { text: 'Quantity', style: 'tableHeader'},
                { text: 'Donor', style: 'tableHeader'},
                { text: 'Pick-up Location', style: 'tableHeader'},
                { text: 'Recipient', style: 'tableHeader'},
                { text: 'Drop-off Location', style: 'tableHeader'}],
              ...selectedContentData.map(item => {
                return [ item.refNo.id,
                  item.refNo.request,
                  item.pickupDay.date + ', ' +  item.pickupDay.schedule,
                  donorItems,
                  donorItemsQty,
                  item.donor.fullname + ', ' + item.donor.contact,
                  item.pickupLocation.address + ', ' + item.pickupLocation.city,
                  item.recipient.fullname + ', ' + item.recipient.contact,
                  item.dropoffLocation.address + ', ' + item.dropoffLocation.city
                ];
              })
            ]
          }
        }
      ]
    };
  }
}
