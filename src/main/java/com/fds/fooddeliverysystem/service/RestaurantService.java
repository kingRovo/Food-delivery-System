package com.fds.fooddeliverysystem.service;

import com.fds.fooddeliverysystem.model.BusinessWallet;
import com.fds.fooddeliverysystem.model.Dish;
import com.fds.fooddeliverysystem.model.Restaurant;
import com.fds.fooddeliverysystem.repository.BusinessWalletRepository;
import com.fds.fooddeliverysystem.repository.DishRepository;
import com.fds.fooddeliverysystem.repository.RestaurantRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final BusinessWalletRepository businessWalletRepository;

    private final DishRepository dishRepository;

    /**
     * this method registering new Restaurant
     * @param restaurant
     * @return ResponseEntity
     */
    @Transactional
    public ResponseEntity<Restaurant> registerRestaurant(Restaurant restaurant){

        if (!ObjectUtils.isEmpty(restaurant)){
            restaurant.setBusinessWallet(new BusinessWallet(UUID.randomUUID(),0));
            return new ResponseEntity<>(restaurantRepository.save(restaurant), HttpStatus.CREATED);
        }
        else {

            log.error("******** Inserted data is not valid to register..**********");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    /**
     * this method will return all open restaurant
     * @return ResponseEntity
     */
    @Transactional
    public ResponseEntity<List<Restaurant>> openRestaurant(){

        try {
            return new ResponseEntity<>(restaurantRepository.allOpenRestaurant(java.time.LocalTime.now()),HttpStatus.OK);
        }
        catch (Exception exception){
            log.error("************ unable to fetch data..************");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    /**
     * this method return available balance in wallet.
     * @param id
     * @return ResponseEntity<Float>
     */
    @Transactional
    public ResponseEntity<Float> viewBalance(Long id){

        try{

            Restaurant restaurant = restaurantRepository.findById(id).orElseThrow();

           Float price =  restaurant.getBusinessWallet().getBalance();
           log.info("***** fetch balance from wallet ********");
           return new ResponseEntity<>(price,HttpStatus.OK);
        }
        catch (Exception exception){
            log.error("****** unable to fetch account balance ******");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * this method will add balance to user wallet
     * @param id
     * @param amount
     * @return ResponseEntity
     */
    @Transactional
    public ResponseEntity addBalance(Long id, Float amount){

        try{
            Restaurant restaurant = restaurantRepository.findById(id).orElseThrow();
            BusinessWallet businessWallet = restaurant.getBusinessWallet();
            businessWallet.setBalance(businessWallet.getBalance()+amount);
            businessWalletRepository.save(businessWallet);
            log.info("{} added to business wallet",amount);
            return new ResponseEntity<>(HttpStatus.OK);

        }
        catch (Exception exception){
            log.error("bad request unable to add balance.");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * will return menu of dishes of a restaurant.
     * @param id
     * @return esponseEntity<List<Dish>>
     */
    @Transactional
    public ResponseEntity<List<Dish>> getMenuData(long id){

        try{

            Restaurant restaurant = restaurantRepository.findById(id).orElseThrow();
            log.info("fetching data for hotel "+id);
            return new ResponseEntity<>(dishRepository.getHotelMenu(id),HttpStatus.OK);

        }
        catch (Exception exception){

            log.error("unable to fetch dish data.");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * adding new dish to restaurant menu.
     * @param dish
     * @return ResponseEntity
     */
    @Transactional
    public ResponseEntity<Dish> addNewDish(Long id,Dish dish){

        if (!ObjectUtils.isEmpty(dish)){

            Restaurant restaurant = restaurantRepository.findById(id).orElseThrow();
            dish.setRestaurant(restaurant);
            log.info("***** {} added to menu *****",dish.getName());
            return new ResponseEntity<>(dishRepository.save(dish),HttpStatus.CREATED);
        }
        else {
            log.error("***** Inserted data is not valid to add dish");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * this method filter and sort restaurant data.
     * @param start
     * @param pageSize
     * @param field
     * @return ResponseEntity<Page<Restaurant>>
     */
    public ResponseEntity<Page<Restaurant>> filterAndSort(int start, int pageSize, String field){


        Page<Restaurant> restaurants = restaurantRepository.findAll(PageRequest.of(start,pageSize).withSort(Sort.by(field)));
        log.info("filtering restaurant data ");
        return new ResponseEntity<>(restaurants,HttpStatus.OK);


    }


}
