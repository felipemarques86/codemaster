import {Component, Input, OnInit, Output, QueryList, ViewChild, ViewChildren} from '@angular/core';
import {Code, Comment, EndUser} from "../../models/evaluation-activity";
import {CodemirrorComponent} from "@ctrl/ngx-codemirror";
import {LineHandle, Position} from "codemirror";
import {CodeService} from "../../services/code.service";
import {ActivityService} from "../../services/activity.service";

@Component({
  selector: 'app-codeeditor',
  templateUrl: './codeeditor.component.html',
  styleUrls: ['./codeeditor.component.css']
})
export class CodeeditorComponent implements OnInit {

  @Input()
  commentList: Comment[]  = [];
  @Input()
  currentUser!: EndUser;
  @Input()
  code!: Code;
  @Input()
  @Output()
  output!: string;
  @Input()
  readOnly: boolean = false;

  @ViewChild('codemirrorComponent') codemirrorComponent!: CodemirrorComponent;
  @ViewChildren('codemirrorComponentOthers') codemirrorComponentOthers!: QueryList<CodemirrorComponent>;


  constructor(private codeService: CodeService, private activityService: ActivityService) { }

  ngOnInit(): void {
    setTimeout(() => this.buildComments()  , 100);
    if(this.readOnly === true) {
      setInterval(() => this.refreshCode(), 30000);
    }
  }

  highlightLine(id?: number) {
    let comment =  document.getElementById("comment_" + id);
    if(comment && comment.parentElement && comment.parentElement.parentElement && comment.parentElement.parentElement.parentElement && comment.parentElement.parentElement.parentElement.parentElement) {
      setTimeout(() => {
        if(comment && comment.parentElement && comment.parentElement.parentElement && comment.parentElement.parentElement.parentElement && comment.parentElement.parentElement.parentElement.parentElement) {
          comment.parentElement.parentElement.parentElement.parentElement.style.textDecoration = "underline wavy white";
        }
      }, 50);
    }
  }

  removeHighlightLine(id?: number) {
    let comment =  document.getElementById("comment_" + id);
    if(comment && comment.parentElement && comment.parentElement.parentElement && comment.parentElement.parentElement.parentElement && comment.parentElement.parentElement.parentElement.parentElement) {
      setTimeout( () => {
        if(comment && comment.parentElement && comment.parentElement.parentElement && comment.parentElement.parentElement.parentElement && comment.parentElement.parentElement.parentElement.parentElement)
          comment.parentElement.parentElement.parentElement.parentElement.style.textDecoration = "none";
      }, 200);
    }
  }

  codeChanged() {
    setTimeout( () => {
      console.log('save code', this.code.code, this.code.id);
      this.codeService.saveCode(this.code).subscribe(c => this.code = c);
    }, 500);
  }

  refreshCode() {
    this.codeService.getCode(this.code.id).subscribe( code => {
      this.code.code = code.code;
      this.buildComments();
    });
  }

  buildComments(): void {
    console.log(this.commentList);
    let n = 0;
    this.codemirrorComponent.codeMirror?.eachLine((line: LineHandle) => {

      console.log(line.text);

      if (line.text.indexOf('/**<comment>') >= 0) {
        let p1: Position = {ch: line.text.indexOf('/**<comment>'), line: n};
        let p2: Position = {ch: line.text.indexOf('**/', p1.ch) + 3, line: n};

        const elm = document.createElement("span");

        let opts: any = {readOnly: true, className: 'cea-comment', atomic: true, replacedWith: elm};
        let commentId = +line.text.substring(p1.ch, p2.ch).replace("/**<comment>", "").replace("</comment>**/", "");
        console.log('commentId', commentId);
        const comment = this.commentList.find(c => c.id == commentId);
        if (comment) {
          elm.id = "comment_" + comment.id;
          elm.className = "btn btn-info cea-comment";
          elm.innerHTML = "<span class='badge badge-light'>" + comment.id + "</span> Comentário de " + comment.author.name + "";
          elm.addEventListener('click', () =>{
            alert('Comentário: ' + comment.content);
          });
        }
        console.log(comment);
        this.codemirrorComponent.codeMirror?.markText(p1, p2, opts);
      }
      n++;
    });
  }

  reply(comment: Comment) {
      const commentText = prompt('Comentário: ');
      if(commentText) {
        this.activityService.replyComment(comment, commentText, this.currentUser).subscribe( c => {
            comment.replies.push(c);
        });
      }
  }
}
