package application;

import java.io.File;
import java.io.IOException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

public class ModernPrisonManagementController {

  @FXML
  private Button minimizeButton;

  @FXML
  private Button exitButton;

  @FXML
  private ImageView logo;

  @FXML
  private JFXPasswordField passwordField;

  @FXML
  private Label incorrectPasswordLabel;

  @FXML
  private JFXButton loginButton;
  
  // Dragging Window
  private double xOffset = 0, yOffset = 0;

  @FXML
  void handleExitButton(ActionEvent event) {
    System.exit(0);
  }


  @FXML
  void handleLoginButtonMouseEntered(MouseEvent event) {
    this.loginButton.setBackground(new Background(new BackgroundFill(Color.valueOf("#00d438"), new CornerRadii(15), null)));
  }

  @FXML
  void handleLoginButtonMouseExited(MouseEvent event) {
    this.loginButton.setBackground(new Background(new BackgroundFill(Color.valueOf("#0015ff"), new CornerRadii(15), null)));
  }

  @FXML
  void handleMinimizeButton(ActionEvent event) {
    ((Stage)(this.minimizeButton).getScene().getWindow()).setIconified(true);
  }


  @FXML
  void loginButtonClick(ActionEvent event) {
    if(!this.passwordField.getText().equals("admin")) {
      this.incorrectPasswordLabel.setVisible(true);
    } else {
      this.incorrectPasswordLabel.setVisible(false);
      FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminView.fxml"));
      try {
        AnchorPane root = (AnchorPane) loader.load();
        Scene newScene = new Scene(root);
        Stage currentStage = (Stage) this.loginButton.getScene().getWindow();
        currentStage.setScene(newScene);
        currentStage.show();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
  
  @FXML
  void handleEnterButton(KeyEvent event) {
    if(event.getCode().equals(KeyCode.ENTER)) {
      this.loginButton.fire();
    }
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
    
    @FXML
    public void initialize() {
      File f = new File("./resources/Logo.png");
      logo.setImage(new Image("file://" + f.toURI().getPath()));
      this.incorrectPasswordLabel.setVisible(false);
    }

}
