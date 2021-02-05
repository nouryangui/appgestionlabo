import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';
import { environment } from '../environments/environment';
import { Event } from '../models/event.model';
import { LoginService } from './login.service';
@Injectable({
    providedIn: 'root'
})
export class EventService {
    private path = `${environment.gatewayEndpoint}/event-service/api/events`;
  constructor(
    private httpClient: HttpClient,private loginService:LoginService
  ) {
  }

    getAllEvents(): Promise<Event[]> {
        return this.httpClient.get<Event[]>(`${this.path}`).toPromise();
    }

    saveEvent(event: any): Promise<Event> {
        if (!!event.id) {
            return this.updateEvent(event.id, event);
        } else {
            return this.createEvent(event);
        }
    }
    createEvent(event: any): Promise<Event> {
        let headers=new HttpHeaders({'Authorization':'bearer '+this.loginService.jwt})

        return this.httpClient.post<Event>(`${this.path}`,event,{ headers:headers }).toPromise();
    }

    updateEvent(id: string,event: any): Promise<Event> {
        let headers=new HttpHeaders({'Authorization':'bearer '+this.loginService.jwt})

        return this.httpClient.put<Event>(`${this.path}`,event,{ headers:headers }).toPromise();
    }
    removeEventById(id: string): Promise<Event> {
        let headers=new HttpHeaders({'Authorization':'bearer '+this.loginService.jwt})

        return this.httpClient.delete<Event>(`${this.path}/${id}`,{ headers:headers }).toPromise();

    }
    getEventById(id: number): Promise<Event> {
        let headers=new HttpHeaders({'Authorization':'bearer '+this.loginService.jwt})

        return this.httpClient.get<Event>(`${this.path}/${id}`,{ headers:headers }).toPromise();
    }
    getEventsPaginator(itemPage: number): Promise<Event[]> {
        const options = new HttpParams()
          .set('page', String(itemPage))
        return this.httpClient.get<Event[]>(`${this.path}/all`, { params: options}).toPromise();
      }
      getEventCount(): Promise<Number> {
        return this.httpClient.get<Number>(`${this.path}/count`).toPromise();
      }
}
