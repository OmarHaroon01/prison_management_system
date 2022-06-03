package application;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class DialogueBoxController {

    @FXML
    private Label dialogueBoxTextField;

    @FXML
    private JFXButton closeButton;
    
    // Dragging Window
    private double xOffset = 0, yOffset = 0;
    //global variable for message;
    private String globalMessage;
    @FXML
    void handleCloseButtonClick(ActionEvent event) {
      Stage currentStage = (Stage) this.closeButton.getScene().getWindow();
      currentStage.close();
    }

    @FXML
    void handleMouseClick(MouseEvent event) {
      xOffset = event.getSceneX();
      yOffset = event.getSceneY();
    }

    @FXML
    void handleMouseDrag(MouseEvent event) {
      this.closeButton.getScene().getWindow().setX(event.getScreenX() - xOffset);
      this.closeButton.getScene().getWindow().setY(event.getScreenY() - yOffset);
    }

    @FXML
    void handlecloseButtonEnter(MouseEvent event) {
      if(this.globalMessage.contains("Successfully")) {
        this.closeButton.setBackground(new Background(new BackgroundFill(Color.valueOf("#00d438"), new CornerRadii(15), null)));
      } else {
        this.closeButton.setBackground(new Background(new BackgroundFill(Color.valueOf("#f72828"), new CornerRadii(15), null)));
      }
    }
    
    @FXML
    void handleEnterButton(KeyEvent event) {
      if(event.getCode().equals(KeyCode.ENTER)) {
        this.closeButton.fire();
      }
    }

    @FXML
    void handlecloseButtonExit(MouseEvent event) {
        this.closeButton.setBackground(new Background(new BackgroundFill(Color.valueOf("#d7daf7"), new CornerRadii(15), null)));      
    }
    
    public void passMessage(String message) {
      globalMessage = message;
      this.dialogueBoxTextField.setText(message);
    }

}
