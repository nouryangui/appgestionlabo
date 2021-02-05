import { MemberEvent } from "../models/memberevent.model";
import { environment } from "../environments/environment";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { MemberService } from '../services/member.service';
import { Member } from "../models/member.model";
import { Event } from "../models/event.model";
import { LoginService } from "./login.service";

@Injectable({
    providedIn: 'root'
})
export class MemberEventService {
    private pathMember = `${environment.gatewayEndpoint}/member-service/api/members`;
    private pathEvent = `${environment.gatewayEndpoint}/event-service/api/events`;

    constructor(private httpClient: HttpClient,private loginService:LoginService) {
    }
    affectMemberToEvent(idMember: number, idEvent: number): Promise<MemberEvent> {
        let headers=new HttpHeaders({'Authorization':'bearer '+this.loginService.jwt})

        return this.httpClient.post<MemberEvent>(`${this.pathMember}/events/${idMember}/${idEvent}`, [idMember, idEvent],{ headers:headers }).toPromise();
    }
    getAllMembers(): Promise<Member[]> {

        return this.httpClient.get<Member[]>(`${this.pathMember}`).toPromise();
    }
    getAllEvents(): Promise<Event[]> {

        return this.httpClient.get<Event[]>(`${this.pathEvent}`).toPromise();
    }
}