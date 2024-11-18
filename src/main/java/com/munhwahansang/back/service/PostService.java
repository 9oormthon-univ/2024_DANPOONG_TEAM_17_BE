package com.munhwahansang.back.service;

import com.munhwahansang.back.data.dto.post.response.PostFindResponse;
import com.munhwahansang.back.data.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public PostFindResponse findPost(Long id) {
        return postRepository.queryById(id);
    }

    public Page<PostFindResponse> findAllPost(Pageable pageable) {
        return postRepository.findAllByIsDeletedFalse(pageable);
    }

}
