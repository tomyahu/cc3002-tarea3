package view.alertBoxes;

import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

public class VBoxAlertBox extends AbstractAlertBox {

  private VBox layout;
  private int value;
  
  /**
   * Initializes a new instance of a VBoxAlertBox.
   */
  public VBoxAlertBox() {
    window = new Stage();
    window.initModality(Modality.APPLICATION_MODAL);

    layout = new VBox();
    layout.setAlignment(Pos.CENTER);
    
    value = -1;
  }

  @Override
  public IAlertBox addMessage(String message) {
    Label label = new Label();
    label.setText(message);
    layout.getChildren().add(label);
    return this;
  }
  
  @Override
  public IAlertBox addButton(String message, int returnValue) {
    Button button = new Button(message);
    button.setOnAction(e -> {
                             window.close();
                             value = returnValue;});
    layout.getChildren().add(button);
    return this;
  }
  
  @Override
  public int display() {
    Scene scene = new Scene(layout);
    window.setScene(scene);

    window.showAndWait();
    
    return value;
  }

}
