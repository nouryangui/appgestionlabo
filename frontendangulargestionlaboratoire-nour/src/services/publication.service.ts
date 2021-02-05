import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';
import { environment } from '../environments/environment';
import { Publication } from '../models/publication.model';
import { LoginService } from './login.service';

@Injectable({
  providedIn: 'root'
})
export class PublicationService {
  private path = `${environment.gatewayEndpoint}/publication-service/api/publications`;

  constructor(private httpClient: HttpClient, private loginService: LoginService

  ) { }
  getAllPublication(): Promise<Publication[]> {

    return this.httpClient.get<Publication[]>(`${this.path}`).toPromise();
  }
  getPublicationsPaginator(itemPage: number): Promise<Publication[]> {
    const options = new HttpParams()
      .set('page', String(itemPage))
    return this.httpClient.get<Publication[]>(`${this.path}/all`, { params: options }).toPromise();
  }
  getPublicationById(id: number): Promise<Publication> {
    return this.httpClient.get<Publication>(`${this.path}/${id}`).toPromise();
  }
  savePublication(publication: any): Promise<Publication> {
    if (!!publication.id) {
      return this.updatePublication(publication.id, publication);
    } else {
      return this.createPublication(publication);
    }
  }
  createPublication(publication: any): Promise<Publication> {
    let headers = new HttpHeaders({ 'Authorization':'bearer '+this.loginService.jwt })
    return this.httpClient.post<Publication>(`${this.path}`, publication, { headers: headers }).toPromise();
  }

  updatePublication(id: string, publication: any): Promise<Publication> {
    let headers = new HttpHeaders({ 'Authorization':'bearer '+this.loginService.jwt })
    return this.httpClient.put<Publication>(`${this.path}`, publication, { headers: headers }).toPromise();
  }
  removePublicationById(id: string): Promise<Publication> {
    let headers = new HttpHeaders({ 'Authorization':'bearer '+this.loginService.jwt })
    return this.httpClient.delete<Publication>(`${this.path}/${id}`, { headers: headers }).toPromise();

  }
  getAllPublications(): Promise<Publication[]> {
    return this.httpClient.get<Publication[]>(`${this.path}`).toPromise();
  }
  getPublicationCount(): Promise<Number> {
    return this.httpClient.get<Number>(`${this.path}/count`).toPromise();
  }
}
