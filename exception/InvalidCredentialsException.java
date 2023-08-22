package org.jsp.springBootDemo.exception;

public class InvalidCredentialsException extends RuntimeException
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return "Invalid Phone Number or Email or Password";
	}
 
	
}
