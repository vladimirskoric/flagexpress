import {Component, OnInit, ViewChild} from '@angular/core';
import {MatTabChangeEvent} from '@angular/material/tabs';

@Component({
  selector: 'app-tabs',
  templateUrl: './tabs.component.html',
  styleUrls: ['./tabs.component.scss']
})
export class TabsComponent implements OnInit {
  @ViewChild('tabGroup') tabGroup;

  constructor() { }

  ngOnInit(): void {
  }

  public tabChanged(tabChangeEvent: MatTabChangeEvent): void {
    console.log(tabChangeEvent);
  }

}
