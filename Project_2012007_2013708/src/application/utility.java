package application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class utility {

  public static void errorMessageHelper(Stage parentStage, String message) {
    try {
      FXMLLoader loader = new FXMLLoader(utility.class.getResource("DialogueBox.fxml"));
      AnchorPane root = (AnchorPane) loader.load();
      DialogueBoxController dialogueBoxController = (DialogueBoxController)loader.getController();
      dialogueBoxController.passMessage(message);
      
      Stage errorStage = new Stage();
      Scene newScene = new Scene(root);
      errorStage.initStyle(StageStyle.UNDECORATED);
      errorStage.setScene(newScene);
      errorStage.sizeToScene();
      errorStage.initOwner(parentStage);
      errorStage.initModality(Modality.APPLICATION_MODAL);
      errorStage.showAndWait();
      
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }
}
