package com.etelrendelo.etelrendelo.repository;

import com.etelrendelo.etelrendelo.entity.FoodOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<FoodOrder, Long> {
}
