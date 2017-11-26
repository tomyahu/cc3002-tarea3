package model.card.deck;

import model.card.CompositeCardPile;
import model.card.ICardPile;
import model.card.type.Color;
import model.card.type.CardFactory;
import model.card.type.Symbol;

/**
 * Class that builds a deck that contains 40 cards, each with a symbol from 0-9 and a color from one
 * of the 4 valid colors.
 * 
 * @author danno
 *
 */
public class NumericDeck implements IDeckStrategy {
  
  @Override
  public ICardPile createDeck() {
    ICardPile deck = new CompositeCardPile();
    for (Color color : Color.getColors()) {
      for (Symbol symbol : Symbol.getNumeric()) {
        deck.pushCard(CardFactory.createCard(color, symbol));
      }
    }
    deck.shuffle();
    return deck;
  }

}
