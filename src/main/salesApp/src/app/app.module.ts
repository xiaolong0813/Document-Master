import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { BrowserAnimationsModule} from "@angular/platform-browser/animations";
// import {NgZorroAntdModule, NZ_I18N, zh_CN} from "ng-zorro-antd";

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {FormsModule} from '@angular/forms';
import {ModalModule} from 'ngx-bootstrap';
import {NgxDatatableModule} from '@swimlane/ngx-datatable';
import {NgSelectModule} from '@ng-select/ng-select';
import { TranslationComponent } from './translation/translation.component';
import { DatabaseComponent } from './database/database.component';
import { TermComponent } from './term/term.component';
import { HomeComponent } from './home/home.component';
import { HelpComponent } from './help/help.component';
import { SettingsComponent } from './settings/settings.component';
import { ModalUploadComponent } from './modal-upload/modal-upload.component';
import { ModalTransComponent } from './modal-trans/modal-trans.component';
import { TransDetailsComponent } from './trans-details/trans-details.component';
import { SidebarComponent } from './sidebar/sidebar.component';

import { registerLocaleData} from "@angular/common";
import zh from '@angular/common/locales/zh';

import { HttpClientModule} from "@angular/common/http";
import { TestComponent } from './test/test.component';
import { MessageComponent } from './message/message.component';
import { PopupComponent } from './popup/popup.component';

// import { FlexLayoutModule} from "@angular/flex-layout";

@NgModule({
  declarations: [
    AppComponent,
    TranslationComponent,
    DatabaseComponent,
    TermComponent,
    HomeComponent,
    HelpComponent,
    SettingsComponent,
    ModalUploadComponent,
    ModalTransComponent,
    TransDetailsComponent,
    SidebarComponent,
    TestComponent,
    MessageComponent,
    PopupComponent,
  ],
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,

    ModalModule.forRoot(),
    NgxDatatableModule,
    NgSelectModule,

    HttpClientModule,

    // NgZorroAntdModule,
    BrowserAnimationsModule,

    // 需要更新到最新版本
    // FlexLayoutModule
  ],

  entryComponents: [
    ModalUploadComponent,
    ModalTransComponent,
  ],

  /** 配置 ng-zorro-antd 国际化（文案 及 日期） **/
  providers: [
    // {provide: NZ_I18N, useValue: zh_CN}
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
