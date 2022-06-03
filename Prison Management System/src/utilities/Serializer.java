package utilities;

import dataclass.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Serializer {
  // path to the database
  private static String OS = System.getProperty("os.name").toLowerCase();
  
  public static final String databasePath = "./database.bin";
  
  // serializing the arraylist to the database file
  public static boolean serialize(String filePath, ArrayList<Prisoner> personsList) {
    File databaseFile = new File(filePath);    
    FileOutputStream fileOutputStream = null;
    ObjectOutputStream objectOutputStream = null;
    
    boolean success = false;
    try {
      fileOutputStream = new FileOutputStream(databaseFile);
      objectOutputStream = new ObjectOutputStream(fileOutputStream);
      objectOutputStream.writeObject(personsList);
      success = true;
    } catch (Exception exception) {
      success = false;
    }
    
    return success;
  }
  
  // deserializing the arraylist from the database file
  public static ArrayList<Prisoner> deserialize(String filePath) {
    File databaseFile = new File(filePath);    
    FileInputStream fileInputStream = null;
    ObjectInputStream objectInputStream = null;
    
    ArrayList<Prisoner> list = null;
    
    try {
      fileInputStream = new FileInputStream(databaseFile);
      objectInputStream = new ObjectInputStream(fileInputStream);
      
      list = (ArrayList<Prisoner>) objectInputStream.readObject();      
    } catch (Exception exception) {
      System.out.println("New file has been created.");
    }
    
    return list;
  }
}

