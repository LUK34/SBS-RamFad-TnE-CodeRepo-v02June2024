package kw.kng;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;


@SpringBootApplication
@OpenAPIDefinition(
		info=@Info(
					title="BLOG REST API Documentation",
					description="BLOG REST API Documentation",
					version="v03June2024",
					contact=@Contact(
										name="Luke Rajan Mathew",
										email="",
										url=""
					),
					license=@License(
										name="",
										url=""
					)
				),
	   externalDocs= @ExternalDocumentation(
											description="BLOG REST API Documentation for Developers",
											url="https://www.javaguides.net/external-doc.html"
										)
)
public class Application 
{

	@Bean
	public ModelMapper modelMapper()
	{
		return new ModelMapper();
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}

/*
 
 																			#Swagger
								
# Here we have implemented spring security via security filter chain. so inorder to access swagger. u must specify the url in 
# SecurityConfig class.
				
#swagger docs -for 2.x.x
#URL: localhost:8080/swagger-ui.html


#swagger docs-for 3.x.x (Currently used) 
#URL:localhost:8080/swagger-ui/index.html (Use me)

http://localhost:8080/v3/api-docs (Use me)

 */


/*
1. What is ModelMapper???

Ans:

ModelMapper is a Java library that provides an easy and efficient way to map objects from one type to another.
It is often used in applications where there is a need to convert between different types of objects, such as mapping between data transfer objects (DTOs) 
and entity objects in a Spring application.

Key Features of ModelMapper:
Object Mapping: Automatically maps fields between source and destination objects based on their names and types.
Type Conversion: Handles conversion between different data types.
Flexible Configuration: Allows customization of mapping logic through configuration.
Nested Mapping: Supports mapping of nested properties and complex object structures.


Reduced Boilerplate: Simplifies the code required for object mapping.
Consistency: Ensures consistent mapping logic throughout the application.
Maintainability: Makes it easier to maintain mapping logic as the application evolves.

2. How to Implement ModelMapper into your project???

Ans:
2.1. First inject the depenedency in pom.xml file

			<!--  Model Mapper Dependency START -->
		<dependency>
			<groupId>org.modelmapper</groupId>
			<artifactId>modelmapper</artifactId>
			<version>3.2.0</version>
		</dependency>
		<!-- END -->

2.2 Annotate `@Bean` and inject this as an object in File that you use to run.

@Bean
	public ModelMapper modelMapper()
	{
		return new ModelMapper();
	}

2.3 Inject into the required class using CONSTRUCTOR INJECTION. Make sure that the Binding class for the corresponding class is injected.



*/