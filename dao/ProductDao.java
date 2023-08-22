package org.jsp.springBootDemo.dao;

import java.util.List;
import java.util.Optional;

import org.jsp.springBootDemo.dto.Product;
import org.jsp.springBootDemo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDao {
	@Autowired
	private ProductRepository repository;
	
	public Product saveProduct(Product product) {
		return repository.save(product);
	}
	
	public Product updateProduct(Product product) {
		return repository.save(product);
	}
	
	public Optional<Product> findById(int id){
		return repository.findById(id);
	}
	
	public boolean deleteProduct(int id) {
		Optional<Product>recProd = findById(id);
		if(recProd.isPresent()) {
			repository.delete(recProd.get());
			return true;
		}
		else {
			return false;
		}
	}
	
	public List<Product> findProductByUserId(int id){
		return repository.findProductByUserId(id);
	}

}
