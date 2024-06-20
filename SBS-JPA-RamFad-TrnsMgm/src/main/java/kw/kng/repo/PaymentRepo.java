package kw.kng.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import kw.kng.entities.Payment;

public interface PaymentRepo extends JpaRepository<Payment, Long> 
{

}
