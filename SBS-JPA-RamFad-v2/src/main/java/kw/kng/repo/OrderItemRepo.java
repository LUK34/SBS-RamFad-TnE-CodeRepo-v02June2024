package kw.kng.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import kw.kng.entities.OrderItem;

public interface OrderItemRepo extends JpaRepository<OrderItem, Long> 
{

}
