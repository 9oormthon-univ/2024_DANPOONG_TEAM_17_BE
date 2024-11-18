package com.munhwahansang.back.data.repository;

import com.munhwahansang.back.data.dto.post.response.PostFindResponse;
import com.munhwahansang.back.data.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

    PostFindResponse queryById(Long id);

    Page<PostFindResponse> findAllByIsDeletedFalse(Pageable pageable);


}
