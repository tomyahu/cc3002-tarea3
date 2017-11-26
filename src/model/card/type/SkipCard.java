package model.card.type;

import controller.IController;
import model.IGameLogic;

/**
 * Implementation of the Skip card in Uno.
 * 
 * @author danno
 *
 */
public class SkipCard extends BasicCard {

  /**
   * Initializes a skip card with the given color and symbol.
   * 
   * @param color the color of the resulting card.
   * @param symbol the symbol of the resulting card.
   */
  public SkipCard(Color color) {
    super(color, Symbol.SKIP);
  }

  @Override
  public void executeAction(IGameLogic game, IController ctrl) {
    super.executeAction(game, ctrl);
    game.skipPlayer();
  }
}
