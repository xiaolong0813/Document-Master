import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {HomeComponent} from './home/home.component';
import {TranslationComponent} from './translation/translation.component';
import {TermComponent} from './term/term.component';
import {DatabaseComponent} from './database/database.component';
import {HelpComponent} from './help/help.component';
import {SettingsComponent} from './settings/settings.component';
import {TestComponent} from "./test/test.component";

const routes: Routes = [
  {path: '', redirectTo: '/home', pathMatch: 'full'},
  {path: 'home', component: HomeComponent},
  {path: 'trans', component: TranslationComponent},
  {path: 'term', component: TermComponent},
  {path: 'data', component: DatabaseComponent},
  {path: 'help', component: HelpComponent},
  {path: 'settings', component: SettingsComponent},

  {path: 'test', component: TestComponent}
];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
