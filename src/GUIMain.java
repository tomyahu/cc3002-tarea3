import controller.ConsoleController;
import model.IGameLogic;
import model.UnoLogic;
import model.card.deck.NormalUnoDeck;
import model.player.IPlayerListBuilder;
import model.player.UnoPlayerListBuilder;
import model.player.type.HumanPlayer;
import model.player.type.IPlayer;
import model.player.type.RandomPlayer;
import view.ConsoleView;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
/**
 * Main class of the GUI UNO Game
 * 
 * Description Pending
 * @author tomimi
 *
 */
public class GUIMain extends Application {
  
  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    primaryStage.setTitle("UNO GUI version 0.1");
    
    StackPane layout = new StackPane();
    
    Scene scene = new Scene(layout, 800, 600);
    primaryStage.setScene(scene);
    
    primaryStage.show();
    
  }

}
