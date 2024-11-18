package com.munhwahansang.back.controller;

import com.munhwahansang.back.data.dto.comment.request.CommentCreateRequest;
import com.munhwahansang.back.data.dto.comment.request.CommentUpdateRequest;
import com.munhwahansang.back.data.entity.User;
import com.munhwahansang.back.service.CommentService;
import com.munhwahansang.back.service.TokenService;
import com.munhwahansang.back.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    private final UserService userService;

    private final TokenService tokenService;

    @PostMapping
    public void createComment(@RequestHeader("Authorization") String token, @RequestBody CommentCreateRequest request, Long postId) {
        token = token.replace("Bearer ", "");
        Integer userId = tokenService.getUserId(token);
        User user = userService.findUserById(userId);
        commentService.createComment(request, postId, user);
    }

    @PutMapping("/{id}")
    public void updateComment(@RequestBody CommentUpdateRequest request, @PathVariable Long id) {
        commentService.updateComment(request, id);
    }

    @DeleteMapping("/{id}")
    public void deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
    }

}
