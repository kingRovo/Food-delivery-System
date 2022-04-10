package com.fds.fooddeliverysystem.service;

import com.fds.fooddeliverysystem.model.*;
import com.fds.fooddeliverysystem.model.dto.OrderStatus;
import com.fds.fooddeliverysystem.repository.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Service
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final WalletRepository walletRepository;
    private final TransactionRepository transactionRepository;
    private final DishRepository dishRepository;
    private final BusinessWalletRepository businessWalletRepository;
    private final OrderRepository orderRepository;


    /**
     * registering new user to system
     * @param user
     * @return ResponseEntity<User>
     */
    @Transactional
    public ResponseEntity<User> register(User user){

        if (ObjectUtils.isEmpty(user)){
            log.info("invalid data unable to register ");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        else {
            user.setWallet(new Wallet(UUID.randomUUID(),0));
            userRepository.save(user);
            log.info("user register Successfully");
            return new ResponseEntity<>(user,HttpStatus.CREATED);
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

            User user = userRepository.findById(id).orElseThrow();

            Float price =  user.getWallet().getBalance();
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
            User user = userRepository.findById(id).orElseThrow();
            Wallet Wallet = user.getWallet();
            Wallet.setBalance(Wallet.getBalance()+amount);
            walletRepository.save(Wallet);
            log.info("{} added to  wallet",amount);
            return new ResponseEntity<>(HttpStatus.OK);

        }
        catch (Exception exception){
            log.error("bad request unable to add balance.");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * this method return list of transaction
     * @param id
     * @return ResponseEntity<List>
     */
    @Transactional
    public ResponseEntity<List<Transaction>> getTransactionHistory(Long id){
        try{

            if (userRepository.findById(id).get()!=null){
                 return new ResponseEntity<>(transactionRepository.findAllTransaction(id),HttpStatus.OK);
            }
            else {
                log.info("no user found at {}",id);
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            }

        }
        catch (Exception exception){
            log.error("unable to fetch transaction record");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * this method make order for user.
     * @param userId
     * @param dishID
     * @return
     */
    @Transactional
    public ResponseEntity<OrderStatus> orderDish(Long userId, Long dishID){



        try {

            Date date = new Date(System.currentTimeMillis());
            Dish dish =dishRepository.findById(dishID).orElseThrow();
            User user =userRepository.findById(userId).orElseThrow();
            Transaction transaction = new Transaction(UUID.randomUUID(),user,dish.getPrice(),date,dish.getName());
            transactionRepository.save(transaction);
            doPayment(dish.getPrice(),user,dish.getRestaurant());

            Order order =  new Order(UUID.randomUUID(),date,"Confirm",dish,user);
            orderRepository.save(order);
            OrderStatus orderStatus = new OrderStatus(order.getId(),user.getName(),dish.getName(),dish.getPrice(),order.getStatus());
            log.info("***** order confirm ******");
            return new ResponseEntity<>(orderStatus,HttpStatus.OK);
        }
        catch (Exception exception){

            log.error("**** bad request, don't make order ****");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Transactional
    public void doPayment(float price, User user, Restaurant restaurant) {

       Wallet wallet =  user.getWallet();
       BusinessWallet businessWallet =  restaurant.getBusinessWallet();
       if(wallet.getBalance()>price) {
           wallet.setBalance(wallet.getBalance() - price);
           businessWallet.setBalance(businessWallet.getBalance()+ price);
           walletRepository.save(wallet);
           businessWalletRepository.save(businessWallet);
           log.info("payment process is completed");
       }
       else {
           log.info("low balance in wallet");
       }
    }

}
