package model.player.type;

import controller.IController;
import model.IGameLogic;
import model.card.type.Color;
import model.card.type.ICard;

/**
 * Class that relies on human input to retrieve cards from the hand.
 * 
 * @author danno
 *
 */
public class HumanPlayer extends AbstractPlayer {

  /**
   * Initializes this player with the given name.
   * 
   * @param name this players name.
   */
  public HumanPlayer(String name) {
    super(name);
  }

  @Override
  public ICard getCardToPlay(IGameLogic game, IController ctrl) {
    int num = ctrl.AskForCardFromHand(this);
    if (num == getHandSize()) {
      return game.drawOneCard(this);
    } else {
      return getCardFromHand(num);
    }
  }

  @Override
  public Color selectColor(IGameLogic game, IController ctrl) {
    return ctrl.askForColor();
  }

  @Override
  public IPlayer selectPlayer(IGameLogic game, IController ctrl) {
    return ctrl.AskForPlayer();
  }

}
