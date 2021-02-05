import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { FormGroup, FormControl } from '@angular/forms';
import { Tool } from '../../models/tool.model';
import { ToolService } from '../../services/tool.service';
import { ToastrService } from 'ngx-toastr';
import { MemberService } from 'services/member.service';
import { LoginService } from 'services/login.service';

@Component({
  selector: 'app-tool-form',
  templateUrl: './tool-form.component.html',
  styleUrls: ['./tool-form.component.css']
})
export class ToolFormComponent implements OnInit {
  currentItemId: number;
  item: Tool;
  form: FormGroup;
  key: string;
  successMessage:string;
  isUpdate=false;
  currentMemberId: number;
  username:string;


  constructor(private router: Router,private toastr: ToastrService,private loginService:LoginService,
    private toolService: ToolService,
    @Inject(MAT_DIALOG_DATA) public data: any, private dialogRef: MatDialogRef<ToolFormComponent>,private memberService: MemberService) { }

  ngOnInit(): void {
    this.fetchMemberByName();
    if (!!this.data) {
      this.currentItemId = this.data.toolId;

    }
    if (!!this.currentItemId) {
      this.isUpdate=true;

      this.key = "Update  tool"
      this.toolService.getToolById(this.currentItemId).then(item => {
        this.item = item;
        this.initForm(item);
        this.successMessage="Tool updated successfully"
      });
    } else {
      this.isUpdate=false;

      this.key = "Add tool";
      this.successMessage="Tool added successfully"

      this.initForm(null);
    }
  }
  initForm(tool: Tool): void {
    this.form = new FormGroup({
      source: new FormControl(!!tool ? tool.source : null, []),
     date: new FormControl(!!tool ? tool.date : null, []),
     
    
    });
  }

  onSubmit(): void {
    const objectToSubmit = { ...this.item, ...this.form.value };
    console.log(objectToSubmit);
    this.toolService.saveTool(objectToSubmit).then((data) => {
      if(this.isUpdate===false)
      {
        this.affectMemberToTool(this.currentMemberId,data.id);
        console.log("*****")

      }
      this.initForm(null);
      this.dialogRef.close();
      this.toastr.success(this.successMessage);

      this.router.navigate(['./dashboard/tools'])
    }
    );
  }
  affectMemberToTool(idmember:number,idTool:number)
  {
    console.log("idmember"+idmember);
    console.log("idTool"+idTool);
    this.memberService.affectMemberToTool(idmember, idTool);
  }
  isAuthenticated() {
    this.username = this.loginService.getUserName();
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
}
