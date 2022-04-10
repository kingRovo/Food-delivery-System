package com.fds.fooddeliverysystem.repository;

import com.fds.fooddeliverysystem.model.Restaurant;
import org.hibernate.mapping.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
import java.util.List;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant,Long> {


    @Query(value = "SELECT r FROM Restaurant r WHERE  r.opening_Time <= ?1 AND r.closing_Time >= ?1 ORDER BY r.name")
    List<Restaurant> allOpenRestaurant(LocalTime currentTime);
}
