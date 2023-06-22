package com.example.demo.controllers;

import com.example.demo.mapstruct.dtos.ArticleDto;
import com.example.demo.models.Comment;
import com.example.demo.models.Article;
import com.example.demo.repositories.ArticleRepository;
import com.example.demo.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/article")

public class ArticleController {
    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private ArticleService articleService;

    @PostMapping
    public ResponseEntity<String> postArticle(@Valid @RequestBody ArticleDto articledto){
        articleService.createArticle(articledto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping(value ="/{id}")
    public ResponseEntity<Article> getArticleByID(@PathVariable Long id) {
        Article article = articleService.getArticleByID(id);
        return ResponseEntity.ok(article);
    }

    @GetMapping
    public ResponseEntity<List<Article>> getArticles() {
        List<Article> articles = articleService.getAllArticles();
        return ResponseEntity.ok(articles);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteArticle(@PathVariable Long id){
        articleService.deleteArticleById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/{id}/comment")
    public ResponseEntity<String> createComment(@RequestBody String comment, @PathVariable Long id) {
        articleService.createComment(comment, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value ="/{id}/comment")
    public ResponseEntity<List<Comment>> getComments(@PathVariable Long id) {
        List<Comment> comments = articleService.getArticleComments(id);
        return ResponseEntity.ok(comments);
    }

    @PutMapping(value ="/{id}/like")
    public ResponseEntity<String> addLike(@PathVariable Long id) {
        articleService.like(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value ="/{id}/dislike")
    public ResponseEntity<String> addDislike(@PathVariable Long id) {
        articleService.dislike(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value ="/{id}/disable")
    public ResponseEntity<String> disableArticle(@PathVariable Long id) {
        articleService.disableArticle(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value ="/{id}/enable")
    public ResponseEntity<String> enableArticle(@PathVariable Long id) {
        articleService.enableArticle(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
