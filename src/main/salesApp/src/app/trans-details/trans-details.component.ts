import {Component, OnInit, ViewChild} from '@angular/core';
import {ModalUploadComponent} from '../modal-upload/modal-upload.component';
import {BsModalRef, BsModalService} from 'ngx-bootstrap';
import {interval, Observable} from "rxjs";
import {pdfFile} from "../models/pdfFile";
import {current} from "codelyzer/util/syntaxKind";
import {EmitorService} from "../services/emitor.service";
import {FileService} from "../services/file.service";

@Component({
  selector: 'app-trans-details',
  templateUrl: './trans-details.component.html',
  styleUrls: ['./trans-details.component.scss']
})
export class TransDetailsComponent implements OnInit {

  modalRef: BsModalRef;

  @ViewChild('transDetail')
  private transDetail: TransDetailsComponent;

  public time = interval(1000);

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
  ) { }

  ngOnInit() {
    this.newPDFEvent();

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

  getPDFFiles() {
    this.fileService.getPDFFiles().subscribe(
      files => {
        this.files = files;
      }
    )
  }

  newPDFEvent() {
    this.emitorService.pdfEmitor.subscribe(
      files => {
        this.fileService.uploadPDF(files).subscribe(
          mes => {
            console.log("message data is : " + mes.data)
            if (mes.status_code == 200) {
              this.getPDFFiles();
              // console.log("message data is : " + mes.data)
            }
          }
        )
      }
    )
  }

  openModelWithComponent() {
    const initialState = {
      title: '上传文件',
      filetype: 'pdf'
    };
    // open popup with modalService with init state settings
    // initialState will be get in modal component
    this.modalRef = this.modalService.show(ModalUploadComponent, {initialState});

  }

  removeAll() {

  }

  getXMLs() {

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
