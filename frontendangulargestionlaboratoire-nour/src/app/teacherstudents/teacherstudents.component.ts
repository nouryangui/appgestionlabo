import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MemberDetailComponent } from 'app/member-detail/member-detail.component';
import { Member } from 'models/member.model';
import { MemberService } from 'services/member.service';

@Component({
  selector: 'app-teacherstudents',
  templateUrl: './teacherstudents.component.html',
  styleUrls: ['./teacherstudents.component.css']
})
export class TeacherstudentsComponent implements OnInit {
  currentItemId: number;
  member: Member;
  isTeacher=false;

  constructor(private memberService: MemberService, private dialogRef: MatDialogRef<TeacherstudentsComponent>, @Inject(MAT_DIALOG_DATA) public data: any,) { }

  ngOnInit(): void {
    if (!!this.data) {
      this.currentItemId = this.data.memberId;

    }
    if (!!this.currentItemId) {
      this.memberService.getMemberById(this.currentItemId).then(item => {
        if(item.type==="teacher")
        {
          this.isTeacher=true;
        }
        this.member = item;
      });
    }
  }

}
