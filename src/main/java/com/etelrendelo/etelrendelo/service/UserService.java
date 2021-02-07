package com.etelrendelo.etelrendelo.service;

import com.etelrendelo.etelrendelo.entity.Food;
import com.etelrendelo.etelrendelo.entity.User;
import com.etelrendelo.etelrendelo.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private static Logger logger = LoggerFactory.getLogger(OrderService.class);

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public User createUser(User user) {
        logger.info("Creating user from input data: {}", user);
        User created = userRepository.save(user);
        logger.debug("Created user: {}", created);
        return created;
    }

    public List<User> getUsers() {
        logger.info("Listing all users ...");
        List<User> users = userRepository.findAll();
        logger.debug("Found {} users", users.size());
        return users;
    }
}
