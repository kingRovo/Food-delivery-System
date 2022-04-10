package com.fds.fooddeliverysystem.service;

import com.fds.fooddeliverysystem.model.Dish;
import com.fds.fooddeliverysystem.repository.DishRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class DishService {

    private final DishRepository dishRepository;



    /**
     * search dishes by given keyword
     * @param keyword
     * @return ResponseEntity<List>
     */
    public ResponseEntity<List<Dish>> searchedDishes(String keyword) {

        if (keyword!=null){
            log.info("**** Searching result for {}  *****",keyword);
            return new ResponseEntity<>(dishRepository.searchDishes(keyword),HttpStatus.OK);
        }
        else {

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    /**
     * this method return all dishes equal or less than by given price
     * @param price
     * @return
     */
    public ResponseEntity<List<Dish>> fetchByPrice(Float price) {

        if (price > 0){
            log.info("**** Searching result for {}  *****",price);
            return new ResponseEntity<>(dishRepository.searchDishesByPrice(price),HttpStatus.OK);
        }
        else {

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }


    /**
     * this method filter and sort dishes data.
     * @param start
     * @param pageSize
     * @param field
     * @return
     */
    public ResponseEntity<Page<Dish>> filterAndSort(int start, int pageSize, String field){


        Page<Dish> dishes = dishRepository.findAll(PageRequest.of(start,pageSize).withSort(Sort.by(field)));
        log.info("filtering dished data ");
        return new ResponseEntity<>(dishes,HttpStatus.OK);


    }



}
