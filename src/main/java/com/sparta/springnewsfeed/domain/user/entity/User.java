package com.sparta.springnewsfeed.domain.user.entity;

import com.sparta.springnewsfeed.domain.post.entity.*;
import jakarta.persistence.*;
import java.util.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;
    private String username;
    private String password;
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    List<Post> postList;
    private String content;

    public void setContent(String content) {
        this.content = content;
    }

    @Builder
    public User(String username, String password, String content) {
        this.username = username;
        this.password = password;
        this.content = content;
    }
}
