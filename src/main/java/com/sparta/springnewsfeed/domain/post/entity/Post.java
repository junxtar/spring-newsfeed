package com.sparta.springnewsfeed.domain.post.entity;

import com.sparta.springnewsfeed.domain.comment.entity.Comment;
import com.sparta.springnewsfeed.domain.post.dto.PostRequestDto;
import com.sparta.springnewsfeed.domain.user.entity.User;
import com.sparta.springnewsfeed.global.util.BaseTime;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "post")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private  Long id;

    @Column(nullable = false)
    private  String title;

    @Column(nullable = false)
    private  String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
    List<Comment> commentList;


    public Post (PostRequestDto requestDto, User user) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.user = user;
    }


    public void update(PostRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
    }
}
