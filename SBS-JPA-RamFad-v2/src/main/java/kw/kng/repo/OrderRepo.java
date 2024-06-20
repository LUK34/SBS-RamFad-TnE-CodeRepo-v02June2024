package kw.kng.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import kw.kng.entities.Order;

public interface OrderRepo extends JpaRepository<Order, Long> 
{
	Order findByOrderTrackingNumber(String orderTracking);

}
