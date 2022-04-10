package com.fds.fooddeliverysystem.service;

import com.fds.fooddeliverysystem.model.Order;
import com.fds.fooddeliverysystem.model.dto.OrderStatus;
import com.fds.fooddeliverysystem.repository.OrderRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@AllArgsConstructor
@Service
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;

    /**
     * this method update order status
     * @param orderId
     * @param status
     * @return
     */
    public ResponseEntity<Order> updateStatus(UUID orderId,String status){

        try{
            Order order = orderRepository.findById(orderId).orElseThrow();
            order.setStatus(status);
            orderRepository.save(order);
            log.info("===== Order status updated =======");
            return new ResponseEntity<>(order,HttpStatus.OK);
        }
        catch (Exception exception){
            log.error("==== unable to update order status =======");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
