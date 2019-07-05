import {Component, OnInit, ViewChild} from '@angular/core';
import {ModalUploadComponent} from '../modal-upload/modal-upload.component';
import {BsModalRef, BsModalService} from 'ngx-bootstrap';
import {interval, Observable} from "rxjs";
import {pdfFile} from "../models/pdfFile";
import {current} from "codelyzer/util/syntaxKind";
import {EmitorService} from "../services/emitor.service";
import {FileService} from "../services/file.service";
import {MessageService} from "../services/message.service";

@Component({
  selector: 'app-trans-details',
  templateUrl: './trans-details.component.html',
  styleUrls: ['./trans-details.component.scss']
})
export class TransDetailsComponent implements OnInit {

  modalRef: BsModalRef;

  public folderID : number;

  public time = interval(1000);
  public time$

  files : pdfFile[];

  // files$ : Observable<pdfFile[]>;
  // filesTest: pdfFile[] = [
  //   {id: 1,name: "no1",status : 1, time: new Date().toLocaleDateString()},
  //   {id: 2,name: "no2",status : 1,time: new Date().toLocaleDateString()},
  //   {id: 3,name: "no3",status : 1, time: new Date().toLocaleDateString()}];

  constructor(
    private modalService: BsModalService,
    private emitorService : EmitorService,
    private fileService : FileService,
    private messageService : MessageService,
  ) { }

  ngOnInit() {
    this.folderID = 0;

    // this.uploadEvent();
    this.getUploadEvent();

    // * should set to timer here
    this.getPDFFiles();
    // this.emitorService.pdfEmitor.asObservable().subscribe(
    //   filelist => {
    //     this.files = filelist;
    //   }
    // )
    // this.files$ = this.emitorService.pdfEmitor.pipe(
    //   (filelist) => this.files = filelist
    // )
  }

  // createTimer() {
  //   this.time
  // }

  getUploadEvent() {
    this.emitorService.uploadDoneEmitor.subscribe(
      upload => {
        if (upload) {
          console.log("Upload finished!")
          this.getPDFFiles();
        }
      }
    )
  }

  getPDFFiles() {
    this.fileService.getPDFFiles(this.folderID).subscribe(
      files => {
        this.files = files;
      }
    )
  }

  openModelWithComponent() {
    const initialState = {
      folderId : this.folderID,
      title: '上传文件',
      filetype: 'pdf'
    };
    // open popup with modalService with init state settings
    // initialState will be get in modal component
    this.modalRef = this.modalService.show(ModalUploadComponent, {initialState});

  }

  removeAll() {
    if (confirm("确定要删除所有文件吗？(所有已翻译数据也会一并删除）")) {
      this.fileService.removeAllPDFs(this.folderID).subscribe(
        mes => {
          if (mes.status_code == 200) {
            this.getPDFFiles();
            this.messageService.new_alert(1, "所有文件已被删除")
            return;
          }
        }
      )
    }
  }

  translateAll() {

  }

  downloadAll() {

  }

  setting(file: pdfFile) {
    
  }

  translate(file: pdfFile) {
    
  }
}
