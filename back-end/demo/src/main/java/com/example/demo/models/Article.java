package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
@Data
@Entity
@Table(name="articles")
@AllArgsConstructor
@NoArgsConstructor

public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(insertable = false, updatable = false)
    private long artID;

    @Column(nullable = false, length = 100)
    private String title;

    @NotNull
    @Column(nullable = false, length = 500)
    private String body;

    @JsonIgnore
    @ManyToOne(optional = false)
    @JoinColumn(name = "usrID_fk", nullable = false, referencedColumnName = "usrID")
    private User author;

    @Column(nullable = false)
    private Date createdAt;

    @Column
    private int likes;

    @Column
    private int dislikes;

    @Column(nullable = false)
    private boolean disabled;

    @OneToMany( targetEntity=Comment.class,  cascade = CascadeType.ALL, mappedBy="article")
    private List<Comment> comments;

    public void incrementLikes(){
        likes++;
    }
    public void incrementDislikes(){
        dislikes++;
    }

    public String getUsername(){
        return author.getUsername();
    }

}

