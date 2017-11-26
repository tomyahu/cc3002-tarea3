package model.card;

public interface IShuffleStrategy {
  /**
   * Changes the order of the cards in the card pile. It changes the original object's state.
   * 
   * @param aPile CardPile to be shuffled.
   */
  public void shuffle(ICardPile aPile);
}
