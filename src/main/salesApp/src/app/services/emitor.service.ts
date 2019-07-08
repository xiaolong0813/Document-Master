import { Injectable } from '@angular/core';
import {Observable, Subject} from "rxjs";
import {uploadFile} from "../models/uploadFile";
import {FileService} from "./file.service";

@Injectable({
  providedIn: 'root'
})
export class EmitorService {

  public pdfEmitor = new Subject<any>()

  public uploadDoneEmitor = new Subject<boolean>();


  // public pdfObsever$ : Observable<uploadFile[]>;

  // public uploadPDFFiles(filelist : FileList) {
  //   this.pdfEmitor.next(filelist);
  //   this.pdfObsever$ = this.pdfEmitor.pipe(
  //     (filelist : FileList) => this.loadService.upload()
  //   )
  // }

  constructor(private fileService : FileService) { }
}
