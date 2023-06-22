package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(insertable = false, updatable = false)
    private long cmtID;

    @Column(nullable = false)
    private Date createdAt;

    @Column(nullable = false, length = 100)
    private String comment;

    @JsonIgnore
    @ManyToOne(optional = false)
    @JoinColumn(name ="artID_fk", nullable = false, referencedColumnName = "artID")
    private Article article;

    @JsonIgnore
    @ManyToOne(optional = false)
    @JoinColumn(name = "usrID_fk", nullable = false, referencedColumnName = "usrID")
    private User author;

    public String getUsername(){
        return author.getUsername();
    }

}

