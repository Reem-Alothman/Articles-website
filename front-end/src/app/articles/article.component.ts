import { Component, OnInit } from '@angular/core';
import { ArticleService } from '../services/article.service';
import { Article } from '../modles/Article';



@Component({
  selector: 'article-component',
  templateUrl: './article.component.html',
  styleUrls: ['./article.component.css']
})

export class ArticleComponent implements OnInit{

  articles: Article[] = [];
  articlesChunk: Article[] = [];

  constructor(private articleService: ArticleService) { }

    ngOnInit(): void {
      this.articleService.getArticles().subscribe(res => {
        this.articles = res;
        this.paginate({first:0, rows:5});
        //console.log(this.articles);
      }, (err) => {
        console.log(err);
      });

  }

  disableArticle(article: Article): void{

  }
  
  paginate(event: { first: number | undefined; rows: any; }) {
    this.articlesChunk = this.articles.slice(event.first, event.first+event.rows);
 }

}
