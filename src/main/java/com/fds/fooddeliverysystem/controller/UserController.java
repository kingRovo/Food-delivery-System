package com.fds.fooddeliverysystem.controller;

import com.fds.fooddeliverysystem.model.Transaction;
import com.fds.fooddeliverysystem.model.User;
import com.fds.fooddeliverysystem.model.dto.OrderStatus;
import com.fds.fooddeliverysystem.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("api/v1/user")
public class UserController {

    private final UserService userService;

    /**
     * registering new user to system
     *
     * @param user
     * @return ResponseEntity<User>
     */
    @PostMapping("/")
    public ResponseEntity<User> register(@RequestBody User user) {

        if (!ObjectUtils.isEmpty(user)) {
            log.info("user register to system ");
            return userService.register(user);
        } else {

            log.info("user body is not valid to register in system");
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

        if (id != null) {
            log.info("will return account balance of user");
            return userService.viewBalance(id);
        } else {

            log.info("user id is not valid to fetch balance");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    /**
     * this method will add balance to user wallet
     *
     * @param id
     * @param amount
     * @return ResponseEntity
     */
    @PatchMapping("/{id}/wallet")
    public ResponseEntity addBalance(@PathVariable("id") Long id, @RequestHeader("amount") Float amount) {

        if (amount <= 0) {
            log.info("adding balance to user wallet");
            return userService.addBalance(id, amount);
        } else {
            log.debug("enter amount is not valid to enter in wallet");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    /**
     * this method return list of user transaction
     *
     * @param id
     * @return ResponseEntity<List>
     */
    @GetMapping("/{id}/transactions")
    public ResponseEntity<List<Transaction>> getTransactionHistory(@PathVariable("id") Long id) {

        log.info("returning user transaction history");
        return userService.getTransactionHistory(id);

    }

    /**
     * this method make order for user.
     *
     * @param userId
     * @param dishID
     * @return ResponseEntity<OrderStatus>
     */
    @PostMapping("/{userid}/order/{dishid}")
    public ResponseEntity<OrderStatus> orderDish(@PathVariable("userid") Long userId, @PathVariable("dishid") Long dishID) {

        log.info(" confirming order ");
        return userService.orderDish(userId,dishID);
    }

}