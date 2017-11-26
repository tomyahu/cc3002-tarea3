package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.card.CompositeCardPile;
import model.card.ICardPile;
import model.card.type.Color;
import model.card.type.Symbol;
import model.card.type.BasicCard;

public class UnoCardPileTest {
  ICardPile pile;
  ICardPile anotherPile;
  BasicCard blue4;
  BasicCard red9;
  BasicCard green1;
  BasicCard yellow0;

  @Before
  public void setUp() throws Exception {
    pile = new CompositeCardPile();
    anotherPile = new CompositeCardPile();
    blue4 = new BasicCard(Color.BLUE, Symbol.FOUR);
    red9 = new BasicCard(Color.RED, Symbol.NINE);
    green1 = new BasicCard(Color.GREEN, Symbol.ONE);
    yellow0 = new BasicCard(Color.YELLOW, Symbol.ZERO);
  }

  @Test
  public void testPushPeekPop() {
    assertTrue(pile.isEmpty());
    pile.pushCard(red9);
    assertFalse(pile.isEmpty());
    assertEquals(red9, pile.peekCard());
    pile.popCard();
    assertTrue(pile.isEmpty());
  }
  
  @Test
  public void testPushPile() {
    pile.pushCard(red9);
    pile.pushCard(yellow0);
    anotherPile.pushCard(blue4);
    anotherPile.pushCard(green1);
    assertEquals(yellow0, pile.peekCard());
    assertEquals(green1, anotherPile.peekCard());
    pile.pushCards(anotherPile);
    assertEquals(blue4, pile.peekCard());
    assertTrue(anotherPile.isEmpty());
  }
  
  @Test
  public void testShuffle() {
    pile.pushCard(blue4);
    pile.pushCard(green1);
    pile.pushCard(red9);
    pile.pushCard(yellow0);
    assertEquals(4, pile.getSize());
    int i = 0;
    while(i < 3) { // 3 attempts
      pile.shuffle();
      assertEquals(4, pile.getSize());
      if(!pile.peekCard().equals(yellow0)) {
        return;
      }
    }
    fail();
  }

}
