package org.jsp.springBootDemo.service;

import java.util.List;
import java.util.Optional;

import org.jsp.springBootDemo.dao.ProductDao;
import org.jsp.springBootDemo.dao.UserDao;
import org.jsp.springBootDemo.dto.Product;
import org.jsp.springBootDemo.dto.ResponseStructure;
import org.jsp.springBootDemo.dto.User;
import org.jsp.springBootDemo.exception.IdNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @Autowired
	private UserDao udao;
    @Autowired
    private ProductDao pdao;
    
    public ResponseEntity<ResponseStructure<Product>> saveProduct(Product product, int id){
    	Optional<User> recUser = udao.findById(id);
    	ResponseStructure<Product> structure = new ResponseStructure<>();
    	if(recUser.isPresent()) {
    		User u = recUser.get();
    		u.getProducts().add(product);
    	    product.setUser(recUser.get());
    	    udao.updateUser(u);
    	    pdao.saveProduct(product);
    	    structure.setData(product);
    	    structure.setMessage("Product added with Id "+ product.getId());
    	    structure.setStatusCode(HttpStatus.CREATED.value());
    	    return new ResponseEntity<ResponseStructure<Product>>(structure,HttpStatus.CREATED);
    	}
    	throw new IdNotFoundException();
    }
    
    public ResponseEntity<ResponseStructure<Product>> updateProduct(Product product, int id){
    	Optional<User> recUser = udao.findById(id);
    	ResponseStructure<Product> structure = new ResponseStructure<>();
    	if(recUser.isPresent()) {
    	    product.setUser(recUser.get());
    	    pdao.saveProduct(product);
    	    structure.setData(product);
    	    structure.setMessage("Product updated with Id "+ product.getId());
    	    structure.setStatusCode(HttpStatus.ACCEPTED.value());
    	    return new ResponseEntity<ResponseStructure<Product>>(structure,HttpStatus.ACCEPTED);
    	}
    	throw new IdNotFoundException();
    }
    
    public ResponseEntity<ResponseStructure<Product>> findById(int id){
    	ResponseStructure<Product> structure = new ResponseStructure<>();
    	Optional<Product> recProduct = pdao.findById(id);
    	if(recProduct.isPresent()) {
    		structure.setData(recProduct.get());
    		structure.setMessage("Product Found");
    		structure.setStatusCode(HttpStatus.OK.value());
    	    return new ResponseEntity<ResponseStructure<Product>>(structure,HttpStatus.OK);
    	}
    	throw new IdNotFoundException();
    }
    
    public ResponseEntity<ResponseStructure<String>>deleteProduct(int id){
    	ResponseStructure<String> structure = new ResponseStructure<>();
    	Optional<Product> recProduct = pdao.findById(id);
    	if(recProduct.isPresent()) {
    		pdao.deleteProduct(id);
    		structure.setData("product deleted");
    		structure.setMessage("Product Found");
    		structure.setStatusCode(HttpStatus.OK.value());
    	    return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.OK);
    	}
    	throw new IdNotFoundException();
    }
    
    public ResponseEntity<ResponseStructure<List<Product>>> findProductByUserId(int id){
    	ResponseStructure<List<Product>> structure = new ResponseStructure<>();
    	structure.setData(pdao.findProductByUserId(id));
    	structure.setMessage("Products found for userId");
    	structure.setStatusCode(HttpStatus.OK.value());
    	return new ResponseEntity<ResponseStructure<List<Product>>>(structure, HttpStatus.OK);
    }
}
