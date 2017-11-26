package model.card;

import model.card.type.ICard;
import model.card.type.NullCard;

/**
 * Class that represents an empty card pile. It's always found at the end of a CompositeCardPile.
 * Always returns the same NullCard when asked for a card.
 * 
 * @author danno
 *
 */
public class EmptyCardPileNode implements ICardPileNode {
  private final ICard card = NullCard.instance();
  
  @Override
  public int getSize() {
    return 0;
  }
  
  @Override
  public ICardPileNode popNode() {
    return this;
  }
  
  @Override
  public ICard peekCard() {
    return card;
  }
}
