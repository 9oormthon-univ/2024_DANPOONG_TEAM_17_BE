package com.munhwahansang.back.data.repository;

import com.munhwahansang.back.data.dto.recipe.response.RecipeResponse;
import com.munhwahansang.back.data.entity.Recipe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RecipeRepository extends JpaRepository<Recipe, Integer> {


    // 모든 레시피 조회
    Page<Recipe> findAll(Pageable pageable);

    // 특정 제목을 포함하는 레시피 검색
    Page<Recipe> findByTitleContaining(String title, Pageable pageable);

    // 특정 국가의 레시피 검색
    Page<RecipeResponse> findByCountry(String country, Pageable pageable);

    // 특정 난이도에 해당하는 레시피 검색
    Page<RecipeResponse> findByDifficulty(Recipe.Difficulty difficulty, Pageable pageable);
}
