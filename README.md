# Ramesh Fadatare Course Code Repo

## 1. Bank Application - RestAPI
- **Course:** Mastering Springboot with 5 projects
- **FileName:** SBS-BankApp

## 2. Expense Tracker - RestAPI
- **Course:** Mastering Springboot with 5 projects
- **FileName:** SBS-ExpenseTracker-RamFad

### Description:
- Swagger customization is applied here.

## 3. Student Management System - MVC + Thymeleaf
- **Course:** Mastering Springboot with 5 projects
- **FileName:** SBS-StudentMS-RamFad

### Description:
- Not an exact tutorial of Mr. Ramesh Fadatare.
- Soft deletion is applied.
- Thymeleaf core layout applied.
- Custom toast is applied.

## 4. Mastering JPA and Hibernate with Entity Relationship
- **Course:** Mastering JPA and Hibernate: Ecommerce Project
- **FileName:** SBS-JPA-RamFad-v2

## 5. JPA Simple Search Rest API
- **Course:** Mastering JPA and Hibernate: Ecommerce Project
- **FileName:** SBS-JPA-RamFad-SrchRstApi

## 6. JPA Transaction Management
- **Course:** Mastering JPA and Hibernate: Ecommerce Project
- **FileName:** SBS-JPA-RamFad-TrnsMgm

### Description:
This will teach you how to handle inconsistent data (Rollback) if there is data inconsistency.

## 7. JPA using JpaTest Unit Testing
- **Course:** Mastering JPA and Hibernate: Ecommerce Project
- **FileName:** SBS-JPA-RamFad-JpaTest
	
## 8. KAFKA Poc using String
- **Course:** Apache Kafka and Springboot - The Practical Guide
- **FileName:** KAFKA-RamFad-String

## 9. KAFKA Poc using JSON
- **Course:** Apache Kafka and Springboot - The Practical Guide
- **FileName:** KAFKA-RamFad-Json

## 10.KAFKA Wikimedia Multi Modal Project
- **Course:** Apache Kafka and Springboot - The Practical Guide
- **FileName:** KAFKA-RamFad-Wikimedia

### Description:
- KAFKA-RamFad-WikiMedia-Producer ( Fetches the data from wikimedia and store it in Topic->wikimedia_recentchange )
- KAFKA-RamFad-Consumer-DB ( Fetches the data from Topic->wikimedia_recentchange and store it in DB->Mysql)


## 11.KAFKA Microservice
- **Course:** Apache Kafka and SpringBoot - The Practical Guide
- **FileName:** KAFKA-Base-Domain,KAFKA-Email-Service,KAFKA-Order-Service,KAFKA-Stock-Service
- KAFKA-Order-Service (Producer Microservice, Port:8080)
- KAFKA-Stock-Service (Consumer Microservice, Port:8084)
- KAFKA-Email-Service (Consumer Microservice, Port:8085)

## 12.MCRSRV-RamFad-Rest-SimpleCRUDv1
- **Course:** Build Microservices using Spring Boot 3, Spring Cloud, React, Kafka, RabbitMQ, REST API, Docker & IntelliJ IDEA
- **FileName:** MCRSRV-RamFad-Rest-SimpleCRUDv1

### Description:
- Implemented CRUD Operation via data transfer using DTO and Entity (ModelMapper dependency)
- Implemented Exception Handling
- Implemented Validations
- Implemented SWAGGER (3.x.x -> http://localhost:8080/swagger-ui/index.html)

## 13.MCRSRV-RamFad-Rest-SimpleCRUDv2
- **Course:** Build Microservices using Spring Boot 3, Spring Cloud, React, Kafka, RabbitMQ, REST API, Docker & IntelliJ IDEA
- **FileName:** MCRSRV-RamFad-Rest-SimpleCRUDv2

### Description:
- Implemented CRUD Operation via data transfer using DTO and Entity (MapStruct dependency)
- Implemented Exception Handling
- Implemented Validations
- Implemented SWAGGER (3.x.x -> http://localhost:8080/swagger-ui/index.html)

- https://mapstruct.org/documentation/installation/
- https://github.com/mapstruct/mapstruct-examples/blob/main/mapstruct-lombok/pom.xml

- Note:
- ModelMapper is easier to use and faster to set up, but MapStruct offers better performance and compile-time safety,
- making it more suitable for complex and performance-sensitive applications.The choice between the two depends on your specific
- project requirements and priorities. For best practices, prefer MapStruct for larger, 
- production-level projects where performance and type safety are crucial, and use ModelMapper for simpler or prototype applications where ease of use is a priority.

## 14.MCRSRV-RamFad-Microservice-v1
- **Course:** Build Microservices using Spring Boot 3, Spring Cloud, React, Kafka, RabbitMQ, REST API, Docker & IntelliJ IDEA
- **FileName:** MCRSRV-RamFad-Microservice-v1

### Description:
- This is a Mulit Modal Project:
- MCRSRV-DepartmentService-v1 (3.x.x -> http://localhost:8080/swagger-ui/index.html, Eureka Client, Port:8080,8082),
- MCRSRV-EmployeeService-v1   (3.x.x -> http://localhost:8081/swagger-ui/index.html, Eureka Client, Port:8081)
- MCRSRV-ServiceRegistry-v1	  (Eureka Server, Port:8761)
- MCRSRV-ApiGateway-v1		  (Eureka Client, Port:9191) 
- MCRSRV-ConfigServer-v1 	   (Prgm to call Config files present at Github, Port:8888)
- Implemented Exception Handling
- Implemented Validations
- Implemented SWAGGER 
- Implemented ModelMapper 
- Implemented following Microservice Communications (EmployeeService communicating with DepartmentService): 
  - RestTemplate (Code Commit Hash Code: 860e083f9212f8d8a0f0de5fe35df38a36305d91) -> will soon be deprecated(5.0), later versions this will be deprecated.
  - WebClient (Code Commit Hash Code: f770a58dbff9c99a05b00de5830e3827eb8d7ecb)
  - FeignClient (Code Commit Hash Code: b007e310451fc462fe504f7f0b2da680a2bedcf4)  ->**Currently used for microservice communication** 
- Implemented API Gateway Routes(Code Commit Hash Code: 65b2a471e8f22380b53e0c506ee97f274f3984cd) manual (**Best**) and automatic.
  
## 15.SBS-RamFad-BLOG-3.x.x
- **Course:** Build REST APIs using Spring Boot, Spring Security 6, JWT, Spring Data JPA, Hibernate, MySQL, Docker &amp; Deploy on AWS
- **FileName:** SBS-RamFad-BLOG-3.x.x

### Description:
- Implemented Exception Handling
- Implemented Validations
- Implemented SWAGGER  (3.x.x -> http://localhost:8080/swagger-ui/index.html)
- Implemented Pagination
- Implemented ModelMapper 
- Implemented SecurityFilterChain for 3.x.x
- Implemented JWT Token (HashCode: 9956224061c0fa7b2275221cf3829ebf4d8674fa)
- Versioning through URI Path(HashCode: 3a9fca681cc3432a2e021854769e93d4dc5573cc)

## 16.SBS-RamFad-BLOG-2.x.x
- **Course:** Build REST APIs using Spring Boot, Spring Security 6, JWT, Spring Data JPA, Hibernate, MySQL, Docker &amp; Deploy on AWS
- **FileName:** SBS-RamFad-BLOG-2.x.x	

### Description:
- This is downscaled version of the previous, (3.3.1) to (2.7.6).

## 17.sbs-docker-demo
- **Course:** Build REST APIs using Spring Boot, Spring Security 6, JWT, Spring Data JPA, Hibernate, MySQL, Docker &amp; Deploy on AWS
- **FileName:** sbs-docker-demo

### Description:
- This is sample programme that is deployed in dockerhub. Refer the note `DockerFie notes` which is KT for beginners.	


## 18.rest-crud-docker
- **Course:** Build REST APIs using Spring Boot, Spring Security 6, JWT, Spring Data JPA, Hibernate, MySQL, Docker &amp; Deploy on AWS
- **FileName: ** rest-crud-docker


### Description:
- As per what you did 3306 will not work in your local docker hub. You followed chatGPT4o advice and some how generated the jar file
- After generating the jar. You followed Section 28: docker-compose.yml file . You did not follow the exact tutorial because it will
- Refer `docker-compose.yml` , `Dockerfile` , `application-docker_prody.properties`, `DockerFile notes`. You need to practice Docker commands.








