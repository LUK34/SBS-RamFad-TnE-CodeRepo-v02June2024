package kw.kng.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kw.kng.entities.Product;
import kw.kng.repo.ProductRepo;

@Service
public class ProductServiceImpl implements ProductService 
{
	private ProductRepo prepo;
	
	
	public ProductServiceImpl(ProductRepo prepo) 
	{
		this.prepo = prepo;
	}


	@Override
	public List<Product> searchProducts(String query) 
	{
		List<Product> pros= prepo.searchProducts(query);
		return pros;
	}


	@Override
	public Product createProduct(Product product) 
	{
		return prepo.save(product);
	}
	
}
