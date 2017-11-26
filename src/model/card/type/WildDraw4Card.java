package model.card.type;

import controller.IController;
import model.IGameLogic;

public class WildDraw4Card extends WildCard {

  /**
   * Initializes a wild draw 4 card with no color and the wild draw 4 symbol.
   */
  public WildDraw4Card() {
    super(Color.NONE, Symbol.WILD_DRAW_FOUR);
  }
  
  @Override
  public void executeAction(IGameLogic game, IController ctrl) {
    super.executeAction(game, ctrl);
    game.addToDrawWell(4);
  }
  
  @Override
  public boolean isDrawCard()
  {
    return true;
  }
}
