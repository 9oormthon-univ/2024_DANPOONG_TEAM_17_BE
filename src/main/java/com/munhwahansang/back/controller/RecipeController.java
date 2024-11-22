package com.munhwahansang.back.controller;

import com.munhwahansang.back.data.dto.recipe.request.RecipeCreateRequest;
import com.munhwahansang.back.data.dto.recipe.response.RecipeResponse;
import com.munhwahansang.back.data.entity.Recipe;
import com.munhwahansang.back.service.RecipeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/recipes")
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }


    //모든 레시피 목록 조회
    @GetMapping
    public ResponseEntity<Page<RecipeResponse>> getAllRecipes(
            @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<RecipeResponse> recipes = recipeService.getAllRecipes(pageable);
        return ResponseEntity.ok(recipes);
    }



    // 특정 제목 포함 검색
    @GetMapping("/search")
    public ResponseEntity<Page<RecipeResponse>> searchByTitle(
            @RequestParam String title,
            Pageable pageable) {
        Page<RecipeResponse> recipes = recipeService.searchByTitle(title, pageable);
        return ResponseEntity.ok(recipes);
    }



    // 레시피 추가
    @PostMapping
    public ResponseEntity<RecipeResponse> createRecipe(@RequestBody RecipeCreateRequest request) throws IOException {
        System.out.println("Received Description: " + request.description());
        RecipeResponse recipeResponse = recipeService.createRecipe(request);
        return ResponseEntity.ok(recipeResponse);
    }


    // 레시피 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecipe(@PathVariable Integer id) {
        recipeService.deleteRecipe(id);
        return ResponseEntity.noContent().build();
    }
}
