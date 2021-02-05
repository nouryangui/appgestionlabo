import { Injectable } from '@angular/core';
import { MemberPublication } from '../models/memberpublication.model';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { environment } from '../environments/environment';
import { LoginService } from './login.service';
import { Member } from '../models/member.model';
import { Publication } from '../models/publication.model';

@Injectable({
  providedIn: 'root'
})
export class MemberPublicationService {

  private pathMember = `${environment.gatewayEndpoint}/member-service/api/members`;
  private pathEvent = `${environment.gatewayEndpoint}/publication-service/api/publications`;

  constructor(private httpClient: HttpClient,private loginService:LoginService) {
  }
  affectMemberToEvent(idMember: number, idEvent: number): Promise<MemberPublication> {
      let headers=new HttpHeaders({'Authorization':'bearer '+this.loginService.jwt})

      return this.httpClient.post<MemberPublication>(`${this.pathMember}/events/${idMember}/${idEvent}`, [idMember, idEvent],{ headers:headers }).toPromise();
  }
  getAllMembers(): Promise<Member[]> {

      return this.httpClient.get<Member[]>(`${this.pathMember}`).toPromise();
  }
  getAllPublication(): Promise<Publication[]> {

      return this.httpClient.get<Publication[]>(`${this.pathEvent}`).toPromise();
  }
}
