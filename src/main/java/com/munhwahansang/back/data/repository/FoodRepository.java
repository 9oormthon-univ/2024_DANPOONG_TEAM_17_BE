package com.munhwahansang.back.data.repository;

import com.munhwahansang.back.data.dto.food.response.FoodFindResponse;
import com.munhwahansang.back.data.entity.Food;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<Food, Integer> {

    Food findFoodByName(String name);

    Page<FoodFindResponse> findFoodByNameContaining(String name, Pageable pageable);

    Page<FoodFindResponse> findFoodByNameNotContaining(String name, Pageable pageable);

    Page<FoodFindResponse> findFoodByIngredientsNotContaining(String ingredients, Pageable pageable);

}
