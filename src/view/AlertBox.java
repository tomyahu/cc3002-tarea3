package view;

import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

public class AlertBox {

  private Stage window;
  private VBox layout;
  private int value;

  public AlertBox() {
    window = new Stage();
    window.initModality(Modality.APPLICATION_MODAL);

    layout = new VBox();
    layout.setAlignment(Pos.CENTER);
    
    value = -1;
  }

  public AlertBox setTitle(String title) {
    window.setTitle(title);
    return this;
  }

  public AlertBox setMinWidth(int width) {
    window.setMinWidth(250);
    return this;
  }

  public AlertBox addMessage(String message) {
    Label label = new Label();
    label.setText(message);
    layout.getChildren().add(label);
    return this;
  }
  
  public AlertBox addButton(String message, int returnValue) {
    Button button = new Button(message);
    button.setOnAction(e -> {
                             window.close();
                             value = returnValue;});
    layout.getChildren().add(button);
    return this;
  }

  public int display() {
    Scene scene = new Scene(layout);
    window.setScene(scene);

    window.showAndWait();
    
    return value;
  }

}
