package kw.kng.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kw.kng.entities.Product;
import kw.kng.service.ProductService;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController 
{
	private ProductService ps;

	public ProductController(ProductService ps) 
	{
		this.ps = ps;
	}
	
	@GetMapping("/search")
	public ResponseEntity<List<Product>> searchProduct(@RequestParam("query") String query)
	{
		return ResponseEntity.ok(ps.searchProducts(query));
	}
	
	@PostMapping
	public Product createProduct(@RequestBody Product product)
	{
		return ps.createProduct(product);
	}
	
	
}

/*
 	Type:POST
 	Url: http://localhost:8080/api/v1/products

 	{
 		"sku":"ABC",
 		"name": "laptop",
 		"description": "laptop description",
 		"active": true,
 		"imageUrl": "laptop.png"
 	}
 	
 	
 	Type:GET
 	Url: http://localhost:8080/api/v1/products/search?query=laptop
 	
 	
 */






