package com.shirish.orderservice.orderservicr.repository;

import com.shirish.orderservice.orderservicr.model.OrderLineIteam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderLineIteamRepo extends JpaRepository<OrderLineIteam,Long> {
}
