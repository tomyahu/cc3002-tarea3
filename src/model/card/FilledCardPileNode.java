package model.card;

import model.card.type.ICard;

/**
 * Class that represents a non-empty CardPile. Returns the contained card when asked for it.
 * 
 * @author danno
 *
 */
public class FilledCardPileNode implements ICardPileNode {
  private ICardPileNode next;
  private ICard card;

  /**
   * Positions this node in front of the given card pile, and stores the given card in it.
   * 
   * @param aCard the card to store in this node.
   * @param nextNode the chain to put in behind of this node.
   */
  public FilledCardPileNode(ICard aCard, ICardPileNode nextNode) {
    card = aCard;
    next = nextNode;
  }
  
  @Override
  public int getSize() {
    return next.getSize() + 1;
  }
  
  @Override
  public ICardPileNode popNode() {
    return next;
  }

  
  @Override
  public ICard peekCard() {
    return card;
  }

}
