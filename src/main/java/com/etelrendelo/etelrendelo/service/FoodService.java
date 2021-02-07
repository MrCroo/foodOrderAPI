package com.etelrendelo.etelrendelo.service;

import com.etelrendelo.etelrendelo.entity.Food;
import com.etelrendelo.etelrendelo.repository.FoodRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FoodService {

    private static Logger logger = LoggerFactory.getLogger(OrderService.class);

    private final FoodRepository foodRepository;

    @Autowired
    public FoodService(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    public Food createFood(Food food) {
        logger.info("Creating food from input data: {}", food);
        Food created = foodRepository.save(food);
        logger.debug("Created food: {}", created);
        return created;
    }

    public List<Food> getFoods() {
        logger.info("Listing all foods...");
        List<Food> foods = foodRepository.findAll();
        logger.debug("Found {} foods", foods.size());
        return foods;
    }

    public Optional<Food> getFoodById(Long id) {
        logger.info("Geting {} id", id);
        Optional<Food> foods = foodRepository.findById(id);
        logger.debug("Found {} foods", foods.isPresent() ? foods.get() : "n.a.");
        return foods;
    }
}
