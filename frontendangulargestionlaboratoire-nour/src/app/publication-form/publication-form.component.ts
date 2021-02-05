import { Component, OnInit, Inject } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { Publication } from '../../models/publication.model';
import { PublicationService } from '../../services/publication.service';
import { MemberService } from 'services/member.service';
import { LoginService } from 'services/login.service';

@Component({
  selector: 'app-publication-form',
  templateUrl: './publication-form.component.html',
  styleUrls: ['./publication-form.component.css']
})
export class PublicationFormComponent implements OnInit {

  currentItemId: number;
  currentMemberId: number;
  item: Publication;
  form: FormGroup;
  key: string;
  username:string;
  successMessage: string;
  isUpdate=false;
  constructor(private router: Router, private toastr: ToastrService,private loginService:LoginService,
    private publicationService: PublicationService, private memberService: MemberService,
    private dialogRef: MatDialogRef<PublicationFormComponent>, @Inject(MAT_DIALOG_DATA) public data: any,) { }

  ngOnInit(): void {
    this.fetchMemberByName();
    console.log(this.currentMemberId);
    if (!!this.data) {
      this.currentItemId = this.data.publicationId;

    }
    if (!!this.currentItemId) {
      this.key = "Update Publication",
        this.successMessage = "Publication updated successfully"
      this.publicationService.getPublicationById(this.currentItemId).then(item => {
        this.item = item;
        this.initForm(item);
        this.isUpdate=true;
      });
    } else {
      this.isUpdate=false;

      this.key = "Add Publication";
      this.successMessage = "Publication added successfully"

      this.initForm(null);
    }
  }
  initForm(publication: Publication): void {
    this.form = new FormGroup({
      titre: new FormControl(!!publication ? publication.titre : null, [Validators.required]),
      type: new FormControl(!!publication ? publication.type : null, [Validators.required]),
      lien: new FormControl(!!publication ? publication.lien : null, []),
      date: new FormControl(!!publication ? publication.date : null, []),
      Sourcepdf: new FormControl(!!publication ? publication.Sourcepdf : null, []),

    });
  }

  onSubmit(): void {
    const objectToSubmit = { ...this.item, ...this.form.value };
    console.log(objectToSubmit);
    this.publicationService.savePublication(objectToSubmit).then((data) => {
      if(this.isUpdate===false)
      {
        this.affectMemberToPublication(this.currentMemberId,data.id);
        console.log("*****")

      }
      console.log(data.id);
      this.initForm(null);
      this.dialogRef.close();
      this.toastr.success(this.successMessage);
      this.router.navigate(['./dashboard/publications'])
    }
    );


  }
  fetchMemberByName() {
    this.isAuthenticated();
    this.memberService.getMemberByName(this.username).then(data => {
      console.log("data" + data.username);
      //this.member = data;
      this.currentMemberId = data.id;

      ;
    })
  }
  isAuthenticated() {
    this.username = this.loginService.getUserName();
  }
  affectMemberToPublication(idmember:number,idPub:number)
  {
    this.memberService.affectMemberToPublication(idmember, idPub);
  }
}
