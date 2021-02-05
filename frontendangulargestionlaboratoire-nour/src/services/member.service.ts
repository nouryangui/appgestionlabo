import { Injectable } from '@angular/core';
import { Member } from '../models/member.model';
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';
import { environment } from '../environments/environment';
import { Publication } from '../models/publication.model';
import { MemberPublication } from '../models/memberpublication.model';
import { MemberTool } from '../models/membertool.model';
import { Event } from '../models/event.model';
import { Tool } from '../models/tool.model';
import { LoginService } from './login.service';
@Injectable({
  providedIn: 'root'
})
export class MemberService {
  private path = `${environment.gatewayEndpoint}/member-service/api/members`;


  constructor(
    private httpClient: HttpClient,private loginService:LoginService
  ) {
  }

  getAllMembers(): Promise<Member[]> {
    return this.httpClient.get<Member[]>(`${this.path}`).toPromise();
  }
  getMembersPaginator(itemPage: number): Promise<Member[]> {
    const options = new HttpParams()
      .set('page', String(itemPage))
    return this.httpClient.get<Member[]>(`${this.path}/all`, { params: options}).toPromise();
  }

  getMemberById(id: number): Promise<Member> {
    return this.httpClient.get<Member>(`${this.path}/${id}`).toPromise();
  }
  saveStudent(member: any): Promise<Member> {
    if (!!member.id) {
      return this.updateStudent(member.id, member);
    } else {
      return this.createStudent(member);
    }
  }

  createStudent(member: any): Promise<Member> {
    let headers=new HttpHeaders({'Authorization':'bearer '+this.loginService.jwt})

    return this.httpClient.post<Member>(`${this.path}/students`, member,{ headers:headers }).toPromise();
  }

  updateStudent(id: string, member: any): Promise<Member> {
    let headers=new HttpHeaders({'Authorization':'bearer '+this.loginService.jwt})

    return this.httpClient.put<Member>(`${this.path}/students`, member,{ headers:headers }).toPromise();
  }
  saveTeacher(member: any): Promise<Member> {
    if (!!member.id) {
      return this.updateTeacher(member.id, member);
    } else {
      return this.createTeacher(member);
    }
  }

  createTeacher(member: any): Promise<Member> {
    let headers=new HttpHeaders({'Authorization':'bearer '+this.loginService.jwt})

    return this.httpClient.post<Member>(`${this.path}/teachers`, member,{ headers:headers }).toPromise();
  }

  updateTeacher(id: string, member: any): Promise<Member> {
    let headers=new HttpHeaders({'Authorization':'bearer '+this.loginService.jwt})

    return this.httpClient.put<Member>(`${this.path}/teachers`, member,{ headers:headers }).toPromise();
  }


  removeMemberById(id: string): Promise<Member> {
    let headers=new HttpHeaders({'Authorization':'bearer '+this.loginService.jwt})

    return this.httpClient.delete<Member>(`${this.path}/${id}`,{ headers:headers }).toPromise();

  }
  getMemberCount(): Promise<Number> {
    return this.httpClient.get<Number>(`${this.path}/count`).toPromise();
  }

  findAllTeacher(): Promise<Member[]> {
    let headers=new HttpHeaders({'Authorization':'bearer '+this.loginService.jwt})

    return this.httpClient.get<Member[]>(`${this.path}/teachers`,{ headers:headers }).toPromise();
  }


  affectMemberToPublication(idMember:number,idPublication:number):Promise<MemberPublication>
  {    let headers=new HttpHeaders({'Authorization':'bearer '+this.loginService.jwt})

    return this.httpClient.post<MemberPublication>(`${this.path}/publications/${idMember}/${idPublication}`,[idMember,idPublication],{ headers:headers }).toPromise();
  }
  getPublicationMember(idMember:number): Promise<Publication[]> {
    return this.httpClient.get<Publication[]>(`${this.path}/publications/${idMember}`).toPromise();
  }
  getEventMember(idMember:number): Promise<Event[]> {
    return this.httpClient.get<Event[]>(`${this.path}/events/${idMember}`).toPromise();
  }
  getToolMember(idMember:number): Promise<Tool[]> {
    return this.httpClient.get<Tool[]>(`${this.path}/tools/${idMember}`).toPromise();
  }
  
  getMemberByName(name:string):Promise<Member>
  {
    return this.httpClient.get<Member>(`${this.path}/username/${name}`).toPromise();

  }
  affectMemberToTool(idMember:number,idTool:number):Promise<MemberTool>
  {    let headers=new HttpHeaders({'Authorization':'bearer '+this.loginService.jwt})

    return this.httpClient.post<MemberTool>(`${this.path}/tools/${idMember}/${idTool}`,[idMember,idTool],{ headers:headers }).toPromise();
  }
}
