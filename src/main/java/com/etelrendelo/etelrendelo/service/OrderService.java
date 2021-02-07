package com.etelrendelo.etelrendelo.service;

import com.etelrendelo.etelrendelo.entity.Food;
import com.etelrendelo.etelrendelo.entity.FoodOrder;
import com.etelrendelo.etelrendelo.entity.User;
import com.etelrendelo.etelrendelo.repository.OrderRepository;
import com.etelrendelo.etelrendelo.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private static Logger logger = LoggerFactory.getLogger(OrderService.class);

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }

    public FoodOrder createOrder(FoodOrder order) {
        logger.info("Creating Order from input data: {}", order);
        FoodOrder created = orderRepository.save(order);
        logger.debug("Created order: {}", created);
        return created;
    }

    public List<FoodOrder> getOrders() {
        logger.info("Listing all orders ...");
        List<FoodOrder> orders = orderRepository.findAll();
        logger.debug("Found {} orders", orders.size());
        return orders;
    }

    public Optional<FoodOrder> getOrderById(Long id) {
        logger.info("Geting Order id {}", id);
        Optional<FoodOrder> order = orderRepository.findById(id);
        logger.debug("Found order {}", order.isPresent() ? order.get() : "n.a.");
        return order;
    }

    public FoodOrder mapFoodToOrder(FoodOrder order, Food food) {
        logger.info("Adding food {} to order {}",food, order );
        order.addFood(food);
        FoodOrder updated = orderRepository.save(order);
        logger.debug("Updated order {}", updated);
        return updated;
    }
}
