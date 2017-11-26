package controller;

import model.IGameLogic;
import model.card.type.Color;
import model.player.type.IPlayer;

/**
 * Class that implements a controller that effectively does nothing. Used for testing without human
 * input.
 * 
 * @author danno
 *
 */
public class NullController implements IController {

  public NullController(IGameLogic game) {
    game.getCurrentPlayedCard().executeAction(game, this);
  }
  
  @Override
  public Color askForColor() {
    return Color.NONE;
  }
  
  @Override
  public int AskForCardFromHand(IPlayer player) {
    return 0;
  }
  
  @Override
  public void showMessage(String message) {}
  
  @Override
  public void playTurn() {}
  
  @Override
  public void updatePlayedCard() {}

  @Override
  public IPlayer AskForPlayer() {
    return null;
  }

}
