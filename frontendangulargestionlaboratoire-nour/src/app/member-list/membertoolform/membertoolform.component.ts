import { Component, Inject, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { Member } from 'models/member.model';
import { MemberService } from 'services/member.service';

@Component({
  selector: 'app-membertoolform',
  templateUrl: './membertoolform.component.html',
  styleUrls: ['./membertoolform.component.css']
})
export class MembertoolformComponent implements OnInit {
  members:Member[];
  selectedValue:string;
  form:FormGroup;
  member:Member;
  currentIdTool:number;
  constructor(private memberService:MemberService,private dialogRef: MatDialogRef<MembertoolformComponent>,    
    private router: Router,@Inject(MAT_DIALOG_DATA) public data: any, 
    ) { }

  

  ngOnInit(): void {
    this.currentIdTool=this.data.toolId
    this.fetchMembers();
    this.initForm(null);
  }
  onSubmit(): void {
    const objectToSubmit = this.form.value ;
    console.log("ccc")
    console.log(objectToSubmit);
    this.memberService.affectMemberToTool( this.form.value.member,this.currentIdTool);
    this.initForm(null);
    this.dialogRef.close();
   

  }
  fetchMembers(): void {
    this.memberService.getAllMembers().then(data => this.members = data);
  }
 
  initForm(member: Member): void {
    this.form = new FormGroup({
      member: new FormControl( null, []),
      
    });
  }

}
