import {Component, OnInit, TemplateRef, ViewChild} from '@angular/core';
import {uploadFile} from "../models/uploadFile";


@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.scss']
})
export class SidebarComponent implements OnInit {
  fileNum: number;

  pdffiles : uploadFile[];



  constructor() { }

  ngOnInit() {
  }



}
