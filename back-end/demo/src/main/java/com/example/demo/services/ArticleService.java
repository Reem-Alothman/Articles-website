package com.example.demo.services;

import com.example.demo.mapstruct.dtos.ArticleDto;
import com.example.demo.mapstruct.mapper.MapStructMapperImpl;
import com.example.demo.models.Comment;
import com.example.demo.repositories.ArticleRepository;
import com.example.demo.repositories.CommentRepository;
import com.example.demo.models.User;
import com.example.demo.repositories.UserRepository;
import com.example.demo.models.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ArticleService {
    @Autowired private ArticleRepository articleRepository;
    @Autowired private CommentRepository commentRepository;
    @Autowired private UserRepository userRepository;
    @Autowired MapStructMapperImpl mapStructMapper;

    public void createArticle(ArticleDto articledto){
        User currentUser = userRepository.findUserByUsername(
                SecurityContextHolder.getContext().getAuthentication().getName()
        );

        Article article = mapStructMapper.articleDtoToArticle(articledto);
        article.setAuthor(currentUser);
        System.out.println(article.toString());
        articleRepository.save(article);

    }
    public List<Article> getAllArticles(){
        List<Article> articles = articleRepository.findAllActiveArticles();
        return articles;
    }
    public Article getArticleByID(long id) {
        Article article = articleRepository.findById(id).orElseThrow(()-> new RuntimeException("Can't find article with id "+id));
        return article;
    }
    public List<Comment> getArticleComments(long id){
        Article art = articleRepository.findById(id).orElseThrow(()-> new RuntimeException("Can't find article with id "+id));
        List<Comment> comments = art.getComments();
        return comments;

    }
    public void createComment(String cmt, long id){

            Article art = articleRepository.findById(id).orElseThrow(()-> new RuntimeException("Can't find article with id "+id));

            User currentUser = userRepository.findUserByUsername(
                    SecurityContextHolder.getContext().getAuthentication().getName()
            );

            Comment newCmt = new Comment();
            newCmt.setComment(cmt);
            newCmt.setAuthor(currentUser);
            newCmt.setArticle(art);
            newCmt.setCreatedAt(new Date());

            commentRepository.save(newCmt);

            }
    public void deleteArticleById(long id){
        Article a = articleRepository.findById(id).orElseThrow(()-> new RuntimeException("Can't find article with id "+id));

        User currentUser = userRepository.findUserByUsername(
                SecurityContextHolder.getContext().getAuthentication().getName()
        );

        if (a.getAuthor().getUsername() == currentUser.getUsername()) {
                    articleRepository.deleteById(id);
        } else {
            throw new RuntimeException ("you are not authorized to delete this article");
        }



    }
    public void like(long id){
        Article art = articleRepository.findById(id).orElseThrow(()-> new RuntimeException("Can't find article with id "+id));
        art.incrementLikes();
        articleRepository.save(art);
    }
    public void dislike(long id){
        Article art = articleRepository.findById(id).orElseThrow(()-> new RuntimeException("Can't find article with id "+id));
        art.incrementDislikes();
        articleRepository.save(art);
    }
    public void disableArticle(long id){
        Article art = articleRepository.findById(id).orElseThrow(()-> new RuntimeException("Can't find article with id "+id));
        art.setDisabled(true);
        articleRepository.save(art);
    }
    public void enableArticle(long id){
        Article art = articleRepository.findById(id).orElseThrow(()-> new RuntimeException("Can't find article with id "+id));
        art.setDisabled(false);
        articleRepository.save(art);
    }

}
