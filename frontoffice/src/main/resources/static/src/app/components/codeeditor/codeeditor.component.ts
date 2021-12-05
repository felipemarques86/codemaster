import {Component, Input, OnInit, QueryList, ViewChild, ViewChildren} from '@angular/core';
import {Code, Comment} from "../../models/evaluation-activity";
import {CodemirrorComponent} from "@ctrl/ngx-codemirror";
import {Deliverable} from "../../models/activity-instance";
import {LineHandle, Position} from "codemirror";

@Component({
  selector: 'app-codeeditor',
  templateUrl: './codeeditor.component.html',
  styleUrls: ['./codeeditor.component.css']
})
export class CodeeditorComponent implements OnInit {

  @Input()
  commentList: Comment[]  = [];
  @Input()
  code!: Code;

  @ViewChild('codemirrorComponent') codemirrorComponent!: CodemirrorComponent;
  @ViewChildren('codemirrorComponentOthers') codemirrorComponentOthers!: QueryList<CodemirrorComponent>;

  constructor() { }

  ngOnInit(): void {
    setTimeout(() => this.buildComments()  , 100);
  }

  highlightLine(id: number) {
    let comment =  document.getElementById("comment_" + id);
    if(comment && comment.parentElement) {
      setTimeout(() => {
        if(comment && comment.parentElement)
          comment.parentElement.style.textDecoration = "underline wavy white";
      }, 50);
    } else {
      console.error('Comment_' + id + " not found");
    }
  }

  removeHighlightLine(id: number) {
    let comment =  document.getElementById("comment_" + id);
    if(comment && comment.parentElement) {
      setTimeout( () => {
        if(comment && comment.parentElement)
          comment.parentElement.style.textDecoration = "none";
      }, 200);
    }
  }

  codeChanged() {

  }

  buildComments(): void {
    let n = 0;
    this.codemirrorComponent.codeMirror?.eachLine((line: LineHandle) => {
      if (line.text.indexOf('/**<comment>') >= 0) {
        let p1: Position = {ch: line.text.indexOf('/**<comment>'), line: n};
        let p2: Position = {ch: line.text.indexOf('**/', p1.ch) + 3, line: n};

        const elm = document.createElement("span");

        let opts: any = {readOnly: true, className: 'cea-comment', atomic: true, replacedWith: elm};
        let commentId = +line.text.substring(p1.ch, p2.ch).replace("/**<comment>", "").replace("</comment>**/", "");
        let comment = this.commentList.find(c => c.id == commentId);
        if (comment) {
          elm.id = "comment_" + comment.id;
          elm.className = "btn btn-info cea-comment";
          elm.innerHTML = "<span class='badge badge-light'>" + comment.id + "</span> Comentário de " + comment.author.name + "";
        }
        this.codemirrorComponent.codeMirror?.markText(p1, p2, opts);
      }
      n++;
    });
  }
}
