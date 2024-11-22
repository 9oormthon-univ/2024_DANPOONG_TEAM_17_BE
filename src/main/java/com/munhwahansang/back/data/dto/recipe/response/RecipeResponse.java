package com.munhwahansang.back.data.dto.recipe.response;

import com.munhwahansang.back.data.entity.Recipe;

import java.time.LocalDateTime;
import java.util.List;

public record RecipeResponse(
        Integer id,
        String title,
        String country,
        Recipe.Difficulty difficulty,
        String description,
        String imagePath,

        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        String ingredients
) {}