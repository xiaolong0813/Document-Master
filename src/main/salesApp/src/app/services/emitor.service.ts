import { Injectable } from '@angular/core';
import {Observable, Subject} from "rxjs";
import {pdfFile} from "../models/pdfFile";
import {FileService} from "./file.service";

@Injectable({
  providedIn: 'root'
})
export class EmitorService {

  public pdfEmitor = new Subject<any>()

  public uploadDoneEmitor = new Subject<boolean>();


  // public pdfObsever$ : Observable<pdfFile[]>;

  // public uploadPDFFiles(filelist : FileList) {
  //   this.pdfEmitor.next(filelist);
  //   this.pdfObsever$ = this.pdfEmitor.pipe(
  //     (filelist : FileList) => this.loadService.upload()
  //   )
  // }

  constructor(private fileService : FileService) { }
}
