package view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ImageScrollAlertBox extends AbstractAlertBox {
  
  private VBox layout;
  private HBox buttons;
  private ScrollPane scroll;
  private int value;
  
  public ImageScrollAlertBox(){
    window = new Stage();
    window.initModality(Modality.APPLICATION_MODAL);
    
    layout = new VBox();
    layout.setAlignment(Pos.TOP_CENTER);
    buttons = new HBox();
    
    scroll = new ScrollPane();
    scroll.setContent(buttons);
    
    value = -1;
  }

  @Override
  public IAlertBox addMessage(String message) {
    Label messageLabel = new Label("message");
    layout.getChildren().add(messageLabel);
    return this;
  }

  @Override
  /**
   * In this case the message is the path of the image.
   */
  public IAlertBox addButton(String message, int returnValue) {
    Button cardButton = new Button();
    
    ImageView cardView;
    Image cardImage = new Image(message);
    cardView = new ImageView(cardImage);
    cardView.setSmooth(true);
    cardView.setCache(true);
    cardView.setFitWidth(100);
    cardView.setPreserveRatio(true);
    
    cardButton.setGraphic(cardView);
    
    buttons.getChildren().add(cardButton);
    
    return this;
  }

  @Override
  public int display() {
    layout.getChildren().add(scroll);
    
    Scene scene = new Scene(layout);
    window.setScene(scene);

    window.showAndWait();
    
    return value;
  }

}
