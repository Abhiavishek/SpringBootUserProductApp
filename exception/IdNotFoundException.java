package org.jsp.springBootDemo.exception;

public class IdNotFoundException extends RuntimeException
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return "The id is invalid";
	}
  
}
