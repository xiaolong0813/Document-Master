import { Component, OnInit } from '@angular/core';
import {pdfFile} from "../models/pdfFile";
import {EmitorService} from "../services/emitor.service";
import {FileService} from "../services/file.service";

@Component({
  selector: 'app-popup',
  templateUrl: './popup.component.html',
  styleUrls: ['./popup.component.scss']
})
export class PopupComponent implements OnInit {

  files : pdfFile[];
  display : boolean;
  header: string;
  // percentage: number;
  // speed: number = 1.2;

  constructor(
    private emitorService : EmitorService,
    private fileService : FileService,
  ) { }

  ngOnInit() {
    this.uploadEvent();
  }

  uploadEvent() {
    this.emitorService.pdfEmitor.subscribe(
      files => {
        this.header = "开始上传";
        // console.log(files.getAll("upFiles")[0])
        this.files = files.getAll("upFiles")
        this.files.forEach(file => {
          file.filename = file["name"];
          // file.size = Number(file["size"] / 1000000).toFixed(2);
          file.status = 1;
          file.percent = 0;
          file.speed = 0;
        });
        this.fileService.uploadPDF(files).subscribe(
          mes => {
            console.log("message data is : " + mes.data)
            if (mes.status_code == 200) {
              this.emitorService.uploadDoneEmitor.next(true);
              // this.getPDFFiles();
              // console.log("message data is : " + mes.data)
            }
          }
        )
      }
    )
  }

}
