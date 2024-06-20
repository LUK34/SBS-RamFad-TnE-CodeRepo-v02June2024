package kw.kng.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kw.kng.dto.OrderRequest;
import kw.kng.dto.OrderResponse;
import kw.kng.entities.Order;
import kw.kng.entities.Payment;
import kw.kng.exceptions.PaymentException;
import kw.kng.repo.OrderRepo;
import kw.kng.repo.PaymentRepo;

@Service
public class OrderServiceImpl implements OrderService
{

	private OrderRepo orepo;
	private PaymentRepo prepo;
	
	public OrderServiceImpl(OrderRepo orepo, PaymentRepo prepo) 
	{
		this.orepo = orepo;
		this.prepo=prepo;
	}


	@Override
	@Transactional(rollbackFor=PaymentException.class) //The annotation will avoid database inconsitancy by not inserting "CREDIT" payment type
	public OrderResponse placeOrder(OrderRequest orderRequest) 
	{
		Order order= orderRequest.getOrder();
		order.setStatus("INPROGRESS");
		order.setOrderTrackingNumber(UUID.randomUUID().toString());
		orepo.save(order);
		
		Payment payment= orderRequest.getPayment();
		
		if(!payment.getType().equals("DEBIT"))
		{
			throw new PaymentException("Payment card type do not support");
		}
		payment.setOrderId(order.getId());
		prepo.save(payment);
		
		OrderResponse orderResponse = new OrderResponse();
		orderResponse.setOrderTrackingNumber(order.getOrderTrackingNumber());
		orderResponse.setStatus(order.getStatus());
		orderResponse.setMessage("SUCCESS");
		
		return orderResponse;
	}

}

/*
 The @Transactional annotation in Spring Boot is used to manage transactions declaratively.
  It ensures that the methods annotated with it are executed within a transaction context. 
  If any part of the method fails, the transaction can be rolled back to maintain data integrity.

--------------------------------------------------
Key Features of @Transactional:
--------------------------------------------------
Atomicity:
------------------
 Ensures that a series of operations within a transaction are treated as a single unit of work, either all operations complete successfully, or none do.

Consistency:
------------------
 Ensures the database remains in a consistent state before and after the transaction.

Isolation: 
------------------
Controls the visibility of transaction operations between different transactions.

Durability:
------------------
 Ensures that once a transaction has been committed, it remains so, even in the event of a system failure.

Properties of @Transactional
--------------------------------------------------
propagation:
------------------
Defines how the transaction should behave when it encounters an existing transaction.
Types: REQUIRED (default), SUPPORTS, MANDATORY, REQUIRES_NEW, NOT_SUPPORTED, NEVER, NESTED.

isolation:
------------------
Defines the isolation level for the transaction.
Types: DEFAULT (default), READ_UNCOMMITTED, READ_COMMITTED, REPEATABLE_READ, SERIALIZABLE.

timeout:
------------------
Specifies the time in seconds that a transaction is allowed to run before it is automatically rolled back.
Default is -1 (no timeout).

readOnly:
------------------
Indicates whether the transaction is read-only, which can allow the underlying database to optimize the transaction.
Default is false.

rollbackFor:
------------------
Specifies which exceptions should trigger a rollback.
Default is no rollback for checked exceptions.

noRollbackFor:
------------------
Specifies which exceptions should not trigger a rollback.
Default is rollback for unchecked exceptions.

rollbackForClassName:
------------------------------------
Specifies exception class names (as strings) that should trigger a rollback.

noRollbackForClassName:
-------------------------------------
Specifies exception class names (as strings) that should not trigger a rollback.

 Transactional Management for the code written:
 ----------------------------------------------------------------------------------------
Transaction Start: When placeOrder is called, Spring starts a new transaction.
Order Processing:
The order is created with a status of "INPROGRESS" and a unique tracking number.
The order is saved to the database using orepo.save(order).
Payment Processing:
The payment information is checked to ensure it is of type "DEBIT".
If the payment type is not "DEBIT", a PaymentException is thrown.
If the payment is valid, it is associated with the order and saved using prepo.save(payment).
Order Response:
An OrderResponse object is created, populated with order details, and returned.
How @Transactional Avoids Database Inconsistency
Atomicity:
If any part of the transaction fails, such as an invalid payment type leading to a PaymentException, the entire transaction is rolled back.
This means that the order will not be saved in the "INPROGRESS" state without a corresponding valid payment.

Consistency:
The database will always remain in a consistent state because incomplete transactions are rolled back.
For instance, if the payment is not of type "DEBIT", neither the order nor the payment records will be persisted in the database.

Isolation:
The @Transactional annotation ensures that the operations within the method are isolated from other concurrent transactions.
This prevents other transactions from seeing intermediate states, like an order being in the "INPROGRESS" state without a valid payment.

Rollback:
The rollbackFor=PaymentException.class property ensures that if a PaymentException is thrown, the transaction is rolled back.
This prevents partial data changes from being committed to the database.
Example Scenario

Valid Payment:
An order is placed and saved with the status "INPROGRESS".
The payment is valid and saved with the order ID.
The transaction commits, and the order is successfully processed with both order and payment records saved.

Invalid Payment:
An order is placed and saved with the status "INPROGRESS".
The payment type is not "DEBIT", leading to a PaymentException.
The transaction is rolled back, so the order and payment records are not saved.
The database remains consistent, with no partial or invalid data.
By wrapping the placeOrder method in a transaction, Spring ensures that either all operations within the method succeed, or none do, thereby maintaining data integrity and consistency in the database.
 
 
 
 
 
 */















