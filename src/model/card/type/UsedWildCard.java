package model.card.type;

import controller.IController;
import model.IGameLogic;

/**
 * Class that represents a wild card after a color has been chosen.
 * 
 * @author danno
 *
 */
public class UsedWildCard extends WildCard {

  /**
   * Initializes a used wild card with the given color and symbol.
   * 
   * @param color the color given to this card.
   * @param symbol the symbol of the previous card.
   */
  public UsedWildCard(Color color, Symbol symbol) {
    super(color, Symbol.USED_WILD_CARD);
  }

  @Override
  public void executeAction(IGameLogic game, IController ctrl) {
    ctrl.showMessage("El siguiente color es " + getColor().getName());
  }

  @Override
  public boolean isDiscardable() {
    return false;
  }
}
