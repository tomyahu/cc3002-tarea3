package view;

import java.util.Observable;
import java.util.Observer;

import controller.NewGUIController;
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
public class GUIView extends Application implements Observer, IView {
  
  NewUnoLogic game;
  NewGUIController ctrl;
  RegularSkin skin;
  
  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    setUpAssets();
    
    game.addObserver(this);;
    
    primaryStage.setTitle("UNO GUI version 0.2");
    
    Button button = new Button();
    button.setText("waddup");
    
    button.setOnAction(ctrl);
    
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
    ctrl = new NewGUIController(game, this);
    
    skin = new RegularSkin("UnoCards");
  }

  @Override
  public void update(Observable o, Object arg) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void updateCurrentStatus() {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void showPlayerHand(IPlayer player) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void showMessage(String message) {
    AlertBox alert = new AlertBox();
    alert.setTitle("Notificación")
         .addMessage(message)
         .addButton("Cerrar", 0)
         .setMinWidth(300);
    
    alert.display();
  }

  @Override
  public void updatePlayedCard() {
    // TODO Auto-generated method stub
    
  }

}
