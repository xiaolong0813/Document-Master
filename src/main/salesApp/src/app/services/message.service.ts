import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class MessageService {
  alert_code : number;
  public alert_msg: string;

  public new_alert(code : number, msg : string) {
    // console.log(this.alert_code)
    this.alert_code = code;
    this.alert_msg = msg;

    setTimeout(() => {
      this.alert_code = 0;
      this.alert_msg = null;
    }, 5000)
  }

  // * animated close
  public close_alert() {
    this.alert_code = 0;
  }

  constructor() { }
}
