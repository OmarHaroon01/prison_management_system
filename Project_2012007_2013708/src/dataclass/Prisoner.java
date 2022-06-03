package dataclass;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;

import utilities.Serializer;

public class Prisoner implements Serializable{

  private static final long serialVersionUID = 6948499589192951542L;
  
  private String firstName;
  private String lastName;
  private String fatherName;
  private String motherName;
  private String gender;
  private int age;
  private String address;
  private String crime;
  private LocalDate DOB;
  private int sentencedYear;
  private LocalDate dateOfAdmit;
  private LocalDate dateOfRelease;
  private int yearsLeft;
  private String type;
  private String pathToPhoto;
  private int ID;

  public Prisoner(String firstName, String lastName, String fatherName, String motherName, String gender, 
      LocalDate DOB, String address, String crime, int sentencedYear, LocalDate dateOfAdmit, String type, 
      String pathToPhoto, int ID) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.fatherName = fatherName;
    this.motherName = motherName;
    this.gender = gender;
    this.DOB = DOB;
    this.address = address;
    this.crime = crime;
    this.sentencedYear = sentencedYear;
    this.dateOfAdmit = dateOfAdmit;
    this.dateOfRelease = dateOfAdmit.plusYears(sentencedYear);
    this.type = type;
    this.ID = ID;
    this.pathToPhoto = pathToPhoto;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getFatherName() {
    return fatherName;
  }

  public String getMotherName() {
    return motherName;
  }

  public String getGender() {
    return gender;
  }
  
  public LocalDate getDOB() {
    return DOB;
  }

  public int getAge() {
    this.age = Period.between(DOB, LocalDate.now()).getYears();
    return age;
  }

  public String getAddress() {
    return address;
  }

  public String getCrime() {
    return crime;
  }

  public int getSentencedYear() {
    return sentencedYear;
  }

  public LocalDate getDateOfAdmit() {
    return dateOfAdmit;
  }

  public LocalDate getDateOfRelease() {
    return dateOfRelease;
  }

  public int getYearsLeft() {
    int yearsLeft = Period.between(LocalDate.now(), dateOfRelease).getYears();
    return yearsLeft;
  }

  public String getType() {
    return type;
  }

  public String getPathToPhoto() {
    return this.pathToPhoto;
  }
  
  public int getID() {
    return this.ID;
  }
   
  @Override
  public String toString() {
    return "Full Name: " + this.firstName + " " + this.lastName + ", Prisoner ID: " + this.ID;
  }
}
