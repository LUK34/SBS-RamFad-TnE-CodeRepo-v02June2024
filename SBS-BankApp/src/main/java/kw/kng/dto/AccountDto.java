package kw.kng.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AccountDto 
{
	private Long id;
	private String accountHolderName;
	private double balance;

}

/*
 Using Record Class as DTO -> This is applicable only for Java 16 and above. Right now dont approach with this scenario.(Video 37)
 ---------------------------------------
 
 1. Java record is a special kind of class that helps you encapsulate related data without the need for boilerplate code.
 
 2. Using a record class as a DTO(Data Transfer Object) in a Spring Boot application is a modern and efficent approach to 
 encapsulating data transfer between the application layers.
 
 3.Records are a good fit for DTOs because they are concise, immutable, and automatically provide implementation of
 getter(), constructors, equals(), hashCode() and toString() methods, which are essential for DTOs. ( reducing boilerplate code)
 
 */


