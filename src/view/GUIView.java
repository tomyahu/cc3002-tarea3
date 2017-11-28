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
import view.alertBoxes.VBoxAlertBox;
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
  private VBox handDisplayCenter;
  private VBox discardLeft;
  private VBox deckRight;
  
  private Label centerMessage;
  
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
    window.setTitle("UNO GUI version 1.0");
    
    setPlaces();
    
    Scene scene = new Scene(layout, 1000, 400);
    window.setScene(scene);
    
    window.show();
    
    while (!game.hasEnded()) {
      ctrl.playTurn();
    }
    game.announceWinner(ctrl);
    
    window.close();
  }
  
  /**
   * Sets up the basic elements of the pane and gives the some attributes
   */
  private void setUpPane() {
    layout = new BorderPane();
    
    playerDisplayTop = new HBox();
    handDisplayCenter = new VBox();
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
    
    centerMessage = new Label();
    centerMessage.setFont(new Font("Arial", 16));
  }
  
  /**
   * Sets ups the elements of the game (The players, logic, controller, etc.)
   */
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
  
  /**
   * Sets Up the skin of the cards it is going to be used.
   * @param folder The folder of the skin.
   */
  private void setUpSkin(String folder) {
    skin = new RegularSkin(folder);
  }
  
  /**
   * Sets the places of the components in the pane.
   */
  private void setPlaces() {
    layout.setCenter(makeCenter());
    layout.setTop(makeTop());
    layout.setLeft(makeLeft());
    layout.setRight(makeRight());
  }
  
  /**
   * Sets up the elements of the top part of the border pane.
   * @return The elements of the top part of the pane.
   */
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
  
  /**
   * Sets up the elements of the center part of the border pane.
   * @return The elements of the center part of the pane.
   */
  private Node makeCenter() {
    VBox center = new VBox();
    center.setAlignment(Pos.CENTER);
    center.setSpacing(10);
    
    centerMessage.setText("Mano");
    center.getChildren().addAll(centerMessage, handDisplayCenter);
    return center;
  }
  
  /**
   * Sets up the elements of the right part of the border pane.
   * @return The elements of the right part of the pane.
   */
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

  /**
   * Sets up the elements of the left part of the border pane.
   * @return The elements of the left part of the pane.
   */
  private Node makeLeft() {
    return discardLeft;
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
      
      handDisplayCenter.getChildren().clear();
      centerMessage.setText("La CPU está Jugando...");
    }
    
    
  }

  @Override
  public void showPlayerHand(IPlayer player) {
    centerMessage.setText("Mano");
    handDisplayCenter.getChildren().clear();
    
    ArrayList<ICard> cartas = player.getHand();
    
    for(int i = 0; i < cartas.size()/6; i++)
    {
      HBox lineaCartas = new HBox();
      for(int j = 0; j < 6; j++)
        addImage(cartas.get(i*6 + j).getColor(), cartas.get(i*6 + j).getSymbol(), lineaCartas);
      lineaCartas.setAlignment(Pos.CENTER);
      handDisplayCenter.getChildren().add(lineaCartas);
    }
    
    HBox lineaCartas2 = new HBox();
    for(int j = 0; j < cartas.size()%6; j++)
      addImage(cartas.get((cartas.size()/6)*6 + j).getColor(), cartas.get((cartas.size()/6)*6 + j).getSymbol(), lineaCartas2);
    lineaCartas2.setAlignment(Pos.CENTER);
    handDisplayCenter.getChildren().add(lineaCartas2);
    
    this.resizeWindowProportions(1000.0, Math.max(500.0, (double) (350 + 160*(cartas.size()/6))));
    
  }

  @Override
  public void showMessage(String message) {
    VBoxAlertBox alert = new VBoxAlertBox();
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
  
  /**
   * Adds the image of a card to the component place.
   * @param color The color of the card.
   * @param symbol The symbol of the card.
   * @param place The component where the image is added.
   */
  private void addImage(Color color, Symbol symbol, Pane place) {
    Node image = makeCardView(color,symbol);
    
    place.getChildren().add(image);
  }
  
  /**
   * Returns the imageView of a card in the form of a Node.
   * @param color The color of the card.
   * @param symbol The symbol of the card.
   * @return The component in which is stored the image.
   */
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
  
  /**
   * Resize the window proportions to width x height.
   * @param width The new width of the window.
   * @param height The new height of the window.
   */
  private void resizeWindowProportions(double width, double height) {
    window.setWidth(width);
    window.setHeight(height);
  }
  
}
