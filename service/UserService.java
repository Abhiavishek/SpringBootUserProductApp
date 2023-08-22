package org.jsp.springBootDemo.service;

import java.util.List;
import java.util.Optional;

import org.jsp.springBootDemo.dto.ResponseStructure;
import org.jsp.springBootDemo.dto.User;
import org.jsp.springBootDemo.exception.IdNotFoundException;
import org.jsp.springBootDemo.exception.InvalidCredentialsException;
import org.jsp.springBootDemo.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

@Service
public class UserService {
	
	@Autowired
	private UserDao dao;
	public ResponseEntity<ResponseStructure<User>> saveUser(User u) {
		ResponseStructure<User> structure = new ResponseStructure<>();
		structure.setData(dao.saveUser(u));
		structure.setMessage("User saved with Id: "+u.getId());
		structure.setStatusCode(HttpStatus.CREATED.value());
		return new ResponseEntity<ResponseStructure<User>>(structure, HttpStatus.CREATED);
	}
	
	public ResponseEntity<ResponseStructure<User>> updateUser(User u){
		ResponseStructure<User> structure = new ResponseStructure<>();
		structure.setData(dao.updateUser(u));
		structure.setMessage("User Updated");
		structure.setStatusCode(HttpStatus.ACCEPTED.value());
		return new ResponseEntity<ResponseStructure<User>>(structure,HttpStatus.ACCEPTED);
	}
	
	public ResponseEntity<ResponseStructure<User>> findById(int id){
		ResponseStructure<User> structure = new ResponseStructure<>();
		Optional<User> recUser = dao.findById(id);
		if(recUser.isPresent()) {
			structure.setMessage("user Found");
			structure.setData(recUser.get());
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<User>>(structure,HttpStatus.OK);
		}
		else {
			throw new IdNotFoundException();
		}
	}

	public ResponseEntity<ResponseStructure<String>> deleteUser( int id){
		ResponseStructure<String> structure = new ResponseStructure<>();
		Optional<User> recUser = dao.findById(id);
		if(recUser.isPresent()) {
			structure.setMessage("user deleted");
			structure.setData("user found");
			structure.setStatusCode(HttpStatus.OK.value());
			dao.deleteUser(id);
			return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.OK);

		}
		else {
			structure.setMessage("User Not Deleted");
			structure.setData("User Not Found");
			structure.setStatusCode(HttpStatus.NOT_FOUND.value());
			return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NOT_FOUND);
		}
	}
	
	public ResponseEntity<ResponseStructure<List<User>>> findAll(){
		ResponseStructure<List<User>> structure = new ResponseStructure<>();
		structure.setData(dao.findAll());
		structure.setMessage("List of All Users");
		structure.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<List<User>>>(structure, HttpStatus.OK);
		
	}
	
	public ResponseEntity<ResponseStructure<User>> verifyUser(long phone, String password){
		ResponseStructure<User> structure = new ResponseStructure<>();
		Optional<User>recUser = dao.verifyUser(phone, password);
		if(recUser.isPresent()) {
			structure.setData(recUser.get());
			structure.setMessage("User Verified Successfully");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<User>>(structure,HttpStatus.OK);
		}
		else {
		    throw new InvalidCredentialsException();
		}
	}
	
	public ResponseEntity<ResponseStructure<User>> verifyUser(String email, String password){
		ResponseStructure<User> structure = new ResponseStructure<>();
		Optional<User>recUser = dao.verifyUser(email, password);
		if(recUser.isPresent()) {
			structure.setData(recUser.get());
			structure.setMessage("User Verified Successfully");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<User>>(structure,HttpStatus.OK);
		}
		else {
			throw new InvalidCredentialsException();
		}
	}
	

}
