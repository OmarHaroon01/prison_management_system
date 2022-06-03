package exceptions;


/*
 * Name: Omar Mohammed Haroon
 * ID: 2012007
 * Course: CSE215L.16
 * Instructor Name: Shaikh Shawon Arefin Shimon
 * Date: January 19, 2021
 */

public class InvalidDateException extends Exception{

  private static final long serialVersionUID = -5307349316103676672L;

  public InvalidDateException() {
    super();
  }
  
  public InvalidDateException(String message) {
    super(message);
  }
}
