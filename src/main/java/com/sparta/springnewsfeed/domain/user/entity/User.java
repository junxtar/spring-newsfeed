package com.sparta.springnewsfeed.domain.user.entity;

import com.sparta.springnewsfeed.domain.post.entity.Post;
import jakarta.persistence.*;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    @Column(nullable = false, unique = true)
    private String username;
    @Column
    private String password;
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    List<Post> postList;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
