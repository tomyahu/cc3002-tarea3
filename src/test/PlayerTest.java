package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import model.card.ICardPilesManager;
import model.card.UnoCardPilesManager;
import model.card.deck.NormalUnoDeck;
import model.card.type.CardFactory;
import model.card.type.Color;
import model.card.type.ICard;
import model.card.type.NullCard;
import model.card.type.Symbol;
import model.player.type.IPlayer;
import model.player.type.RandomPlayer;

public class PlayerTest {
  IPlayer p1;
  IPlayer p2;
  IPlayer p3;
  IPlayer p4;
  ICardPilesManager manager;
  
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
    
    p1 = new RandomPlayer("CPU 1");
    p2 = new RandomPlayer("CPU 2");
    p3 = new RandomPlayer("Mati");
    p4 = new RandomPlayer("Franquito");
   
    manager = new UnoCardPilesManager(new NormalUnoDeck());
    
    ArrayList<ICard> hand = new ArrayList<ICard>();
    hand.add(blue4);
    hand.add(redSkip);
    hand.add(wild4);
    
    p3.addToHand(hand);
    
    ArrayList<ICard> hand2 = new ArrayList<ICard>();
    hand.add(red4);
    
    p4.addToHand(hand2);
  
  }

  @Test
  public void test() {
    assertTrue(p1.hasWon());
    assertTrue(p1.needsToDrawCard(NullCard.instance()));
    ArrayList<ICard> drawnCards = manager.drawCards(5);
    p1.addToHand(drawnCards);
    assertEquals(drawnCards, p1.getHand());
    assertFalse(p1.hasWon());
    assertEquals(5, p1.getHandSize());
    assertFalse(p1.hasOneCard());
    assertFalse(p1.hasSaidUNO());
    p1.setSaidUNO(true);
    assertTrue(p1.hasSaidUNO());
    drawnCards.remove(0);
    for(ICard card : drawnCards) {
      p1.removeCardFromHand(card);
    }
    assertTrue(p1.hasOneCard());
    p1.getCardFromHand(0);
    assertFalse(p1.hasWon());
    
    assertEquals(NullCard.instance(), p2.getCardFromHand(0));
    assertNotEquals(p1, p2);
  }
  
  @Test
  public void drawCardsTest() {
    assertTrue(p3.hasDrawCard());
    assertFalse(p4.hasDrawCard());
    
    assertEquals(p3.getDrawCard(), wild4);
  }

}
