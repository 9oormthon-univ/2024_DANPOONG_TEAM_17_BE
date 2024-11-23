package com.munhwahansang.back.service;

import com.munhwahansang.back.data.dto.food.request.FoodCreateRequest;
import com.munhwahansang.back.data.dto.food.request.FoodFindRequest;
import com.munhwahansang.back.data.dto.food.request.FoodUpdateRequest;
import com.munhwahansang.back.data.dto.food.response.FoodFindResponse;
import com.munhwahansang.back.data.entity.Food;
import com.munhwahansang.back.data.repository.FoodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FoodService {

    private final FoodRepository foodRepository;

    @Transactional
    public Food createFood(String imagePath, FoodCreateRequest request) {
        return foodRepository.save(Food.of(imagePath,request));
    }

    @Transactional
    public void updateFood(FoodUpdateRequest request) {
        Food food = foodRepository.findFoodByName(request.name());
        food.update(request);
    }

    public FoodFindResponse findFood(FoodFindRequest request) {
        Food food = foodRepository.findFoodByName(request.name());
        return food.convertToFindResponse();
    }

    public Page<FoodFindResponse> searchByIncludeFoodName(String name, Pageable pageable) {
        return foodRepository.findFoodByNameContaining(name, pageable);
    }

    public Page<FoodFindResponse> searchByExcludeFoodName(String name, Pageable pageable) {
        return foodRepository.findFoodByNameNotContaining(name, pageable);
    }

    public Page<FoodFindResponse> searchByExcludeIngredients(String ingredients, Pageable pageable) {
        return foodRepository.findFoodByIngredientsNotContaining(ingredients, pageable);
    }

}
