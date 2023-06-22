import { Component, OnInit, Input, Output, EventEmitter} from '@angular/core';
import { Observable } from 'rxjs';
import { Article } from '../modles/Article';
import { User } from '../modles/User';
import { ArticleService } from '../services/article.service';
import { formatDate } from '@angular/common';
import { ButtonModule } from 'primeng/button';
import { ArticleComponent } from '../articles/article.component';

@Component({
  selector: 'app-article-item',
  templateUrl: './article-item.component.html',
  styleUrls: ['./article-item.component.css']
})
export class ArticleItemComponent implements OnInit{

  @Input() article: Article;
  comment: String;
  // @Output() disableArticle: EventEmitter<Article> = new EventEmitter;

  constructor(private articleService: ArticleService, private articleComponent: ArticleComponent){
    this.article = {
      artID: NaN,
      title: "",
      body: "",
      createdAt: "",
      likes: 0,
      dislikes: 0,
      disable: false,
      comments: [],
      username: ''
    }
    this.comment ='';
  }

  ngOnInit(): void {
      
  }

  like(article: Article): void {
    this.articleService.addLike(article.artID).subscribe(res => {this.articleComponent.ngOnInit();});
  }

  dislike(article: Article): void{
    this.articleService.addDislike(article.artID).subscribe(res => {this.articleComponent.ngOnInit();});
  }

  disable(article: Article): void{
    this.articleService.disableArticle(article.artID).subscribe(res => {this.articleComponent.ngOnInit();});
  }

  submitComment(id: number, comment: String){
    this.articleService.addComment(id, comment).subscribe(res => {this.articleComponent.ngOnInit();});
  }

  delete(article: Article): void{
    this.articleService.deleteArticle(article.artID).subscribe(res => {this.articleComponent.ngOnInit();});
  }

}
