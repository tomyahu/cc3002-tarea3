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
public class NewHumanPlayer extends AbstractPlayer {

  /**
   * Initializes this player with the given name.
   * 
   * @param name this players name.
   */
  public NewHumanPlayer(String name) {
    super(name);
  }

  @Override
  public ICard getCardToPlay(IGameLogic game, IController ctrl) {
    
    if(this.needsToDrawCard(game.getCurrentPlayedCard()))
      return game.drawOneCard(this);
    
    int num = ctrl.AskForCardFromHand(this);
    return getCardFromHand(num);
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
