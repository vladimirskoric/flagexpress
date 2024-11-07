import { Component, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { ActivatedRoute, NavigationEnd, Router } from '@angular/router';
import { filter } from 'rxjs/operators';
import { NavigationService } from '@shared/services/navigation.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {

  constructor(private router: Router,
              private activatedRoute: ActivatedRoute,
              private titleService: Title,
              private navigationService: NavigationService) {
  }

  public ngOnInit(): void {
    this.router.events.pipe(filter(event => event instanceof NavigationEnd))
      .subscribe(() => {
        const activeRoute = this.getChild(this.activatedRoute);
        activeRoute.data.subscribe(data => {
          this.titleService.setTitle(data.title || 'FLAGexpress.ph');
          this.navigationService.setTitle(data);
        });
      });
  }

  public getChild(activatedRoute: ActivatedRoute): ActivatedRoute {
    if (activatedRoute.firstChild) {
      return this.getChild(activatedRoute.firstChild);
    } else {
      return activatedRoute;
    }

  }
}
