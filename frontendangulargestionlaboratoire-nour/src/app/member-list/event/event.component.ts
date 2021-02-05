import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MemberEventFormComponent } from '../member-event-form/member-event-form.component';
import { Subject } from 'rxjs';
import { takeUntil } from 'rxjs/operators';
import { EventService } from '../../../services/event.service';
import { Event } from '../../../models/event.model';
import { User } from '../../../models/user.model';
import { LoginService } from '../../../services/login.service';
import { MemberEventService } from '../../../services/MemberEvent.service';
import { ToastrService } from 'ngx-toastr';
import { MemberService } from 'services/member.service';
import { Member } from 'models/member.model';

@Component({
  selector: 'app-event',
  templateUrl: './event.component.html',
  styleUrls: ['./event.component.css']
})
export class EventComponent implements OnInit {
  protected _onDestroy = new Subject<void>();
  events: Event[];
  member: Member;
  displayedColumns = ['Member', 'Event Name'];
  username: string = "";
  currentEventId: number;
  currentMemberId: number;

  constructor(private memberService:MemberService, private memberEventService: MemberEventService, private eventService: EventService, private loginService: LoginService,private toastr: ToastrService,) { }

  ngOnInit(): void {
    this.isAuthenticated()

    this.fetchEvents()
    this.fetchMemberByName();

  }
  onAdd(id: number) {
  
    this.currentEventId = id;
    this.memberEventService.affectMemberToEvent(this.currentMemberId, this.currentEventId);
    this.toastr.success("subscription to event added successfully");

   

  }
  fetchEvents() {
    this.eventService.getAllEvents().then(data => {
      this.events = data
    })
  }
  fetchMemberByName() {
    this.memberService.getMemberByName(this.username).then(data => {
      console.log("data" + data.username);
      this.member = data;
this.currentMemberId=data.id;

      ;
    })
  }
  isAuthenticated() {
    this.username = this.loginService.getUserName();
  }

}
