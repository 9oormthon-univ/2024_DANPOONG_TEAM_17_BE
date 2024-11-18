package com.munhwahansang.back.data.entity;

import com.munhwahansang.back.data.dto.post.request.PostCreateRequest;
import com.munhwahansang.back.data.dto.post.request.PostUpdateRequest;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "posts")
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String title;

    private String content;

    private LocalDateTime createdAt;

    private LocalDateTime meetAt;

    private Boolean isDeleted;

    private String location;

    private String imagePath;


    private Post(User user, String title, String content, LocalDateTime meetAt,String location) {
        this.user = user;
        this.title = title;
        this.content = content;
        this.createdAt = LocalDateTime.now();
        this.isDeleted = false;
        this.meetAt = meetAt;
        this.location = location;
        this.imagePath = null;
    }

    public void delete() {
        this.isDeleted = true;
    }

    public void update(PostUpdateRequest updateRequest) {
        this.title = updateRequest.title();
        this.content = updateRequest.content();
    }

    public static Post of(PostCreateRequest request, User user) {
        return new Post(user, request.title(), request.content(), request.dateTime(),request.location());
    }

}
