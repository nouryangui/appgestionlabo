import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';
import { environment } from '../environments/environment';
import { Tool } from '../models/tool.model';
import { LoginService } from './login.service';
@Injectable({
    providedIn: 'root'
})
export class ToolService {
    private path = `${environment.gatewayEndpoint}/tool-service/api/tools`;


    constructor(private httpClient: HttpClient, private loginService: LoginService

    ) {
    }
    getAllTools(): Promise<Tool[]> {
        return this.httpClient.get<Tool[]>(`${this.path}`).toPromise();
    }

    saveTool(tool: any): Promise<Tool> {
        if (!!tool.id) {
            return this.updateTool(tool.id, tool);
        } else {
            return this.createTool(tool);
        }
    }
    createTool(tool: any): Promise<Tool> {
        let headers = new HttpHeaders({ 'Authorization':'bearer '+this.loginService.jwt })
        return this.httpClient.post<Tool>(`${this.path}`, tool, { headers: headers }).toPromise();
    }

    updateTool(id: string, tool: any): Promise<Tool> {
        let headers = new HttpHeaders({ 'Authorization':'bearer '+this.loginService.jwt })
        return this.httpClient.put<Tool>(`${this.path}`, tool,{ headers: headers }).toPromise();
    }
    removeToolById(id: string): Promise<Tool> {
        let headers = new HttpHeaders({ 'Authorization':'bearer '+this.loginService.jwt})
        return this.httpClient.delete<Tool>(`${this.path}/${id}`,{ headers: headers }).toPromise();

    }
    getToolById(id: number): Promise<Tool> {
        return this.httpClient.get<Tool>(`${this.path}/${id}`).toPromise();
    }
    getToolPaginator(itemPage: number): Promise<Event[]> {
        const options = new HttpParams()
            .set('page', String(itemPage))
        return this.httpClient.get<Event[]>(`${this.path}/all`, { params: options }).toPromise();
    }
    getToolCount(): Promise<Number> {
        return this.httpClient.get<Number>(`${this.path}/count`).toPromise();
    }
}
