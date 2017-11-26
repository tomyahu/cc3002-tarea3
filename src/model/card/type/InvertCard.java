package model.card.type;

import controller.IController;
import model.IGameLogic;

/**
 * Implementation of the Invert Card in Uno.
 * 
 * @author danno
 *
 */
public class InvertCard extends BasicCard {

  /**
   * Initializes an invert card with the given color and symbol.
   * 
   * @param color the color of the resulting card.
   * @param symbol the symbol of the resulting card.
   */
  public InvertCard(Color color) {
    super(color, Symbol.INVERT);
  }

  @Override
  public void executeAction(IGameLogic game, IController ctrl) {
    super.executeAction(game, ctrl);
    game.invertDirection();
  }
}
