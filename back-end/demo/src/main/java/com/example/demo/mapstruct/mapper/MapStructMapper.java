package com.example.demo.mapstruct.mapper;

import com.example.demo.mapstruct.dtos.ArticleDto;
import com.example.demo.mapstruct.dtos.UserDto;
import com.example.demo.models.Article;
import com.example.demo.models.User;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring"
)

public interface MapStructMapper {
    ArticleDto articleToArticleDto(Article article);
    Article articleDtoToArticle(ArticleDto articleDto);
    UserDto userToUserDto(User user);
    User userDtoToUser(UserDto userDto);
}
