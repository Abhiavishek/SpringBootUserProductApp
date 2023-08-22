package org.jsp.springBootDemo.controller;

import java.util.List;

import org.jsp.springBootDemo.dto.Product;
import org.jsp.springBootDemo.dto.ResponseStructure;
import org.jsp.springBootDemo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class ProductController {
    @Autowired
	private ProductService productService;
    @PostMapping("/products/{user_id}")
    public ResponseEntity<ResponseStructure<Product>> saveProduct(@RequestBody Product product, @PathVariable int user_id) {
    	return productService.saveProduct(product, user_id);
    }
    @PutMapping("/products/{user_id}")
    public ResponseEntity<ResponseStructure<Product>> updateProduct(@RequestBody Product product, @PathVariable int user_id) {
    	return productService.updateProduct(product, user_id);
    }
    @GetMapping("/products/{id}")
    public ResponseEntity<ResponseStructure<Product>> findById(@PathVariable int id) {
    	return productService.findById(id);
    }
    @DeleteMapping("/products/{id}")
    public ResponseEntity<ResponseStructure<String>> deleteProduct(@PathVariable int id) {
    	return productService.deleteProduct(id);
    }
     @GetMapping("/products/byuserid/{id}")
    public ResponseEntity<ResponseStructure<List<Product>>> findProductByUserId(@PathVariable int id){
    	return productService.findProductByUserId(id);
    }
    
    
}
