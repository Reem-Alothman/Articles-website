package com.example.demo.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name="users")
@AllArgsConstructor
@NoArgsConstructor

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(insertable = false, updatable = false)
    private long usrID;
    @Column(unique = true, nullable = false)
    private String username;
    @Column(nullable=false)
    private int mobileNumber;
    @Column(nullable=false)
    private String password;

    @Column(unique = true, nullable = false)
    private String email;

    @OneToMany(targetEntity=Comment.class)
    private List<Comment> comments;

    @OneToMany( targetEntity=Article.class)
    private List<Article> articles;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "roles_privileges",
            joinColumns = @JoinColumn(
                    name = "usr_id", referencedColumnName = "usrID"),
            inverseJoinColumns = @JoinColumn(
                    name = "prv_id", referencedColumnName = "prvID"))
    private List<Privilege> privileges;

}
