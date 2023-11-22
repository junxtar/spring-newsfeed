package com.sparta.springnewsfeed.domain.comment.entity;


import com.sparta.springnewsfeed.domain.comment.dto.CommentRequestDto;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@Table(name = "comment")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment {

    @Id
    @Column(name = "comment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 500)
    private String commentText;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @Builder
    private Comment(Long id, String commentText, User user, Post post) {
        this.id = id;
        this.commentText = commentText;
        this.user = user;
        this.post = post;
    }
}
