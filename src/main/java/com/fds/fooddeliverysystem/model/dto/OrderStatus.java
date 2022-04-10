package com.fds.fooddeliverysystem.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderStatus {

    private UUID orderId;
    private String userName;
    private String dishName;
    private float price;
    private String OrderStatus;

}
