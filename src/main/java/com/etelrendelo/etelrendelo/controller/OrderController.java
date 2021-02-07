package com.etelrendelo.etelrendelo.controller;

import com.etelrendelo.etelrendelo.entity.Food;
import com.etelrendelo.etelrendelo.entity.FoodOrder;
import com.etelrendelo.etelrendelo.entity.User;
import com.etelrendelo.etelrendelo.service.FoodService;
import com.etelrendelo.etelrendelo.service.OrderService;
import com.etelrendelo.etelrendelo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/orders")
public class OrderController {

    private static Logger logger = LoggerFactory.getLogger(OrderService.class);

    private final OrderService orderService;
    private final FoodService foodService;
    private final UserService userService;

    @Autowired
    public OrderController(OrderService orderService, FoodService foodService, UserService userService) {
        this.orderService = orderService;
        this.foodService = foodService;
        this.userService = userService;
    }

    @PostMapping("order")
    @ResponseStatus(HttpStatus.CREATED)
    public FoodOrder createOrder(@RequestBody FoodOrder order){
        logger.info("Received Post request to create order: {}", order);
        return orderService.createOrder(order);
    }

    @PostMapping("user")
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody User user){
        logger.info("Received Post request to create user: {}", user);
        return userService.createUser(user);
    }

    @GetMapping("/allorders")
    public List<FoodOrder> getOrders(){
        logger.info("Getting all orders");
        return orderService.getOrders();
    }

    @GetMapping("/allusers")
    public List<User> getUsers(){
        logger.info("Getting all users...");
        return userService.getUsers();
    }

    @GetMapping("/order/{id}")
    public FoodOrder getOrderById(@PathVariable(name = "id") Long id){
        logger.info("Getting order with id {}", id);
        Optional<FoodOrder> order = orderService.getOrderById(id);
        if(order.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No order found on " + id);
        }
        return order.get();
    }

    @PutMapping("/order/{id}/{foodId}")
    public FoodOrder putFoodToOrder(@PathVariable(name = "id") Long id, @PathVariable(name = "foodId") Long foodId){
        logger.info("Received PUT foodID: {}, to orderID: {}", id, foodId);
        Optional<FoodOrder> order = orderService.getOrderById(id);
        Optional<Food> food = foodService.getFoodById(id);
        if(order.isEmpty() || food.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No order or food found ons");
        }
        FoodOrder orderResult = orderService.mapFoodToOrder(order.get(), food.get());
        return orderResult;
    }
}
