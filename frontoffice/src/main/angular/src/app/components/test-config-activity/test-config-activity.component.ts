import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-test-config-activity',
  templateUrl: './test-config-activity.component.html',
  styleUrls: ['./test-config-activity.component.css']
})
export class TestConfigActivityComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

  save() {
    const x:any = document.getElementsByTagName("iframe");
    console.log(x);
  }
}
