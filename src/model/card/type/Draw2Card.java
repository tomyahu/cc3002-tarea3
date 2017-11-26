package model.card.type;

import controller.IController;
import model.IGameLogic;

/**
 * Implementation of the Draw Two card in Uno.
 * 
 * @author danno
 *
 */
public class Draw2Card extends BasicCard {

  /**
   * Initializes a draw 2 card with the given color and symbol.
   * 
   * @param color the color of the resulting card.
   * @param symbol the symbol of the resulting card.
   */
  public Draw2Card(Color color) {
    super(color, Symbol.DRAW_TWO);
  }

  @Override
  public void executeAction(IGameLogic game, IController ctrl) {
    super.executeAction(game, ctrl);
    game.addToDrawWell(2);
  }
  
  @Override
  public boolean isDrawCard()
  {
    return true;
  }
}
