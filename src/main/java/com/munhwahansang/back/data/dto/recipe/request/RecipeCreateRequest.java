package com.munhwahansang.back.data.dto.recipe.request;


import com.munhwahansang.back.data.entity.Recipe;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public record RecipeCreateRequest(
        String title,
        String country,
        Recipe.Difficulty difficulty,
        String description,
        MultipartFile image,

        String ingredients
) {}