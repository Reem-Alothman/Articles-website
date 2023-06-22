import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Article } from '../modles/Article'
import { ArticlePost } from '../modles/ArticlePost';


@Injectable({
  providedIn: 'root'
})

export class ArticleService {

  private baseUrl = "http://localhost:8080/article";
  headers = { 
    headers: new HttpHeaders({ 'Content-Type': 'application/json', 
                              'Authorization': `${localStorage.getItem('Authorization')}` }) 
  };
  constructor(private http: HttpClient) { }

  getArticles(): Observable<Article[]>{
    return this.http.get<Article[]>(this.baseUrl);
  }

  postArticle(record: ArticlePost): Observable<any>{
    return this.http.post<any>(this.baseUrl, record, this.headers);
  }

  addLike(id: number): Observable<any>{
    return this.http.put<any>(`${this.baseUrl}/${id}/like`, '', this.headers);
  }

  addDislike(id: number): Observable<any>{
    return this.http.put<any>(`${this.baseUrl}/${id}/dislike`, '', this.headers);
  }

  disableArticle(id: number): Observable<any>{
    return this.http.put<any>(`${this.baseUrl}/${id}/disable`, '', this.headers);
  }

  addComment(id: number, comment: String): Observable<any>{
    return this.http.post<any>(`${this.baseUrl}/${id}/comment`, comment, this.headers);
  }

  deleteArticle(id: number): Observable<any>{
    return this.http.delete<any>(`${this.baseUrl}/${id}`, this.headers);
  }
  
}
