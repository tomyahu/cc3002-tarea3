import controller.NewGUIController;
import model.IGameLogic;
import model.NewUnoLogic;
import model.card.deck.NormalUnoDeck;
import model.player.IPlayerListBuilder;
import model.player.UnoPlayerListBuilder;
import model.player.type.HumanPlayer;
import model.player.type.IPlayer;
import model.player.type.RandomPlayer;
import view.GUIView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.control.Button;
import javafx.stage.Stage;;

/**
 * Main class of the GUI UNO Game
 * 
 * Description Pending
 * @author tomimi
 *
 */
public class GUIMain extends Application {
  
  Button button;
  NewUnoLogic game;
  GUIView view;
  NewGUIController ctrl;
  
  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    setUpAssets();
    
    game.addObserver(view);;
    
    primaryStage.setTitle("UNO GUI version 0.1");
    
    button = new Button();
    button.setText("waddup");
    
    button.setOnAction(new NewGUIController(null, null));
    
    StackPane layout = new StackPane();
    layout.getChildren().add(button);
    
    Scene scene = new Scene(layout, 1000, 600);
    primaryStage.setScene(scene);
    
    primaryStage.show();
  }
  
  private void setUpAssets(){
    IPlayerListBuilder playerBuilder = new UnoPlayerListBuilder();
    IPlayer p1 = new HumanPlayer("Jugador 1");
    IPlayer p2 = new RandomPlayer("CPU 1");
    IPlayer p3 = new RandomPlayer("CPU 2");
    IPlayer p4 = new RandomPlayer("CPU 3");
    playerBuilder.addPlayer(p1);
    playerBuilder.addPlayer(p2);
    playerBuilder.addPlayer(p3);
    playerBuilder.addPlayer(p4);
    game = new NewUnoLogic(playerBuilder, new NormalUnoDeck());
    view = new GUIView(game);
    ctrl = new NewGUIController(game, view);
  }

}
