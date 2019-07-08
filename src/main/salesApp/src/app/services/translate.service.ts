import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {Message} from "../models/message";
import {environment} from "../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {MessageService} from "./message.service";

@Injectable({
  providedIn: 'root'
})
export class TranslateService {

  private api = environment.apiBase + "/trans/";

  constructor(
    private http : HttpClient,
    private messageService : MessageService,
  ) { }

  translate(fileId : number) : Observable<Message> {
    return this.http.get<Message>(this.api + fileId);

  }



  translateAll(folderId : number) {

  }

}
