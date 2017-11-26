package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.card.type.CardFactory;
import model.card.type.Color;
import model.card.type.ICard;
import model.card.type.NullCard;
import model.card.type.Symbol;
import model.card.type.UsedWildCard;

public class UnoCardTest {
  ICard blue4;
  ICard red4;
  ICard blue8;
  ICard blueSwitch;
  ICard redSkip;
  ICard wild;
  ICard wild4;
  
  @Before
  public void setUp() throws Exception {
    blue4 = CardFactory.createCard(Color.BLUE, Symbol.FOUR);
    red4 = CardFactory.createCard(Color.RED, Symbol.FOUR);
    blue8 = CardFactory.createCard(Color.BLUE, Symbol.EIGHT);
    blueSwitch = CardFactory.createCard(Color.BLUE, Symbol.INVERT);
    redSkip = CardFactory.createCard(Color.RED, Symbol.SKIP);
    wild = CardFactory.createCard(Color.NONE, Symbol.WILD);
    wild4 = CardFactory.createCard(Color.NONE, Symbol.WILD_DRAW_FOUR);
  }

  @Test
  public void testPlayableOver() {
    assertTrue(red4.isPlayableOver(blue4));
    assertTrue(blue8.isPlayableOver(blue4));
    assertFalse(red4.isPlayableOver(blue8));
    assertTrue(blueSwitch.isPlayableOver(blue4));
    assertTrue(wild.isPlayableOver(blue4));
    assertTrue(wild4.isPlayableOver(wild));
  }
  
  @Test
  public void testIsFirstPlayable() {
    assertTrue(red4.isFirstPlayable());
    assertTrue(blue4.isFirstPlayable());
    assertTrue(blueSwitch.isFirstPlayable());
    assertTrue(redSkip.isFirstPlayable());
    assertFalse(wild.isFirstPlayable());
    assertFalse(wild4.isFirstPlayable());
  } 
  
  @Test
  public void testString() {
    assertEquals("4 Azul", blue4.toString());
    assertEquals("Comodin Sin Color", wild.toString());
  }
  
  @Test
  public void testIsDiscardable() {
    assertTrue(blue4.isDiscardable());
    assertTrue(blueSwitch.isDiscardable());
    assertTrue(wild.isDiscardable());
    assertFalse(NullCard.instance().isDiscardable());
    assertFalse(new UsedWildCard(Color.BLUE, Symbol.WILD).isDiscardable());
  }
  
  @Test
  public void testEquals() {
    assertEquals(blue4, CardFactory.createCard(Color.BLUE, Symbol.FOUR));
    assertNotEquals(blue4, red4);
  }
  
  @Test
  public void testIsDrawCard() {
    assertTrue(wild4.isDrawCard());
    assertFalse(blue4.isDrawCard());
  }
  
}
