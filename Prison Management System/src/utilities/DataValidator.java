package utilities;

import java.time.LocalDate;

import exceptions.*;

/*
 * Name: Omar Mohammed Haroon
 * ID: 2012007
 * Course: CSE215L.16
 * Instructor Name: Shaikh Shawon Arefin Shimon
 * Date: January 19, 2021
 */

public class DataValidator {

  public static void isValid(String firstName, String lastName, String fatherName, String motherName, String gender, 
      LocalDate DOB, String address, String crime, String sentencedYear, LocalDate dateOfAdmit, String type,
      String pathToProfilePhoto) throws Exception {
    if (isNull(firstName) == true || firstName.length() < 3 || firstName.length() > 15) {
      throw new InvalidNameException("First name is not correct. Name must be of atleast 3 characters and not greater than 15 characters.");
    } else if (isNull(lastName) == true || lastName.length() < 3 || lastName.length() > 15) {
      throw new InvalidNameException("Last name is not correct. Name must be of atleast 3 characters and not greater than 15 characters.");
    } else if (isNull(fatherName) == true || fatherName.length() < 3 || fatherName.length() > 30) {
      throw new InvalidNameException("Father's name is not correct. Name must be of atleast 3 characters and not greater than 15 characters.");
    } else if (isNull(motherName) == true || motherName.length() < 3 || motherName.length() > 30) {
      throw new InvalidNameException("Mother's name is not correct. Name must be of atleast 3 characters and not greater than 15 characters.");
    } else if (isNull(gender) == true) {
      throw new InvalidNameException("Gender is not correct. Maybe you didnt select an option.");
    } else if (isNull(DOB) == true) {
      throw new NullException("Date of Birth is not correct. Probably you didnt enter a date.");
    } else if (isNull(address) == true || address.length() < 3 || address.length() > 30) {
      throw new NullException("Address is not correct. Address must be of atleast 3 characters and not greater than 30 characters.");
    } else if (isNull(crime) == true || crime.length() < 3 || crime.length() > 30) {
      throw new NullException("Crime is not correct. Crime must be of atleast 3 characters and not greater than 30 characters.");
    } else if (isNull(sentencedYear) == true || isInt(sentencedYear) == false) {
      throw new NullException(sentencedYear + " is not correct. Sentenced year can't be less than 0 and must be a number.");
    } else if (isNull(dateOfAdmit) == true) {
      throw new InvalidDateException("Date is not correct. Probably you didnt enter a date.");
    } else if (isNull(type) == true) {
      throw new NullException("Type is not correct. Maybe you didnt select an option.");
    } else if (isNull(pathToProfilePhoto) == true) {
      throw new NullException("Profile Photo Location is not correct. Maybe you didnt select a photo.");
    }
  }
  
  public static<T> boolean isNull(T string) {
    return string == null;
  }
  
  public static boolean isInt(String AssumedInt) {
    
    try {
      int probableInt = Integer.parseInt(AssumedInt);
      
      if (probableInt <= 0) {
        return false;
      }
    } catch (Exception parseException) {
      return false;
    }
    
    return true;
  }
}
