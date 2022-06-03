package application;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;

import dataclass.Prisoner;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import utilities.Serializer;

public class AdminViewController {

  @FXML
  private JFXListView<Prisoner> prisonerListView;

  @FXML
  private JFXButton editButton;

  @FXML
  private JFXButton removeButton;

  @FXML
  private ImageView prisonerImageView;

  @FXML
  private Label inmateIDLabel;

  @FXML
  private Label fullNameLabel;

  @FXML
  private Label fNameLabel;

  @FXML
  private Label mNameLabel;

  @FXML
  private Label genderLabel;

  @FXML
  private Label ageLabel;

  @FXML
  private Label addressLabel;

  @FXML
  private Label crimeLabel;

  @FXML
  private Label admitLabel;

  @FXML
  private Label releaseLabel;

  @FXML
  private Label yearLeftLabel;

  @FXML
  private Label typeLabel;

  @FXML
  private JFXButton addButton;

  @FXML
  private JFXButton signOutButton;

  @FXML
  private Button minimizeButton;

  @FXML
  private Button exitButton;

  // Dragging Window
  private double xOffset = 0, yOffset = 0;

  ArrayList<Prisoner> prisonerArrayList = null;
  ObservableList<Prisoner> prisonerObservableList = null;

  @FXML
  void handleAddButton(ActionEvent event) {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("NewEntryForm.fxml"));
    try {
      AnchorPane root = (AnchorPane) loader.load();
      NewEntryFormController newEntryFormController = (NewEntryFormController) loader.getController();
      newEntryFormController.arrayListReference(prisonerArrayList, prisonerListView);
      Stage addStage = new Stage();
      Scene addScene = new Scene(root);
      addStage.setScene(addScene);
      Stage currentStage = (Stage) this.addButton.getScene().getWindow();
      addStage.initOwner(currentStage);
      addStage.initModality(Modality.APPLICATION_MODAL);
      addStage.initStyle(StageStyle.UNDECORATED);
      addStage.setResizable(false);
      addStage.showAndWait();
      this.prisonerListView.refresh();
      this.resetUI();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void refreshUI() {
    this.prisonerObservableList = FXCollections.observableArrayList(prisonerArrayList);
    this.prisonerListView.setItems(prisonerObservableList);
  }

  @FXML
  void handleAddMouseEntered(MouseEvent event) {
    this.addButton
        .setBackground(new Background(new BackgroundFill(Color.valueOf("#00d438"), new CornerRadii(15), null)));
  }

  @FXML
  void handleAddMouseExited(MouseEvent event) {
    this.addButton
        .setBackground(new Background(new BackgroundFill(Color.valueOf("#d7daf7"), new CornerRadii(15), null)));
  }

  @FXML
  void handleCellClick(MouseEvent event) {
    Prisoner prisoner = this.prisonerListView.getSelectionModel().getSelectedItem();
    if (prisoner != null) {
      this.editButton.setDisable(false);
      this.removeButton.setDisable(false);
      this.fullNameLabel.setText(prisoner.getFirstName() + " " + prisoner.getLastName());
      this.fNameLabel.setText(prisoner.getFatherName());
      this.mNameLabel.setText(prisoner.getMotherName());
      this.genderLabel.setText(prisoner.getGender());
      this.ageLabel.setText(Integer.toString(prisoner.getAge()));
      this.addressLabel.setText(prisoner.getAddress());
      this.crimeLabel.setText(prisoner.getCrime());
      this.typeLabel.setText(prisoner.getType());
      this.admitLabel.setText(prisoner.getDateOfAdmit().toString());
      this.releaseLabel.setText(prisoner.getDateOfRelease().toString());
      this.yearLeftLabel.setText(Integer.toString(prisoner.getYearsLeft()));
      this.inmateIDLabel.setText(Integer.toString(prisoner.getID()));
      this.prisonerImageView.setImage(new Image(prisoner.getPathToPhoto()));
    }

  }

  @FXML
  void handleEditButton(ActionEvent event) {
    int index = this.prisonerListView.getSelectionModel().getSelectedIndex();
    Prisoner prisoner = this.prisonerListView.getSelectionModel().getSelectedItem();
    if (prisoner != null) {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("NewEntryForm.fxml"));
      try {
        AnchorPane root = (AnchorPane) loader.load();
        NewEntryFormController newEntryFormController = (NewEntryFormController) loader.getController();
        newEntryFormController.arrayListReference(prisonerArrayList, prisonerListView);
        newEntryFormController.getPrisoner(prisoner, index);
        Stage addStage = new Stage();
        Scene addScene = new Scene(root);
        addStage.setScene(addScene);
        Stage currentStage = (Stage) this.addButton.getScene().getWindow();
        addStage.initOwner(currentStage);
        addStage.initModality(Modality.APPLICATION_MODAL);
        addStage.initStyle(StageStyle.UNDECORATED);
        addStage.setResizable(false);
        addStage.showAndWait();
        this.prisonerListView.refresh();
        this.resetUI();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  @FXML
  void handleEditMouseEntered(MouseEvent event) {
    this.editButton
        .setBackground(new Background(new BackgroundFill(Color.valueOf("#00d438"), new CornerRadii(15), null)));
  }

  @FXML
  void handleEditMouseExited(MouseEvent event) {
    this.editButton
        .setBackground(new Background(new BackgroundFill(Color.valueOf("#d7daf7"), new CornerRadii(15), null)));
  }

  @FXML
  void handleExitButton(ActionEvent event) {
    System.exit(0);
  }


  @FXML
  void handleMinimizeButton(ActionEvent event) {
    ((Stage) (this.minimizeButton).getScene().getWindow()).setIconified(true);
  }


  @FXML
  void handleRemoveButton(ActionEvent event) {
    int index = -1;
    index = this.prisonerListView.getSelectionModel().getSelectedIndex();

    if (index != -1) {
      prisonerArrayList.remove(index);
      this.prisonerObservableList = FXCollections.observableArrayList(prisonerArrayList);
      this.prisonerListView.setItems(prisonerObservableList);
      Serializer.serialize(Serializer.databasePath, prisonerArrayList);
      this.prisonerListView.refresh();
      this.resetUI();
    }
  }

  @FXML
  void handleRemoveMouseEntered(MouseEvent event) {
    this.removeButton
        .setBackground(new Background(new BackgroundFill(Color.valueOf("#f72828"), new CornerRadii(15), null)));
  }

  @FXML
  void handleRemoveMouseExited(MouseEvent event) {
    this.removeButton
        .setBackground(new Background(new BackgroundFill(Color.valueOf("#d7daf7"), new CornerRadii(15), null)));
  }

  @FXML
  void handleSignOutButton(ActionEvent event) {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("ModernPrisonManagement.fxml"));
    try {
      AnchorPane root = (AnchorPane) loader.load();
      Scene newScene = new Scene(root);
      Stage currentStage = (Stage) this.signOutButton.getScene().getWindow();
      currentStage.setScene(newScene);
      currentStage.show();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @FXML
  void handleSignOutEntered(MouseEvent event) {
    this.signOutButton
        .setBackground(new Background(new BackgroundFill(Color.valueOf("#f72828"), new CornerRadii(15), null)));
  }

  @FXML
  void handleSignOutExited(MouseEvent event) {
    this.signOutButton
        .setBackground(new Background(new BackgroundFill(Color.valueOf("#d7daf7"), new CornerRadii(15), null)));
  }

  @FXML
  void handleMouseClick(MouseEvent event) {
    xOffset = event.getSceneX();
    yOffset = event.getSceneY();
  }

  @FXML
  void handleMouseDrag(MouseEvent event) {
    this.exitButton.getScene().getWindow().setX(event.getScreenX() - xOffset);
    this.exitButton.getScene().getWindow().setY(event.getScreenY() - yOffset);
  }
  
  public void resetUI() {
    this.fullNameLabel.setText("");
    this.fNameLabel.setText("");
    this.mNameLabel.setText("");
    this.genderLabel.setText("");
    this.ageLabel.setText("");
    this.addressLabel.setText("");
    this.crimeLabel.setText("");
    this.typeLabel.setText("");
    this.admitLabel.setText("");
    this.releaseLabel.setText("");
    this.yearLeftLabel.setText("");
    this.inmateIDLabel.setText("");
    File f = new File("./resources/no-image.png");
    this.prisonerImageView.setImage(new Image("File://" + f.toURI().getPath()));
    this.removeButton.setDisable(true);
    this.editButton.setDisable(true);
  }
  
  @FXML
  public void initialize() {
    
    File f = new File("./resources/no-image.png");
    this.prisonerImageView.setImage(new Image("File://" + f.toURI().getPath()));
    this.prisonerArrayList = Serializer.deserialize(Serializer.databasePath);

    if (prisonerArrayList == null) {
      this.prisonerArrayList = new ArrayList<Prisoner>();
    }

    this.prisonerObservableList = FXCollections.observableArrayList(prisonerArrayList);
    this.prisonerListView.setItems(prisonerObservableList);
    this.removeButton.setDisable(true);
    this.editButton.setDisable(true);
  }

}
