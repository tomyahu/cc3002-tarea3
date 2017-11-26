package test;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import model.card.ICardPile;
import model.card.deck.NumericDeck;
import model.card.type.Symbol;

public class NumericDeckTest {
  ICardPile deck;

  @Before
  public void setUp() throws Exception {
    deck = new NumericDeck().createDeck();
  }

  @Test
  public void testCreateDeck() {
    List<Symbol> numbers = Arrays.asList(Symbol.getNumeric());
    while(!deck.isEmpty()) {
      assertTrue(numbers.contains(deck.popCard().getSymbol()));
    }
  }

}
