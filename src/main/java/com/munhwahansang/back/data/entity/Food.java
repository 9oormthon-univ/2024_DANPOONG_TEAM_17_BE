package com.munhwahansang.back.data.entity;

import com.munhwahansang.back.data.dto.food.request.FoodCreateRequest;
import com.munhwahansang.back.data.dto.food.request.FoodUpdateRequest;
import com.munhwahansang.back.data.dto.food.response.FoodFindResponse;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "foods")
@NoArgsConstructor
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String explanation;

    private String ingredients;

    private String imagePath;

    public Food(String name, String explanation, String ingredients) {
        this.name = name;
        this.explanation = explanation;
        this.ingredients = ingredients;
        this.imagePath = null;
    }

    public Integer getId() {
        return id;
    }

    public static Food of(FoodCreateRequest request) {
        return new Food(request.name(), request.explanation(), request.ingredients());
    }

    public FoodFindResponse convertToFindResponse() {
        return new FoodFindResponse(imagePath,name, explanation, ingredients);
    }

    public void update(FoodUpdateRequest request) {
        this.name = request.name();
        this.explanation = request.explanation();
        this.ingredients = request.ingredients();
    }

}
