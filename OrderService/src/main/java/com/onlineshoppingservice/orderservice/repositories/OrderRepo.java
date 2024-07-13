package com.onlineshoppingservice.orderservice.repositories;

import com.onlineshoppingservice.orderservice.models.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<OrderDetails, Long>{
}
