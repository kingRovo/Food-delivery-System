package com.fds.fooddeliverysystem.controller;

import com.fds.fooddeliverysystem.model.Dish;
import com.fds.fooddeliverysystem.model.Restaurant;
import com.fds.fooddeliverysystem.service.RestaurantService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@Slf4j
@AllArgsConstructor
@RequestMapping("api/v1/restaurant")
public class RestaurantController {

    private final RestaurantService restaurantService;


    /**
     * this method will return all open restaurant
     *
     * @return ResponseEntity<List < Restaurant>>
     */
    @GetMapping("/open")
    ResponseEntity<List<Restaurant>> openRestaurant() {

        log.info(" ===== all open restaurant ======");
        return restaurantService.openRestaurant();
    }

    /**
     * this method registering new Restaurant
     *
     * @param restaurant
     * @return ResponseEntity
     */
    @PostMapping("/")
    ResponseEntity<Restaurant> registerRestaurant(Restaurant restaurant) {

        if (!ObjectUtils.isEmpty(restaurant)) {
            log.info("registering restaurant");
            return restaurantService.registerRestaurant(restaurant);
        } else {
            log.debug("provided restaurant data is not valid to register");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * will return menu of dishes of a restaurant.
     *
     * @param id
     * @return esponseEntity<List < Dish>>
     */
    @GetMapping("/{id}/items-menu")
    public ResponseEntity<List<Dish>> getMenuData(@PathVariable("id") long id) {

        log.info("getting menu data ");
        return restaurantService.getMenuData(id);
    }

    /**
     * adding new dish to restaurant menu.
     *
     * @param dish
     * @return ResponseEntity
     */
    @PostMapping("/{id}/dish")
    public ResponseEntity<Dish> addNewDish(@PathVariable("id") Long id, @RequestBody Dish dish) {

        if (!ObjectUtils.isEmpty(dish)) {
            log.info("adding new dish to hotel menu");
            return restaurantService.addNewDish(id, dish);
        } else {
            log.debug("dish data is not valid to add in menu");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * this method return available balance in wallet.
     *
     * @param id
     * @return ResponseEntity<Float>
     */
    @GetMapping("/{id}/wallet/balance")
    public ResponseEntity<Float> viewBalance(@PathVariable("id") Long id) {

        return restaurantService.viewBalance(id);

    }

    /**
     * this method filter and sort restaurant data.
     *
     * @param start
     * @param pageSize
     * @param field
     * @return ResponseEntity<Page < Restaurant>>
     */
    @GetMapping("/")
    public ResponseEntity<Page<Restaurant>> filterAndSort(int start, int pageSize, String field) {

        if (start < 0 && pageSize < 1) {
            log.error(" invalid arguments ");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            log.info("retuning sorted dishes data");
            return restaurantService.filterAndSort(start, pageSize, field);
        }

    }

}
