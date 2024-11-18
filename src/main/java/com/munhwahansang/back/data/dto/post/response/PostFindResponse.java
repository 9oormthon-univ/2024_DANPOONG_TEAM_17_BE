package com.munhwahansang.back.data.dto.post.response;

import java.time.LocalDateTime;

public record PostFindResponse(String imagePath, String userName, LocalDateTime createdAt, String title, String content,
                               Long id,
                               String location) {
}
