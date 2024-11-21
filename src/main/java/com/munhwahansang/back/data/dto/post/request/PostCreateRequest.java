package com.munhwahansang.back.data.dto.post.request;

import java.time.LocalDateTime;

public record PostCreateRequest(String title, String content, LocalDateTime dateTime,String location, Long categoryId) {
}
