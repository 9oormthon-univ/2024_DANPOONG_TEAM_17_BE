package com.munhwahansang.back.data.dto.food.request;

import org.springframework.web.multipart.MultipartFile;

public record FoodCreateRequest(MultipartFile file, String name, String explanation, String ingredients) {

}
