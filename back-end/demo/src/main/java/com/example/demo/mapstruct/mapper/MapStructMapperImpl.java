package com.example.demo.mapstruct.mapper;

import com.example.demo.mapstruct.dtos.ArticleDto;
import com.example.demo.mapstruct.dtos.UserDto;
import com.example.demo.models.Article;
import com.example.demo.models.User;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class MapStructMapperImpl implements MapStructMapper{

    @Override
    public ArticleDto articleToArticleDto(Article article){
        ArticleDto a = new ArticleDto();
        a.setTitle(article.getTitle());
        a.setBody(article.getBody());
        return a;
    }

    @Override
    public Article articleDtoToArticle(ArticleDto articleDto){
        Article a = new Article();
        a.setTitle(articleDto.getTitle());
        a.setBody(articleDto.getBody());
        a.setCreatedAt(new Date());
        return a;
    }

    @Override
    public UserDto userToUserDto(User user){
        UserDto u = new UserDto();
        u.setUsername(user.getUsername());
        u.setPassword(user.getPassword());
        u.setEmail(user.getEmail());
        u.setMobileNumber(user.getMobileNumber());

        return u;
    }

    @Override
    public User userDtoToUser(UserDto userDto){
        User u = new User();
        u.setUsername(userDto.getUsername());
        u.setPassword(userDto.getPassword());
        u.setEmail(userDto.getEmail());
        u.setMobileNumber(userDto.getMobileNumber());

        return u;

    }

}
