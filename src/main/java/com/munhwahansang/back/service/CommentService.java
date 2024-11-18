package com.munhwahansang.back.service;

import com.munhwahansang.back.data.dto.comment.request.CommentCreateRequest;
import com.munhwahansang.back.data.dto.comment.request.CommentUpdateRequest;
import com.munhwahansang.back.data.dto.comment.response.CommentFindResponse;
import com.munhwahansang.back.data.entity.Comment;
import com.munhwahansang.back.data.entity.Post;
import com.munhwahansang.back.data.entity.User;
import com.munhwahansang.back.data.repository.CommentRepository;
import com.munhwahansang.back.data.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    private final PostRepository postRepository;

    public void createComment(CommentCreateRequest request, Long postId, User user) {
        Post post = postRepository.findById(postId).orElseThrow();
        Comment comment = Comment.of(user, post, request.content());
        commentRepository.save(comment);
    }

    public void updateComment(CommentUpdateRequest request, Long id) {
        Comment comment = commentRepository.findById(id).orElseThrow();
        comment.update(request);
    }

    public void deleteComment(Long id) {
        Comment comment = commentRepository.findById(id).orElseThrow();
        comment.delete();
    }

    public List<CommentFindResponse> findPostComments(Long postId) {
        return commentRepository.findAllByPostIdAndIsDeletedFalse(postId);
    }


}
