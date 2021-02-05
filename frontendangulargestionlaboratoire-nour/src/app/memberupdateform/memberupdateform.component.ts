import { Component, Inject, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { Member } from 'models/member.model';
import { ToastrService } from 'ngx-toastr';
import { MemberService } from 'services/member.service';

@Component({
  selector: 'app-memberupdateform',
  templateUrl: './memberupdateform.component.html',
  styleUrls: ['./memberupdateform.component.css']
})
export class MemberupdateformComponent implements OnInit {
  currentItemId: number;
member:Member;
form: FormGroup;
teacherplaceholder: string = "teacher"


  constructor(private router: Router, private toastr: ToastrService,private memberService: MemberService,private dialogRef: MatDialogRef<MemberupdateformComponent>, @Inject(MAT_DIALOG_DATA) public data: any,) { }

  ngOnInit(): void {
    if (!!this.data) {
      this.currentItemId = this.data.memberId;
console.log(this.currentItemId)
    }
    if (!!this.currentItemId) {
      this.memberService.getMemberById(this.currentItemId).then(item => {
        this.member = item;
        if (!!this.member.teacher) {
          this.teacherplaceholder = this.member.teacher.firstName + " " + this.member.teacher.lastName;

        }
        console.log("member"+this.member)
        this.initForm(this.member);

      });
    }
  }
  initForm(member: Member): void {
    this.form = new FormGroup({
      cin: new FormControl(!!member ? member.cin : null, [Validators.required]),
      firstName: new FormControl(!!member ? member.firstName : null, []),
      lastName: new FormControl(!!member ? member.lastName : null, []),
      email: new FormControl(!!member ? member.email : null, []),
      cv: new FormControl(!!member ? member.cv : null, []),
      birthDate: new FormControl(!!member ? member.birthDate : null, []),
      type: new FormControl(!!member ? member.type : null, []),
      diplome: new FormControl(!!member ? member.diplome : null, [Validators.required]),
      dateInscription: new FormControl(!!member ? member.dateInscription : null, []),
      grade: new FormControl(!!member ? member.grade : null, []),
      etablissement: new FormControl(!!member ? member.etablissement : null, []),
      teacher: new FormControl(!!member ? member.teacher : null, []),
      image: new FormControl(null),
      username: new FormControl(!!member ? member.username : null, []),
      password: new FormControl(!!member ? member.password : null, []),




    });
  }
  onSubmit()

  
  
  {     const objectToSubmit = { ...this.member, ...this.form.value };

    
    if (this.form.controls.type.value == "teacher") {
    this.memberService.saveTeacher(objectToSubmit).then(() => {
      this.initForm(null);
      this.dialogRef.close();

      this.router.navigate(['./dashboard/profil'])
    }
    );
  }
  else if (this.form.controls.type.value == "student") {
    console.log(objectToSubmit);
    this.memberService.saveStudent(objectToSubmit).then(() => {
      this.initForm(null);
      this.dialogRef.close();
      this.router.navigate(['./dashboard/profil'])

    }
    );

  }
}
}
