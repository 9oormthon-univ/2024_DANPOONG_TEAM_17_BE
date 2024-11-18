package com.munhwahansang.back.data.dto.post.response;


import com.munhwahansang.back.data.dto.comment.response.CommentFindResponse;

import java.util.List;

public record PostCommentResponse(PostFindResponse postResponse, List<CommentFindResponse> commentFindResponseList) {
}
