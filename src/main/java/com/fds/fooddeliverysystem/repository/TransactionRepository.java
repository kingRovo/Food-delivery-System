package com.fds.fooddeliverysystem.repository;

import com.fds.fooddeliverysystem.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Long> {

    @Query(value = "SELECT t from Transaction  t where t.user = ?1",nativeQuery = true)
    List<Transaction> findAllTransaction(Long user_id);
}
