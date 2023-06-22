import { Component, OnInit } from '@angular/core';
import { ArticleService } from '../services/article.service';
import { ArticleComponent } from '../articles/article.component';

@Component({
  selector: 'app-article-post',
  templateUrl: './article-post.component.html',
  styleUrls: ['./article-post.component.css']
})
export class ArticlePostComponent implements OnInit {

  title: String = 'Lorem Ipsum';
  body: String = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries";

  constructor(private articleService: ArticleService, private articleComponent: ArticleComponent){}

  ngOnInit(): void {
      
  }

  submitArticle(): void {

    const record = {
      title: this.title,
      body: this.body
    }
 
    this.articleService.postArticle(record).subscribe(res => {
      console.log(`Article added in succefully`)
      //window.location.reload();
      this.articleComponent.ngOnInit();
    })
  }
}
