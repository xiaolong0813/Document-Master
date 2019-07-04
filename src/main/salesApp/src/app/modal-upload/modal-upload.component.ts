import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {BsModalRef} from 'ngx-bootstrap';
import {FileService} from "../services/file.service";
import {forkJoin, Observable} from "rxjs";
import {EmitorService} from "../services/emitor.service";
import {MessageService} from "../services/message.service";
import {el} from "@angular/platform-browser/testing/src/browser_util";

@Component({
  selector: 'app-modal-upload',
  templateUrl: './modal-upload.component.html',
  // template: `
  //
  //   `
  // styleUrls: ['./modal-upload.component.scss']
})
export class ModalUploadComponent {

  title: string;
  // filetype is set in initialState
  filetype: string;
  // 监视名为files的input元素
  @ViewChild('fileInput') fileInput: ElementRef;
  // 存放上传文件,每次建立新的set
  uploadFiles : Set<File> = new Set();

  // FileList is List-type
  fileList : FileList;
  // 存放返回
  // progress = {};
  formData : FormData = new FormData();

  constructor(
    private modalRef: BsModalRef,
    private fileService: FileService,
    private emitorService : EmitorService,
    private messageService : MessageService,
  ) { }

  ngOnInit() {
    // console.log(this.filetype)
  }


  // read input uploaded files
  handleFileInput() {
    // clear current uploadFiles set, or previous ones would be remained
    this.uploadFiles.clear();
    // this.fileInput.nativeElement.files获取的是FileList类型
    const files : {[key: string] : File} = this.fileInput.nativeElement.files;
    // console.log(typeof files)
    // 只提取FileList里面的File类型（还包含其他类型），存入本地files变量
    for (let key in files) {
      // console.log(key)
      if (!isNaN(parseInt(key))) {
        this.uploadFiles.add(files[key])
      }
    }

    this.fileList = this.fileInput.nativeElement.files;

  }

  submitUploadEvent() {
    // this.progress = this.fileService.upload(this.uploadFiles);
    // console.log(this.progress);
    // const allProgressed = [];
    // const fileDict = {};
    if (this.filetype == 'pdf') {
      // check if all files are pdf formats
      this.uploadFiles.forEach(file => {
        if (file.type != 'application/pdf'){
          this.messageService.new_alert(-1, "请选择.pdf格式文件")
          return;
        }
      });
      // * dict type can be used?
      // fileDict['file'] = this.uploadFiles;
      // fileDict['type'] = 0;
      for (let i = 0; i < this.fileList.length; i++) {
        // add to FormDataEntryValue[]
        this.formData.append("upFiles", this.fileList.item(i))
      }
      // to get all FormDataEntryValue type data
      // console.log(this.formData.getAll("upFiles"));

      this.emitorService.pdfEmitor.next(this.formData);

      this.closeModal();
    }
    else if (this.filetype == 'voca') {
      return
    }
  }

  closeModal() {

    // this.formData = new FormData();
    // this.fileToUpload = null;
    // this.selectedTypeId = 0;
    this.modalRef.hide();

  }
}
