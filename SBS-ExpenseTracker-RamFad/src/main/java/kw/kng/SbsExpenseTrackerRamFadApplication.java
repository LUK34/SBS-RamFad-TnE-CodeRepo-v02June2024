package kw.kng;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@OpenAPIDefinition(
					info=@Info(
								title="Expense Tracker REST API Documentation",
								description="Expense Tracker REST API Dcoumentation",
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
														description="Expense Tracker REST API Documentation for Developers",
														url="https://www.javaguides.net/external-doc.html"
													)
)
@SpringBootApplication
public class SbsExpenseTrackerRamFadApplication {

	public static void main(String[] args) {
		SpringApplication.run(SbsExpenseTrackerRamFadApplication.class, args);
	}

}

//http://localhost:8080/swagger-ui/index.html
