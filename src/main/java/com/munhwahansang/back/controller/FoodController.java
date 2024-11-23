package com.munhwahansang.back.controller;

import com.munhwahansang.back.data.dto.food.request.FoodCreateRequest;
import com.munhwahansang.back.data.dto.food.request.FoodFindRequest;
import com.munhwahansang.back.data.dto.food.request.FoodUpdateRequest;
import com.munhwahansang.back.data.dto.food.response.FoodFindResponse;
import com.munhwahansang.back.service.FoodService;
import com.munhwahansang.back.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/foods")
@RequiredArgsConstructor
public class FoodController {

    private final FoodService foodService;

    private final ImageService imageService;

    private static final String INCLUDE = "include";

    private static final String EXCLUDE = "exclude";


    @GetMapping
    public ResponseEntity<FoodFindResponse> findFood(@RequestBody FoodFindRequest request) {
        FoodFindResponse response = foodService.findFood(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void createFood(@RequestPart FoodCreateRequest request,@RequestPart MultipartFile file) {

        String imagePath = imageService.uploadFile(file);

        foodService.createFood(imagePath, request);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void updateFood(@RequestBody FoodUpdateRequest request) {
        foodService.updateFood(request);
    }

    @GetMapping("/search")
    public ResponseEntity<Page<FoodFindResponse>> searchBy(@RequestParam String keyword, @RequestParam String filter, @RequestParam Boolean isIngredients, Pageable pageable) {

        if (isIngredients) {
            return new ResponseEntity<>(foodService.searchByExcludeIngredients(keyword, pageable), HttpStatus.OK);
        }

        if (filter.equals(INCLUDE)) {
            return new ResponseEntity<>(foodService.searchByIncludeFoodName(keyword, pageable), HttpStatus.OK);
        }

        if (filter.equals(EXCLUDE)) {
            return new ResponseEntity<>(foodService.searchByExcludeFoodName(keyword, pageable), HttpStatus.OK);
        }

        throw new IllegalArgumentException();
    }

}
