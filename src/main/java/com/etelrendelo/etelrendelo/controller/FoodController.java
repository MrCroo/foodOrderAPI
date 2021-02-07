package com.etelrendelo.etelrendelo.controller;

import com.etelrendelo.etelrendelo.entity.Food;
import com.etelrendelo.etelrendelo.service.FoodService;
import com.etelrendelo.etelrendelo.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/foods")
public class FoodController {

    private static Logger logger = LoggerFactory.getLogger(OrderService.class);

    private final FoodService foodService;

    @Autowired
    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }

    @PostMapping("/food")
    @ResponseStatus(HttpStatus.CREATED)
    public Food createFood(@RequestBody Food food){
        logger.info("Received Post request to create food: {}", food);
        return foodService.createFood(food);
    }

    @GetMapping("/allfoods")
    public List<Food> getFoods(){
        logger.info("Getting all food..");
        return foodService.getFoods();
    }

    @GetMapping("/food/{id}")
    public Food getFoodById(@PathVariable(name = "id") Long id){
        logger.info("Getting id {}", id);
        Optional<Food> food = foodService.getFoodById(id);
        if(food.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No food found on " + id);
        }
        return food.get();
    }
}
