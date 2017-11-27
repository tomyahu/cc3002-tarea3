package view;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import controller.NewGUIController;
import model.NewUnoLogic;
import model.card.deck.NewUnoDeck;
import model.card.type.Color;
import model.card.type.ICard;
import model.card.type.Symbol;
import model.player.IPlayerListBuilder;
import model.player.UnoPlayerListBuilder;
import model.player.type.IPlayer;
import model.player.type.NewHumanPlayer;
import model.player.type.NewRandomPlayer;
import view.GUIView;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
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
  private VBox discardLeft;
  private VBox deckRight;
  
  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    setUpPane();
    setUpSkin("TomimiSkin");
    setUpAssets();
    
    ctrl.addObserver(this);
    
    window = primaryStage;
    window.setTitle("UNO GUI version sonic 06");
    
    
    
    setPlaces();
    
    Scene scene = new Scene(layout, 1000, 400);
    window.setScene(scene);
    
    window.show();
    
    while (!game.hasEnded()) {
      ctrl.playTurn();
    }
    game.announceWinner(ctrl);
  }
  
  private void setUpPane() {
    layout = new BorderPane();
    
    playerDisplayTop = new HBox();
    handDisplayCenter = new HBox();
    discardLeft = new VBox();
    deckRight = new VBox();
    
    handDisplayCenter.setAlignment(Pos.CENTER);
    handDisplayCenter.setSpacing(10);
    
    playerDisplayTop.setAlignment(Pos.TOP_CENTER);
    playerDisplayTop.setSpacing(80);
    
    discardLeft.setAlignment(Pos.CENTER_LEFT);
    discardLeft.setSpacing(10);
    
    deckRight.setAlignment(Pos.CENTER);
    deckRight.setSpacing(10);
  }

  private void setUpAssets(){
    IPlayerListBuilder playerBuilder = new UnoPlayerListBuilder();
    IPlayer p1 = new NewHumanPlayer("Jugador 1");
    IPlayer p2 = new NewRandomPlayer("CPU 1");
    IPlayer p3 = new NewRandomPlayer("CPU 2");
    IPlayer p4 = new NewRandomPlayer("CPU 3");
    playerBuilder.addPlayer(p1);
    playerBuilder.addPlayer(p2);
    playerBuilder.addPlayer(p3);
    playerBuilder.addPlayer(p4);
    game = new NewUnoLogic(playerBuilder, new NewUnoDeck());
    ctrl = new NewGUIController(game, this);
  }
  
  private void setUpSkin(String folder) {
    skin = new RegularSkin(folder);
  }
  
  private void setPlaces() {
    layout.setCenter(makeCenter());
    layout.setTop(makeTop());
    layout.setLeft(discardLeft);
    layout.setRight(makeRight());
  }
  
  private Node makeTop() {
    VBox top = new VBox();
    
    top.setAlignment(Pos.TOP_CENTER);
    
    Label topLabel = new Label();
    topLabel.setText("Players (current player in bold)");
    topLabel.setFont(new Font("Arial", 16));
    
    top.setSpacing(10);
    top.getChildren().addAll(topLabel, playerDisplayTop);
    
    
    return top;
  }
  
  private Node makeCenter() {
    VBox center = new VBox();
    center.setAlignment(Pos.CENTER);
    center.setSpacing(10);
    center.getChildren().add(new Label("Mano"));
    
    ScrollPane centerScroll = new ScrollPane();
    centerScroll.setContent(handDisplayCenter);
    centerScroll.setMaxWidth(700);
    centerScroll.setMinHeight(170);
    
    center.getChildren().add(centerScroll);
    return center;
  }
  
  private Node makeRight() {
    Label deckText = new Label("Deck");
    deckText.setFont(new Font("Arial", 16));
    deckRight.getChildren().add(deckText);
    
    ImageView coverView;
    Image coverImage = new Image(skin.getCover());
    coverView = new ImageView(coverImage);
    coverView.setSmooth(true);
    coverView.setCache(true);
    coverView.setFitWidth(100);
    coverView.setPreserveRatio(true);
    
    deckRight.getChildren().add(coverView);
    
    return deckRight;
  }

  @Override
  public void update(Observable o, Object arg) {
    updateCurrentStatus();
  }

  @Override
  public void updateCurrentStatus() {
    playerDisplayTop.getChildren().clear();
    
    ArrayList<IPlayer> players = game.getPlayers();
    
    int i = 0;
    for(IPlayer player : players) {
      Label playerNameLabel = new Label();
      playerNameLabel.setText(player.toString() + "\n" + player.getHandSize() + " cards");
      if(game.getCurrentPlayer().equals(player)) playerNameLabel.setStyle("-fx-font-weight: bold;");
      playerDisplayTop.getChildren().add(playerNameLabel);
      
      Label next = new Label();
      next.setText("<");
      if(i < players.size() - 1) playerDisplayTop.getChildren().add(next);
      i++;
    }
    
    
  }

  @Override
  public void showPlayerHand(IPlayer player) {
    handDisplayCenter.getChildren().clear();
    
    ArrayList<ICard> cartas = player.getHand();
    
    for(int i = 0; i < cartas.size(); i++)
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
    
    Label txt = new Label();
    txt.setText("Last Card Played");
    txt.setFont(new Font("Arial", 16));
    discardLeft.getChildren().add(txt);
    
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
