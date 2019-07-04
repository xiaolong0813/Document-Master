import { Injectable } from '@angular/core';
import {Observable, interval, of} from "rxjs";
import {take} from "rxjs/operators";
import {pdfFile} from "../models/pdfFile";
import {HttpClient} from "@angular/common/http";
import {MessageService} from "./message.service";
import {Message} from "../models/message";
import {environment} from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class FileService {

  private api = environment.apiBase + '/file/';

  constructor(
    private http : HttpClient,
    private messageService : MessageService,
  ) { }

  // return
  public uploadPDF(uploadFiles: FormData): Observable<Message>{
    // const status = {};
    // uploadFiles.forEach(file => {
    //   status[file.name] = { progress: interval(1000)}
    // });
    return this.http.post<Message>(this.api + 'uploadpdf', uploadFiles);
  }

  // public upload(uploadFiles: Set<File>): Observable<pdfFile[]> {
  //   if (uploadFiles.size == 0) {
  //     return of([]);
  //   }
  //   // return uploadFiles
  //
  //   // uploadFiles.forEach(file => {
  //   //   status[file.name] = { progress: interval(1000)}
  //   // });
  //   // return status;
  // }

  getPDFFiles() : Observable<pdfFile[]>{
    return this.http.get<pdfFile[]>(this.api + "getPDFs")

  }
}
