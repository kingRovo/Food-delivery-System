package com.fds.fooddeliverysystem.repository;

import com.fds.fooddeliverysystem.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {
}
