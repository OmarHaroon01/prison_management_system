package application;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;

import dataclass.Prisoner;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import utilities.DataValidator;
import utilities.Serializer;

public class NewEntryFormController {

    @FXML
    private ImageView prisonerImageView;

    @FXML
    private JFXTextField FirstNameTextField;

    @FXML
    private JFXTextField lastNameTextField;

    @FXML
    private JFXTextField fNameTextField;

    @FXML
    private JFXTextField mNameTextField;

    @FXML
    private JFXDatePicker dobDatePicker;

    @FXML
    private JFXRadioButton maleRadioButton;

    @FXML
    private ToggleGroup genderRadioToggle;

    @FXML
    private JFXRadioButton femaleRadioButoon;

    @FXML
    private JFXRadioButton otherRadioButton;

    @FXML
    private JFXTextField addressTextField;

    @FXML
    private JFXTextField crimeTextField;

    @FXML
    private JFXTextField durationTextField;

    @FXML
    private JFXDatePicker admitDatePicker;

    @FXML
    private JFXRadioButton criticalRadioButton;

    @FXML
    private ToggleGroup behaviourToggleGroup;

    @FXML
    private JFXRadioButton moderateRadioButton;

    @FXML
    private JFXRadioButton safeRadioButton;

    @FXML
    private JFXButton cancelButton;

    @FXML
    private JFXButton choosePhotoButton;

    @FXML
    private JFXButton submitButton;

    @FXML
    private JFXButton clearButton;
    
    // Dragging Window
    private double xOffset = 0, yOffset = 0;
    // prisoner photo storing string
    private String pathToPrisonerPhoto;
    //last Prisoner ID
    private int lastPrisonerID;
    //arraylist
    private ArrayList<Prisoner> prisonerArrayList;
    //listview
    private JFXListView<Prisoner> globalListView;
    //observable
    private ObservableList<Prisoner> observablePrisonerList;
    
    Prisoner prisonObject;
    
    int indexForEditing = -1;

    @FXML
    void handleCancelButton(ActionEvent event) {
      Stage currentStage = (Stage)this.cancelButton.getScene().getWindow();
      currentStage.close();
    }

    @FXML
    void handleCancelMouseEntered(MouseEvent event) {
      this.cancelButton.setBackground(new Background(new BackgroundFill(Color.valueOf("#f72828"), new CornerRadii(15), null)));
    }

    @FXML
    void handleCancelMouseExited(MouseEvent event) {
      this.cancelButton.setBackground(new Background(new BackgroundFill(Color.valueOf("#d7daf7"), new CornerRadii(15), null)));
    }

    @FXML
    void handleChoosePhotoButton(ActionEvent event) {
      FileChooser fileChooser = new FileChooser();
      Stage currentStage = (Stage) this.choosePhotoButton.getScene().getWindow();
      File prisonerPhoto = fileChooser.showOpenDialog(currentStage);
      
      if (prisonerPhoto != null) {
        this.pathToPrisonerPhoto = "File://" + prisonerPhoto.toURI().getPath();
        Image image = new Image(this.pathToPrisonerPhoto);
        prisonerImageView.setImage(image);
      }
    }

    @FXML
    void handleChoosePhotoMouseEntered(MouseEvent event) {
      this.choosePhotoButton.setBackground(new Background(new BackgroundFill(Color.valueOf("#00d438"), new CornerRadii(15), null)));
    }
    
    @FXML
    void handleChoosePhotoMouseExited(MouseEvent event) {
      this.choosePhotoButton.setBackground(new Background(new BackgroundFill(Color.valueOf("#d7daf7"), new CornerRadii(15), null)));
    }
   
    private void resetUI() {
      this.FirstNameTextField.setText("");
      this.lastNameTextField.setText("");
      this.fNameTextField.setText("");
      this.mNameTextField.setText("");
      this.dobDatePicker.setValue(null);
      this.maleRadioButton.setSelected(false);
      this.femaleRadioButoon.setSelected(false);
      this.otherRadioButton.setSelected(false);
      this.addressTextField.setText("");
      this.crimeTextField.setText("");
      this.durationTextField.setText("");
      this.admitDatePicker.setValue(null);
      this.criticalRadioButton.setSelected(false);
      this.moderateRadioButton.setSelected(false);
      this.safeRadioButton.setSelected(false);
      this.prisonerImageView.setImage(null);
      this.pathToPrisonerPhoto = null;
      File f = new File("./resources/no-image.png");
      this.prisonerImageView.setImage(new Image("File://" + f.toURI().getPath()));
    }

    @FXML
    void handleClearButton(ActionEvent event) {
       this.resetUI();
    }
    
    @FXML
    void handleEnterButton(KeyEvent event) {
      if(event.getCode().equals(KeyCode.ENTER)) {
        this.submitButton.fire();
      }
    }

    @FXML
    void handleClearMouseEntered(MouseEvent event) {
      this.clearButton.setBackground(new Background(new BackgroundFill(Color.valueOf("#f72828"), new CornerRadii(15), null)));
    }

    @FXML
    void handleClearMouseExited(MouseEvent event) {
      this.clearButton.setBackground(new Background(new BackgroundFill(Color.valueOf("#d7daf7"), new CornerRadii(15), null)));
    }

    @FXML
    void handleSubmitButton(ActionEvent event) {
      String firstName = this.FirstNameTextField.getText();
      String lastName = this.lastNameTextField.getText();
      String fatherName = this.fNameTextField.getText();
      String motherName = this.mNameTextField.getText();
      LocalDate DOB = this.dobDatePicker.getValue();
      String gender = null;
      if (this.maleRadioButton.isSelected()) {
        gender = this.maleRadioButton.getText();
      } else if (this.femaleRadioButoon.isSelected()) {
        gender = this.femaleRadioButoon.getText();
      } else if (this.otherRadioButton.isSelected()) {
        gender = this.otherRadioButton.getText();
      }
      String address = this.addressTextField.getText();
      String crime = this.crimeTextField.getText();
      String durationString = this.durationTextField.getText();
      LocalDate admitDate = this.admitDatePicker.getValue();
      String type = null;
      if (this.criticalRadioButton.isSelected()) {
        type = this.criticalRadioButton.getText();
      } else if (this.moderateRadioButton.isSelected()) {
        type = this.moderateRadioButton.getText();
      } else if (this.safeRadioButton.isSelected()) {
        type = this.safeRadioButton.getText();
      }
      String prisonerPhotoPath = this.pathToPrisonerPhoto;
      
      try {
        DataValidator.isValid(firstName, lastName, fatherName, motherName, gender, DOB, address, crime, durationString, admitDate, type, prisonerPhotoPath);
        
        int duration = Integer.parseInt(durationString);
        Prisoner prisoner = new Prisoner(firstName, lastName, fatherName, motherName, gender, DOB, address, crime, duration, admitDate, type, prisonerPhotoPath, this.lastPrisonerID);
        if (prisonObject == null) {
          prisonerArrayList.add(prisoner);
          Stage parentStage = (Stage) this.submitButton.getScene().getWindow();
          utility.errorMessageHelper(parentStage, "Prisoner Added Successfully!!");
          this.lastPrisonerID++;
          this.resetUI();
        } else {
          prisonerArrayList.set(indexForEditing, prisoner);
          Stage parentStage = (Stage) this.submitButton.getScene().getWindow();
          utility.errorMessageHelper(parentStage, "Prisoner Updated Successfully!!");
        }
        this.observablePrisonerList = FXCollections.observableArrayList(prisonerArrayList);
        this.globalListView.setItems(observablePrisonerList);
        this.globalListView.refresh();
        Serializer.serialize(Serializer.databasePath, prisonerArrayList);
        
      } catch (Exception ex) {
        Stage parentStage = (Stage) this.submitButton.getScene().getWindow();
        utility.errorMessageHelper(parentStage, ex.getMessage());
      }
    }

    @FXML
    void handleSubmitMouseEntered(MouseEvent event) {
      this.submitButton.setBackground(new Background(new BackgroundFill(Color.valueOf("#00d438"), new CornerRadii(15), null)));
    }

    @FXML
    void handleSubmitMouseExited(MouseEvent event) {
      this.submitButton.setBackground(new Background(new BackgroundFill(Color.valueOf("#d7daf7"), new CornerRadii(15), null)));
    }
    
    @FXML
    void handleMouseClick(MouseEvent event) {
      xOffset = event.getSceneX();
      yOffset = event.getSceneY();
    }

    @FXML
    void handleMouseDrag(MouseEvent event) {
      this.cancelButton.getScene().getWindow().setX(event.getScreenX() - xOffset);
      this.cancelButton.getScene().getWindow().setY(event.getScreenY() - yOffset);
    }
    
    public void arrayListReference(ArrayList<Prisoner> arrayList, JFXListView<Prisoner> listView) {
      this.prisonerArrayList = arrayList;
      if (arrayList == null || arrayList.size() == 0) {
        this.lastPrisonerID = 1001;
      } else {
        Prisoner prisoner = prisonerArrayList.get(prisonerArrayList.size() - 1);
        this.lastPrisonerID = prisoner.getID();
        this.lastPrisonerID++;
      }
      this.globalListView = listView;
    }
    
    public void getPrisoner(Prisoner prisoner, int index) {
      prisonObject = prisoner;
      indexForEditing = index;
      this.FirstNameTextField.setText(prisoner.getFirstName());
      this.lastNameTextField.setText(prisoner.getLastName());
      this.fNameTextField.setText(prisoner.getFatherName());
      this.mNameTextField.setText(prisoner.getMotherName());
      this.dobDatePicker.setValue(prisoner.getDOB());
      this.addressTextField.setText(prisoner.getAddress());
      this.crimeTextField.setText(prisoner.getCrime());
      this.durationTextField.setText(String.valueOf(prisoner.getSentencedYear()));
      this.admitDatePicker.setValue(prisoner.getDateOfAdmit());
      this.prisonerImageView.setImage(new Image(prisoner.getPathToPhoto()));
      this.pathToPrisonerPhoto = prisoner.getPathToPhoto();
      if (prisoner.getGender().equals("Male")) {
        this.maleRadioButton.setSelected(true);
      } else  if (prisoner.getGender().equals("Female")) {
        this.femaleRadioButoon.setSelected(true);
      } else {
        this.otherRadioButton.setSelected(true);
      }
      this.clearButton.setDisable(true);
      if (prisoner.getType().equals("Critical")) {
        this.criticalRadioButton.setSelected(true);
      } else if (prisoner.getType().equals("Moderate")) {
        this.moderateRadioButton.setSelected(true);
      } else {
        this.safeRadioButton.setSelected(true);
      }
      this.lastPrisonerID = prisoner.getID();
    }
    


}
