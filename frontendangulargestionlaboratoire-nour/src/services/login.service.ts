import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JwtHelperService } from '@auth0/angular-jwt';
import { Router } from '@angular/router';


const AUTH_API = 'http://localhost:8762/auth-service';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};
@Injectable({
  providedIn: 'root'
})
export class LoginService {
 path = 'http://localhost:8762/auth-service/register/users';

  public jwt: string;
  public userName: string;
  public role: Array<string>;
  constructor(private httpClient: HttpClient, private router: Router) { }
  login(credentials) {
    return this.httpClient.post(AUTH_API + "/auth", credentials, { observe: 'response' });

  }
  initParams() {
    this.jwt = undefined;
    this.userName = undefined;
    this.role = undefined;
  }

  saveJwt(token: string) {
    localStorage.setItem("token", token);
    console.log("xxx" + token)
    this.jwt = token;
    this.parseJWT();
  }

  parseJWT() {
    let jwtHelper = new JwtHelperService();
    let jwtObject = jwtHelper.decodeToken(this.jwt);
    this.userName = jwtObject.sub;
    this.role = jwtObject.roles;
    console.log("roles" + this.role);
    console.log("admin=" + this.isAdmin());
    console.log("user=" + this.isUSER());
  }


  isAdmin() {
    return this.role.indexOf('admin') >= 0;
  }

  isUSER() {
    return this.role.indexOf('user') >= 0;
  }

  isAuthenticated() {
    return this.role && (this.isAdmin() || this.isUSER());
  }

  loadJWT() {
    this.jwt = localStorage.getItem('token');
    this.parseJWT();
  }

  getUserName() {
    return this.userName;
  }
  logout() {
    localStorage.removeItem('token');
    this.initParams();
    this.router.navigate(['/login']);
  }
  register(user): Observable<any> {
    return this.httpClient.post(AUTH_API + '/register', {
      username: user.username,
      email: user.email,
      password: user.password
    }, httpOptions);
  }


  
}