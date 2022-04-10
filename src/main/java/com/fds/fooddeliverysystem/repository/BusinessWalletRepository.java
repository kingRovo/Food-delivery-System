package com.fds.fooddeliverysystem.repository;

import com.fds.fooddeliverysystem.model.BusinessWallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BusinessWalletRepository extends JpaRepository<BusinessWallet, UUID> {
}
