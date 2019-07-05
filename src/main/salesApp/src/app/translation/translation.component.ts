import {Component, OnInit, ViewChild} from '@angular/core';
import {TransDetailsComponent} from "../trans-details/trans-details.component";

@Component({
  selector: 'app-translation',
  templateUrl: './translation.component.html',
  styleUrls: ['./translation.component.scss']
})
export class TranslationComponent implements OnInit {

  @ViewChild('transDetail')
  private transDetail: TransDetailsComponent;

  constructor(
  ) { }

  ngOnInit() {
  }


  getXMLs() {

  }
}
