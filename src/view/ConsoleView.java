package view;

import model.IGameLogic;
import model.card.type.ICard;
import model.player.type.IPlayer;

/**
 * View of the game. It uses the console output for showing information.
 * 
 * @author eriveros
 *
 */
public class ConsoleView implements IView {

  IGameLogic game;

  /**
   * Constructor of the view. Associates a game to it.
   * 
   * @param game
   */
  public ConsoleView(IGameLogic game) {
    this.game = game;
  }

  @Override
  public void updateCurrentStatus() {
    System.out.println("[Turno Actual]: " + game.getCurrentPlayer());
  }

  @Override
  public void showPlayerHand(IPlayer player) {
    System.out.println("[Mano]:");
    int i = 0;
    for (ICard card : player.getHand()) {
      System.out.println(i + ") " + card);
      i++;
    }
  }
  
  @Override
  public void showMessage(String message) {
    System.out.println(message);
  }

  @Override
  public void updatePlayedCard() {
    System.out.println("[Carta Descartada]: " + game.getCurrentPlayedCard());
  }

}
