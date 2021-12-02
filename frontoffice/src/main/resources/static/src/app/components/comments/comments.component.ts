import { Component, OnInit } from '@angular/core';
import {Deliverable} from "../../models/activity-instance";

@Component({
  selector: 'app-comments',
  templateUrl: './comments.component.html',
  styleUrls: ['./comments.component.css']
})
export class CommentsComponent implements OnInit {

  public deliverable!: Deliverable;

  constructor() { }

  ngOnInit(): void {
  }

  highlightLine(id: number) {

  }

  removeHighlightLine(id: number) {

  }
}
