package com.sparta.springnewsfeed.domain.user.entity;

import com.sparta.springnewsfeed.domain.post.entity.Post;
import com.sparta.springnewsfeed.domain.utils.BaseTime;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "users")
public class User extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;
    private String username;
    private String password;
    private String content;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    List<Post> postList;

    public void setContent(String content) {
        this.content = content;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Builder
    private User(Long id, String username, String password, String content) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.content = content;
    }

}
