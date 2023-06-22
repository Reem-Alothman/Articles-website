import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Login } from '../modles/Login';
import { Register } from '../modles/Register';


@Injectable({
  providedIn: 'root'
})


export class UserService {

  private baseUrl = "http://localhost:8080/";
  options = { Headers: { 'Content-Type': 'application/json'},
              responseType: 'text' as 'json'
            };

  constructor(private http: HttpClient) { }

  login(creds: Login): Observable<any> {
    return this.http.post<any>(this.baseUrl+'login', creds, {observe: 'response', responseType: 'text' as 'json'});
  }

  register(creds: Register): Observable<any> {
    return this.http.post<any>(this.baseUrl+'user', creds);
  }
}