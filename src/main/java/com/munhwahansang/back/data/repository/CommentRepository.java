package com.munhwahansang.back.data.repository;

import com.munhwahansang.back.data.dto.comment.response.CommentFindResponse;
import com.munhwahansang.back.data.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<CommentFindResponse> findAllByPostIdAndIsDeletedFalse(Long postId);

}
