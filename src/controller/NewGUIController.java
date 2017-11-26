package controller;

import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.IGameLogic;
import model.card.type.Color;
import model.card.type.ICard;
import model.player.type.IPlayer;
import view.AlertBox;
import view.GUIView;

public class NewGUIController implements IController, EventHandler<ActionEvent> {
  
  IGameLogic game;
  GUIView view;
  Scanner in;

  /**
   * Controller constructor. Initializes model, view, and input method.
   * Also, it plays the card in discard pile.
   * @param game
   * @param viewand
   */
  public NewGUIController(IGameLogic game, GUIView guiView) {
    this.view = guiView;
    this.game = game;
    this.in = new Scanner(System.in);
    showMessage("¡Bienvenido a JavaUNO!");
    game.getCurrentPlayedCard().executeAction(game, this);
  }
  
  @Override
  public Color askForColor() {
    showMessage("Elige un Color");
    
    AlertBox colorAlert = new AlertBox();
    
    colorAlert.setTitle("Colores")
              .setMinWidth(300);
    
    int i = 0;
    for(Color color : Color.getColors()){
      colorAlert.addButton(color.getName(), i);
      i++;
    }
    
    int val = colorAlert.display();
    
    return Color.getColors()[val];
  }

  @Override
  public int AskForCardFromHand(IPlayer player) {
    showMessage("Elige una Carta");
    
    AlertBox cardAlert = new AlertBox();
    
    cardAlert.setTitle("Cartas")
             .setMinWidth(300);
    
    int i = 0;
    for(ICard card : player.getHand()){
      cardAlert.addButton(card.toString(), i);
      i++;
    }
    
    return cardAlert.display();
  }

  @Override
  public IPlayer AskForPlayer() {
    showMessage("Elige a un Jugador");
    
    AlertBox playerAlert = new AlertBox();
    
    playerAlert.setTitle("Jugadores")
               .setMinWidth(300);
    
    int i = 0;
    for(IPlayer player : game.getPlayers()){
      if (!player.equals(game.getCurrentPlayer())){
        playerAlert.addButton(player.toString(), i);
      }
      i++;
    }
    
    int val = playerAlert.display();
    
    return game.getPlayers().get(val);
  }

  @Override
  public void showMessage(String message) {
    view.showMessage(message);
  }

  @Override
  public void playTurn() {
    game.startTurn(this);
    view.updateCurrentStatus();
    IPlayer currentPlayer = game.getCurrentPlayer();
    boolean cardPlayed = false;
    while (!cardPlayed) {
      ICard card = currentPlayer.getCardToPlay(game, this);
      cardPlayed = game.playCard(card, this);
      if(!cardPlayed) showMessage("No se puede poner esta carta.");
    }
  }

  @Override
  public void updatePlayedCard() {
    view.updatePlayedCard();
  }

  @Override
  public void handle(ActionEvent event) {
    // TODO Auto-generated method stub
    
  }

}
