package com.etelrendelo.etelrendelo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ORDERED_FOOD")
public class FoodOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    private List<Food> foods = new ArrayList<>();
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "USER_TO_ORDER")
    private User orderedBy;

    public void addFood(Food food) {
        foods.add(food);
    }

    public void removeFood( Food food) {
        foods.remove(food);
    }

}
