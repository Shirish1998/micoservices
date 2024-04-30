package com.shirish.orderservice.orderservicr.repository;

import com.shirish.orderservice.orderservicr.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepo extends JpaRepository<Order,Long> {
}
