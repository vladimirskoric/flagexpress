import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MaterialModule } from '../_shared/material/material.module';
import { DashboardRoutingModule } from './dashboard-routing.module';
import { DashboardComponent } from './dashboard.component';
import { ToolbarComponent } from '../_shared/components/toolbar/toolbar.component';
import { TabsComponent } from '../_shared/components/tabs/tabs.component';
import { FooterComponent } from '../_shared/components/footer/footer.component';
import { AllListComponent } from './all-list/all-list.component';
import { DataTableComponent } from '../_shared/components/data-table/data-table.component';
import { DateAdapter, MAT_DATE_FORMATS, MAT_DATE_LOCALE, SatDatepickerModule, SatNativeDateModule } from 'saturn-datepicker';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RequestListComponent } from './request-list/request-list.component';
import { PickUpListComponent } from './pickup-list/pickup-list.component';
import { CompletedListComponent } from './completed-list/completed-list.component';
import { IssuesListComponent } from './issues-list/issues-list.component';
import { MAT_MOMENT_DATE_FORMATS, MomentDateAdapter } from '@angular/material-moment-adapter';
import {ViewDetailsComponent} from '../_shared/components/action-dialog/view-details/view-details.component';


@NgModule({
  declarations: [
    DashboardComponent,
    ToolbarComponent,
    TabsComponent,
    FooterComponent,
    AllListComponent,
    DataTableComponent,
    RequestListComponent,
    ViewDetailsComponent,
    PickUpListComponent,
    CompletedListComponent,
    IssuesListComponent
  ],
  imports: [
    CommonModule,
    DashboardRoutingModule,
    MaterialModule,
    SatDatepickerModule,
    SatNativeDateModule,
    FormsModule,
    ReactiveFormsModule,
  ],
  providers: [
    { provide: DateAdapter, useClass: MomentDateAdapter, deps: [MAT_DATE_LOCALE] },
    { provide: MAT_DATE_FORMATS, useValue: MAT_MOMENT_DATE_FORMATS },
  ]
})
export class DashboardModule { }
