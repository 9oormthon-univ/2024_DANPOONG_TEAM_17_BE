package com.munhwahansang.back.data.entity;

import com.munhwahansang.back.data.dto.recipe.request.RecipeCreateRequest;
import com.munhwahansang.back.data.dto.recipe.response.RecipeResponse;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "recipes")
@Getter
@Setter
@NoArgsConstructor
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false)
    private String country;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private Difficulty difficulty;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(length = 300)
    private String imagePath;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String ingredients;


    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now(); // 생성일

    @Column(nullable = false)
    private LocalDateTime updatedAt = LocalDateTime.now(); // 수정일

    @PreUpdate
    public void updateTimestamp() {
        this.updatedAt = LocalDateTime.now();
    }

    public Integer getId() {
        return id;
    }
    public enum Difficulty {
        Easy,
        Medium,
        Hard
    }

    public Recipe(String title, String country, Difficulty difficulty, String ingredients, String description) {
        this.title = title;
        this.country = country;
        this.difficulty = difficulty;
        this.ingredients = ingredients;
        this.description = description;
        this.createdAt = LocalDateTime.now(); // 생성 시간 자동 설정
        this.updatedAt = LocalDateTime.now(); // 수정 시간 초기화
    }


    public static Recipe of(RecipeCreateRequest request) {
        return new Recipe(
                request.title(),
                request.country(),
                request.difficulty(),
                request.ingredients(),
                request.description()
        );
    }


    public RecipeResponse convertToRecipeResponse() {
        return new RecipeResponse(
                id,
                title,
                country,
                difficulty,
                description,
                imagePath,
                createdAt,
                updatedAt,
                ingredients // JSON 문자열로 저장된 재료 정보
        );
    }

}
