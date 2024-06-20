package kw.kng.service;

import java.util.List;

import kw.kng.entities.Product;

public interface ProductService 
{
	List<Product> searchProducts(String query);

	Product createProduct(Product product);

}
