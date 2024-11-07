import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './home.component';
import { LandingComponent } from './landing/landing.component';

const routes: Routes = [
  { path: '', component: HomeComponent,
    children: [
      { path: '', component: LandingComponent },
      {
        path: 'application',
        loadChildren: () => import('./application/application.module').then(m => m.ApplicationModule)
      }
    ]
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class HomeRoutingModule { }
