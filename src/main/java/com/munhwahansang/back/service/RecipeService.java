package com.munhwahansang.back.service;

import com.munhwahansang.back.data.dto.recipe.request.RecipeCreateRequest;
import com.munhwahansang.back.data.dto.recipe.response.RecipeResponse;
import com.munhwahansang.back.data.entity.Food;
import com.munhwahansang.back.data.entity.Recipe;
import com.munhwahansang.back.data.repository.FoodRepository;
import com.munhwahansang.back.data.repository.RecipeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;
    private final FoodRepository foodRepository;

    public RecipeService(RecipeRepository recipeRepository, FoodRepository foodRepository) {
        this.recipeRepository = recipeRepository;
        this.foodRepository = foodRepository;
    }


    // 제목 포함 검색
    public Page<RecipeResponse> searchByTitle(String title, Pageable pageable) {
        return recipeRepository.findByTitleContaining(title, pageable)
                .map(Recipe::convertToRecipeResponse);
    }

    // 레시피 생성
    @Transactional
    public RecipeResponse createRecipe(RecipeCreateRequest request) {

        Recipe recipe = Recipe.of(request);
        Recipe savedRecipe = recipeRepository.save(recipe);
        return savedRecipe.convertToRecipeResponse();
    }

    // 레시피 삭제
    @Transactional
    public void deleteRecipe(Integer id) {
        recipeRepository.deleteById(id);
    }

    // 모든 레시피 목록 조회
    public Page<RecipeResponse> getAllRecipes(Pageable pageable) {
        return recipeRepository.findAll(pageable)
                .map(Recipe::convertToRecipeResponse);
    }



}
