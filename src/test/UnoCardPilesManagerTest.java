package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.card.UnoCardPilesManager;
import model.card.deck.TestingDeck;
import model.card.type.Color;
import model.card.type.Symbol;
import model.card.type.BasicCard;

public class UnoCardPilesManagerTest {
  UnoCardPilesManager manager;
  TestingDeck tester;
  BasicCard blue4;
  BasicCard red9;
  BasicCard green1;
  BasicCard yellow0;
  BasicCard blue5;
  BasicCard red8;
  BasicCard green3;
  BasicCard yellow2;


  @Before
  public void setUp() throws Exception {
    blue4 = new BasicCard(Color.BLUE, Symbol.FOUR);
    blue5 = new BasicCard(Color.BLUE, Symbol.FIVE);
    red9 = new BasicCard(Color.RED, Symbol.NINE);
    red8 = new BasicCard(Color.RED, Symbol.EIGHT);
    green1 = new BasicCard(Color.GREEN, Symbol.ONE);
    green3 = new BasicCard(Color.GREEN, Symbol.THREE);
    yellow0 = new BasicCard(Color.YELLOW, Symbol.ZERO);
    yellow2 = new BasicCard(Color.YELLOW, Symbol.TWO);
    tester = new TestingDeck(blue4, blue5, red9, red8, green1, green3, yellow0, yellow2);
    manager = new UnoCardPilesManager(tester);
  }

  @Test
  public void testManager() {
    int i = 3;
    assertEquals(blue4, manager.getCurrentPlayedCard());
    while(i-- > 0) { // put cards in discarded pile
      manager.discard(manager.drawCard());
    }
    assertEquals(red8, manager.getCurrentPlayedCard());
    assertEquals(7, manager.getDrawableCardsNumber());
    manager.drawCards(3);
    assertEquals(4, manager.getDrawableCardsNumber());
    manager.rebuildDeck();
    assertEquals(red8, manager.getCurrentPlayedCard());
    
  }

}
