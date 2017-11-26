package view;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import controller.NewGUIController;
import model.NewUnoLogic;
import model.card.deck.NormalUnoDeck;
import model.card.type.Color;
import model.card.type.ICard;
import model.card.type.Symbol;
import model.player.IPlayerListBuilder;
import model.player.UnoPlayerListBuilder;
import model.player.type.HumanPlayer;
import model.player.type.IPlayer;
import model.player.type.RandomPlayer;
import view.GUIView;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;;

/**
 * Main class of the GUI UNO Game
 * 
 * Description Pending
 * @author tomimi
 *
 */
public class GUIView extends Application implements Observer, IView {
  
  private NewUnoLogic game;
  private NewGUIController ctrl;
  private RegularSkin skin;
  private BorderPane layout;
  private Stage window;
  
  private HBox playerDisplayTop;
  private HBox handDisplayCenter;
  private HBox buttonsBottom;
  private VBox discardLeft;
  private VBox deckRight;
  
  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    setUpPane();
    setUpSkin("UnoCards");
    setUpAssets();
    
    
    
    game.addObserver(this);
    
    window = primaryStage;
    window.setTitle("UNO GUI version 0.2");
    
    /*Button buttonA = new Button("Next Card");
    buttonA.setOnAction(e -> {
      this.addImage(Color.RED, Symbol.DRAW_TWO, handDisplayCenter);
    });
    buttonsBottom.getChildren().add(buttonA);*/
    
    updateCurrentStatus();
    showPlayerHand(game.getCurrentPlayer());
    
    setPlaces();
    
    
    Scene scene = new Scene(layout, 1000, 600);
    window.setScene(scene);
    
    window.show();
  }
  
  private void setUpPane() {
    layout = new BorderPane();
    
    playerDisplayTop = new HBox();
    handDisplayCenter = new HBox();
    buttonsBottom = new HBox();
    discardLeft = new VBox();
    deckRight = new VBox();
    
    handDisplayCenter.setAlignment(Pos.CENTER);
    handDisplayCenter.setSpacing(10);
    
    playerDisplayTop.setAlignment(Pos.TOP_CENTER);
    playerDisplayTop.setSpacing(80);
    
    discardLeft.setAlignment(Pos.CENTER_LEFT);
    deckRight.setAlignment(Pos.CENTER_RIGHT);
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
  }
  
  private void setUpSkin(String folder) {
    skin = new RegularSkin(folder);
  }
  
  private void setPlaces() {
    layout.setBottom(buttonsBottom);
    layout.setCenter(handDisplayCenter);
    layout.setTop(playerDisplayTop);
    layout.setLeft(discardLeft);
    layout.setRight(deckRight);
  }

  @Override
  public void update(Observable o, Object arg) {
    updatePlayedCard();
    updateCurrentStatus();
  }

  @Override
  public void updateCurrentStatus() {
    playerDisplayTop.getChildren().clear();
    
    
    ArrayList<IPlayer> players = game.getPlayers();
    
    
    Label currentP = new Label();
    currentP.setText("Current\nPlayer");
    playerDisplayTop.getChildren().add(currentP);
    
    for(IPlayer player : players) {
      Label playerNameLabel = new Label();
      playerNameLabel.setText(player.toString() + "\n" + player.getHandSize() + " cards");
      playerDisplayTop.getChildren().add(playerNameLabel);
      
      Label next = new Label();
      next.setText("<");
      playerDisplayTop.getChildren().add(next);
    }
    
    
    
    
  }

  @Override
  public void showPlayerHand(IPlayer player) {
    handDisplayCenter.getChildren().clear();
    
    ArrayList<ICard> cartas = player.getHand();
    
    for(int i = 0; i < Math.min(cartas.size(), 7); i++)
    {
      addImage(cartas.get(i).getColor(), cartas.get(i).getSymbol(), handDisplayCenter);
    }
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
    discardLeft.getChildren().clear();
    
    ICard currentCard = game.getCurrentPlayedCard();
    
    addImage(currentCard.getColor(), currentCard.getSymbol(), discardLeft);
  }
  
  private void addImage(Color color, Symbol symbol, Pane place) {
    Node image = makeCardView(color,symbol);
    
    place.getChildren().add(image);
  }
  
  private Node makeCardView(Color color, Symbol symbol) {
    ImageView image;
    
    String path = skin.getCard(color, symbol);
    Image cardImage = new Image(path);
    image = new ImageView(cardImage);
    image.setSmooth(true);
    image.setCache(true);
    image.setFitWidth(100);
    image.setPreserveRatio(true);
    return image;
  }

}
