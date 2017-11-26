package model.card;

import model.card.type.ICard;

/**
 * Implementation of a card pile that uses a composite design pattern.
 * 
 * @author danno
 *
 */
public class CompositeCardPile implements ICardPile {
  private ICardPileNode first;
  private IShuffleStrategy shuffler;

  /**
   * Initializes an empty CardPile with an ArrayList-based shuffler strategy.
   */
  public CompositeCardPile() {
    this(new ShuffleWithArrayList());
  }

  /**
   * Initialized an empty CardPile with the given shuffler strategy.
   * 
   * @param aShuffler
   */
  public CompositeCardPile(IShuffleStrategy aShuffler) {
    first = new EmptyCardPileNode();
    shuffler = aShuffler;
  }
  
  @Override
  public int getSize() {
    return first.getSize();
  }
  
  @Override
  public ICard pushCard(ICard newCard) {
    first = new FilledCardPileNode(newCard, first);
    return newCard;
  }
  
  @Override
  public ICard popCard() {
    ICard poppedCard = first.peekCard();
    first = first.popNode();
    return poppedCard;
  }
  
  @Override
  public ICard peekCard() {
    return first.peekCard();
  }
  
  @Override
  public void shuffle() {
    shuffler.shuffle(this);
  }
  
  @Override
  public boolean isEmpty() {
    return getSize() == 0;
  }
  
  @Override
  public void pushCards(ICardPile otherPile) {
    while (!otherPile.isEmpty()) {
      pushCard(otherPile.popCard());
    }
  }
}
