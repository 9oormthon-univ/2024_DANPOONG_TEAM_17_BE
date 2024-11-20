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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    private String title;

    private String content;

    private LocalDateTime createdAt;

    private LocalDateTime meetAt;

    private Boolean isDeleted;

    private String location;

    private String imagePath;


    private Post(User user, Category category, String title, String content, LocalDateTime meetAt, String location) {
        this.user = user;
        this.title = title;
        this.category = category;
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

    public static Post of(PostCreateRequest request, User user, Category category) {
        return new Post(user, category, request.title(), request.content(), request.dateTime(),request.location());
    }

}
