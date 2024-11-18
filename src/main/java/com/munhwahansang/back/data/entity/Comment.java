package com.munhwahansang.back.data.entity;

import com.munhwahansang.back.data.dto.comment.request.CommentUpdateRequest;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String content;

    private LocalDateTime createdAt;

    private Boolean isDeleted;

    private Comment(User user, Post post, String content) {
        this.user = user;
        this.post = post;
        this.content = content;
        this.createdAt = LocalDateTime.now();
        this.isDeleted = false;
    }

    public void update(CommentUpdateRequest request) {
        this.content = request.content();
    }

    public void delete() {
        this.isDeleted = true;
    }

    public static Comment of(User user, Post post, String content) {
        return new Comment(user, post, content);
    }


}
