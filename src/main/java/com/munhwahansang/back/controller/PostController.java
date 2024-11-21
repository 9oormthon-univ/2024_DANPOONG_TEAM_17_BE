package com.munhwahansang.back.controller;

import com.munhwahansang.back.data.dto.post.response.PostCommentResponse;
import com.munhwahansang.back.data.dto.post.response.PostFindResponse;
import com.munhwahansang.back.service.CommentService;
import com.munhwahansang.back.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    private final CommentService commentService;

    //모든 게시글 조회
    @GetMapping
    public ResponseEntity<Page<PostFindResponse>> findAllPost(Pageable pageable) {
        return new ResponseEntity<>(postService.findAllPost(pageable), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostCommentResponse> findPost(@PathVariable Long id) {
        return new ResponseEntity<>(new PostCommentResponse(postService.findPost(id), commentService.findPostComments(id)), HttpStatus.OK);
    }

    // 카테고리별 게시글 조회
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<Page<PostFindResponse>> findPostsByCategory(@PathVariable Long categoryId, Pageable pageable) {
        return new ResponseEntity<>(postService.findPostsByCategory(categoryId, pageable), HttpStatus.OK);
    }

}
