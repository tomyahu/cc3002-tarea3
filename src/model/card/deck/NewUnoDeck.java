package model.card.deck;

import model.card.CompositeCardPile;
import model.card.ICardPile;
import model.card.type.Color;
import model.card.type.Symbol;
import model.card.type.CardFactory;

/**
 * Class that builds a deck with the 108 cards of a reglamentary Uno deck.
 * 
 * @author danno
 *
 */
public class NewUnoDeck implements IDeckStrategy {

  /**
   * Returns a shuffled deck with the reglamentary 108 UNO cards
   * 
   * @return a shuffled, basic uno deck
   */
  @Override
  public ICardPile createDeck() {
    ICardPile deck = new CompositeCardPile();
    for (Color color : Color.getColors()) {
      for (Symbol symbol : Symbol.getNumeric()) {
        deck.pushCard(CardFactory.createCard(color, symbol)); // first colored card
        if (!symbol.getName().equals("0")) {
          deck.pushCard(CardFactory.createCard(color, symbol)); // push the second colored card
                                                       // (unless it's a 0)
        }
      }

      int i = 2;
      while (i-- > 0) {
        deck.pushCard(CardFactory.createCard(color, Symbol.SKIP));
        deck.pushCard(CardFactory.createCard(color, Symbol.INVERT));
        deck.pushCard(CardFactory.createCard(color, Symbol.DRAW_TWO));
        deck.pushCard(CardFactory.createCard(color, Symbol.EXCHANGE_ONE_CARD));
      }
    }

    int i = 4;
    while (i-- > 0) {
      deck.pushCard(CardFactory.createCard(Color.NONE, Symbol.WILD));
      deck.pushCard(CardFactory.createCard(Color.NONE, Symbol.WILD_DRAW_FOUR));
      deck.pushCard(CardFactory.createCard(Color.NONE, Symbol.WILD_SEE_HAND));
    }

    deck.shuffle();
    return deck;
  }

}
