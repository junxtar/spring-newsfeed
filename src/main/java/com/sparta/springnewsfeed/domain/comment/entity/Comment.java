package com.sparta.springnewsfeed.domain.comment.entity;


import com.sparta.springnewsfeed.domain.comment.constant.CommentConstant;
import com.sparta.springnewsfeed.domain.commentLike.entity.Likes;
import com.sparta.springnewsfeed.domain.post.entity.Post;
import com.sparta.springnewsfeed.domain.user.entity.User;
import com.sparta.springnewsfeed.domain.utils.BaseTime;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseTime {

    @Id
    @Column(name = "comment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String commentText;

    @NotNull
    private Long likeCnt = CommentConstant.DEFAULT_LIKE_CNT;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @OneToMany(mappedBy = "comment", cascade = CascadeType.REMOVE)
    List<Likes> likesList = new ArrayList<>();

    @Builder
    private Comment(Long id, String commentText, User user, Post post, Long likeCnt) {
        this.id = id;
        this.commentText = commentText;
        this.user = user;
        this.post = post;
        this.likeCnt = likeCnt;
    }

    public void update(String commentText) {
        this.commentText = commentText;
    }

    public void updateLikeCnt(Boolean updated) {
        if (updated) {
            this.likeCnt++;
            return;
        }
        this.likeCnt--;
    }
}
