package com.fds.fooddeliverysystem.controller;

import com.fds.fooddeliverysystem.model.Dish;
import com.fds.fooddeliverysystem.service.DishService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@Slf4j
@RequestMapping("api/v1/dish")
public class DishController {

    private final DishService dishService;


    /**
     * search dishes by given keyword
     * @param keyword
     * @return ResponseEntity<List>
     */
    @GetMapping("/search")
    ResponseEntity<List<Dish>> search(@RequestParam("keyword") String keyword){
        if (keyword!=null){
            log.info("search result by {}",keyword);
            return dishService.searchedDishes(keyword);
        }
        else {
            log.info("keyword is empty unable to fetch search result");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }


    /**
     * this method return all dishes equal or less than by given price
     * @param price
     * @return
     */
    @GetMapping("/price/{price}")
    public ResponseEntity<List<Dish>> findByPrice(@PathVariable("price") Float price){
        if (price>0){
            log.info("search dished by price :"+price);
            return dishService.fetchByPrice(price);
        }
        else {
            log.info("given price value is not valid to fetch result ");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    /**
     * this method filter and sort dishes data.
     * @param start
     * @param pageSize
     * @param field
     * @return
     */

    @GetMapping("/")
    public ResponseEntity<Page<Dish>> filterAndSort(@RequestParam("start")int start,@RequestParam("page_size") int pageSize,@RequestParam("field") String field) {

        if (start<0 && pageSize< 1){
            log.error(" invalid arguments ");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else {
            log.info("retuning sorted dishes data");
            return dishService.filterAndSort(start,pageSize,field);
        }

    }

}
