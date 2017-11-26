package model.card.deck;

import java.util.ArrayList;

import model.card.CompositeCardPile;
import model.card.ICardPile;
import model.card.type.ICard;

/**
 * Class that builds a new deck from the given cards in its constructor.
 * 
 * @author danno
 *
 */
public class TestingDeck implements IDeckStrategy {
  ArrayList<ICard> cards = new ArrayList<ICard>();

  /**
   * Initializes the builder with the given cards as the future deck's contents
   * 
   * @param cards the cards to initialize with (0 or more)
   */
  public TestingDeck(ICard... cards) {
    for (ICard card : cards) {
      this.cards.add(card);
    }
  }

  /**
   * Puts the given cards on a card pile and returns
   * 
   * @return a CardPile containing the cards in this object
   */
  @Override
  public ICardPile createDeck() {
    ICardPile deck = new CompositeCardPile();
    while (!cards.isEmpty()) {
      deck.pushCard(cards.remove(0));
    }
    return deck;
  }

}
