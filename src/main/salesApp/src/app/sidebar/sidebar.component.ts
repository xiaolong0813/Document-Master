import {Component, OnInit, TemplateRef, ViewChild} from '@angular/core';
import {pdfFile} from "../models/pdfFile";


@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.scss']
})
export class SidebarComponent implements OnInit {
  fileNum: number;

  pdffiles : pdfFile[];



  constructor() { }

  ngOnInit() {
  }



}
