package model.card.type;

/**
 * Implementation of a basic card in Uno. This includes numeric cards, skip cards, invert cards and
 * draw 2 cards. In particular, an instance of this class represents the behaviour of a numeric
 * card.
 * 
 * @author danno
 *
 */
public class BasicCard extends AbstractCard {

  /**
   * Initializes a numeric card with the given color and symbol.
   * 
   * @param color the color of the resulting card.
   * @param symbol the symbol of the resulting card.
   */
  public BasicCard(Color color, Symbol symbol) {
    super(color, symbol);
  }
  
  @Override
  public boolean isFirstPlayable() {
    return true;
  }
}
