import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { Member } from 'models/member.model';
import { LoginService } from 'services/login.service';
import { MemberService } from 'services/member.service';
import { MemberupdateformComponent } from '../../memberupdateform/memberupdateform.component';

@Component({
  selector: 'app-profile',
  templateUrl: './profil.component.html',
  styleUrls: ['./profil.component.css']
})
export class ProfilComponent implements OnInit {
  form: FormGroup;
  currentItemId: number;
  passwordhidden=true;
  successMessage: string;
  item: Member;
  teacherplaceholder: string = "teacher"
  username: string = "";
  member:Member;


  constructor( private memberService: MemberService,private loginService:LoginService,private matDialog: MatDialog) { }

  ngOnInit() {
    this.fetchMemberByName()
    
  }

  isAuthenticated() {
    this.username = this.loginService.getUserName();
  }
  fetchMemberByName() {
    this.isAuthenticated();
    console.log(this.username);
    this.memberService.getMemberByName(this.username).then(data => {
      console.log("dataaaaa" + data.username);
      this.member = data;
      console.log(this.member)
    })
  }
  open(id:number)
  {
    const dialogRef = this.matDialog.open(MemberupdateformComponent, {
      disableClose: false,
      data: { memberId: id }
    });
    

  }
}
