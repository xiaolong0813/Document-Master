import { Injectable } from '@angular/core';
import {Observable, interval, of} from "rxjs";
import {take} from "rxjs/operators";
import {uploadFile} from "../models/uploadFile";
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
  public upload(uploadFiles: FormData): Observable<Message[]>{
    // const status = {};
    // uploadFiles.forEach(file => {
    //   status[file.name] = { progress: interval(1000)}
    // });
    return this.http.post<Message[]>(this.api + 'upload', uploadFiles);
  }

  // public upload(uploadFiles: Set<UploadFile>): Observable<uploadFile[]> {
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

  getPDFFiles(folderId : number) : Observable<uploadFile[]>{
    return this.http.get<uploadFile[]>(this.api + "getPDFs/" + folderId);
  }

  removeAllPDFs(folderId : number) : Observable<Message>{
    return this.http.delete<Message>(this.api + folderId);
  }
}
