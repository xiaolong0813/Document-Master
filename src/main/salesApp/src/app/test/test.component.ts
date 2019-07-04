import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-test',
  templateUrl: './test.component.html',
  styleUrls: ['./test.component.scss']
})
export class TestComponent implements OnInit {

  isCollapsed = false;

  data = [
    {
      title: 'Alert Message Infor 1'
    },
    {
      title: 'Alert Message Infor 2'
    },
    {
      title: 'Alert Message Infor 3'
    },
    {
      title: 'Alert Message Infor 4'
    },
    {
      title: 'Alert Message Infor 5'
    }
  ];

  constructor() { }

  ngOnInit() {
  }

  // Collapse Buttion Handler
  handleCollapseAction() {
    this.isCollapsed = ! this.isCollapsed;
  }

}
