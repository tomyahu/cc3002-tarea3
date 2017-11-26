package model.card;

import model.card.type.ICard;

public interface ICardPileNode {

  /**
   * Returns the length of the card pile node chain
   * 
   * @return the size of the node chain
   */
  public int getSize();

  /**
   * Returns the chain after this node. Use after peek() to obtain the previous node's contents.
   * 
   * @return the chain after this node (non-inclusive)
   */
  public ICardPileNode popNode();

  /**
   * Returns the card contained in this node
   * 
   * @return the card contained in the node
   */
  public ICard peekCard();
}
