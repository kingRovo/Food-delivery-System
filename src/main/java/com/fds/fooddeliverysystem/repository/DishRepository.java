package com.fds.fooddeliverysystem.repository;

import com.fds.fooddeliverysystem.model.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DishRepository extends JpaRepository<Dish,Long> {


    @Query(value = "SELECT dish FROM Dish dish WHERE dish.name LIKE %?1%"
            + " OR dish.type LIKE %?1%"
            + " OR concat(dish.price,'') LIKE %?1%",nativeQuery = true)
    List<Dish> searchDishes(String keyword);

    @Query(value = "SELECT dish FROM Dish dish WHERE dish.price <= ?1",nativeQuery = true)
    List<Dish> searchDishesByPrice(Float price);

    @Query(value = "SELECT dish FROM Dish dish WHERE dish.restaurant <= ?1",nativeQuery = true)
    List<Dish> getHotelMenu(Long hotel_id);

}
