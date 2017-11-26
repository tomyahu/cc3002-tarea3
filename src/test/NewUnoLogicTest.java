package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import controller.IController;
import controller.NullController;
import model.IGameLogic;
import model.NewUnoLogic;
import model.UnoLogic;
import model.card.deck.NumericDeck;
import model.card.deck.TestingDeck;
import model.card.type.Color;
import model.card.type.Draw2Card;
import model.card.type.ICard;
import model.card.type.InvertCard;
import model.card.type.BasicCard;
import model.card.type.CardFactory;
import model.card.type.SkipCard;
import model.card.type.Symbol;
import model.card.type.WildCard;
import model.card.type.WildDraw4Card;
import model.player.IPlayerListBuilder;
import model.player.UnoPlayerListBuilder;
import model.player.type.IPlayer;
import model.player.type.NewRandomPlayer;
import model.player.type.RandomPlayer;

public class NewUnoLogicTest {
  IGameLogic logic;
  IPlayer p1;
  IPlayer p2;
  IPlayer p3;
  IPlayer p4;
  IController ctrl;
  
  ICard blue4;
  ICard red4;
  ICard blue8;
  ICard blueSwitch;
  ICard redSkip;
  ICard blueExchange;
  ICard wild;
  ICard wild4;
  ICard wildSeeHand;

  @Before
  public void setUp() throws Exception {
    blue4 = CardFactory.createCard(Color.BLUE, Symbol.FOUR);
    red4 = CardFactory.createCard(Color.RED, Symbol.FOUR);
    blue8 = CardFactory.createCard(Color.BLUE, Symbol.EIGHT);
    blueSwitch = CardFactory.createCard(Color.BLUE, Symbol.INVERT);
    redSkip = CardFactory.createCard(Color.RED, Symbol.SKIP);
    blueExchange = CardFactory.createCard(Color.BLUE, Symbol.EXCHANGE_ONE_CARD);
    wild = CardFactory.createCard(Color.NONE, Symbol.WILD);
    wild4 = CardFactory.createCard(Color.NONE, Symbol.WILD_DRAW_FOUR);
    wildSeeHand = CardFactory.createCard(Color.NONE, Symbol.WILD_SEE_HAND);
    
    p1 = new NewRandomPlayer("R1");
    p2 = new NewRandomPlayer("R2");
    p3 = new NewRandomPlayer("R3");
    p4 = new NewRandomPlayer("R4");
    IPlayerListBuilder playerList = new UnoPlayerListBuilder();
    playerList.addPlayer(p1);
    playerList.addPlayer(p2);
    playerList.addPlayer(p3);
    playerList.addPlayer(p4);
    logic = new NewUnoLogic(playerList, new NumericDeck());
    ctrl = new NullController(logic);
  }

  @Test
  public void testHasEnded() {
    assertFalse(logic.hasEnded());
    while (!logic.hasEnded()) {
      logic.startTurn(ctrl);
      IPlayer currentPlayer = logic.getCurrentPlayer();
      boolean cardPlayed = false;
      while (!cardPlayed) {
        ICard card = currentPlayer.getCardToPlay(logic, ctrl);
        cardPlayed = logic.playCard(card, ctrl);
      }
    }
    logic.announceWinner(ctrl);
    assertTrue(logic.hasEnded());
  }

  @Test
  public void testFirstPlaySkip() {
    IPlayerListBuilder playerList = new UnoPlayerListBuilder();
    IPlayer r1 = new RandomPlayer("P1"), r2 = new RandomPlayer("P2");
    playerList.addPlayer(r1);
    playerList.addPlayer(r2);
    UnoLogic anotherLogic =
        new UnoLogic(playerList, new TestingDeck(CardFactory.createCard(Color.BLUE, Symbol.SKIP)));
    IController anotherCtrl= new NullController(anotherLogic);
    assertEquals(r2, anotherLogic.getCurrentPlayer());
    anotherLogic.startTurn(anotherCtrl);
    assertEquals(r1, anotherLogic.getCurrentPlayer());
  }

  @Test
  public void testFirstPlayInvert() {
    IPlayerListBuilder playerList = new UnoPlayerListBuilder();
    IPlayer r1 = new RandomPlayer("P1"), r2 = new RandomPlayer("P2"), r3 = new RandomPlayer("P3");
    playerList.addPlayer(r1);
    playerList.addPlayer(r2);
    playerList.addPlayer(r3);
    UnoLogic anotherLogic = new UnoLogic(playerList,
        new TestingDeck(CardFactory.createCard(Color.BLUE, Symbol.INVERT)));
    IController anotherCtrl= new NullController(anotherLogic);
    assertEquals(r1, anotherLogic.getCurrentPlayer());
    anotherLogic.startTurn(anotherCtrl);
    assertEquals(r3, anotherLogic.getCurrentPlayer());
  }

  @Test
  public void testFirstPlayDraw2() {
    IPlayerListBuilder playerList = new UnoPlayerListBuilder();
    IPlayer r1 = new RandomPlayer("P1"), r2 = new RandomPlayer("P2");
    playerList.addPlayer(r1);
    playerList.addPlayer(r2);
    UnoLogic anotherLogic = new UnoLogic(playerList,
        new TestingDeck(CardFactory.createCard(Color.BLUE, Symbol.DRAW_TWO),
            CardFactory.createCard(Color.BLUE, Symbol.ZERO),
            CardFactory.createCard(Color.BLUE, Symbol.ZERO),
            CardFactory.createCard(Color.BLUE, Symbol.ZERO),
            CardFactory.createCard(Color.BLUE, Symbol.ZERO),
            CardFactory.createCard(Color.BLUE, Symbol.ZERO),
            CardFactory.createCard(Color.BLUE, Symbol.ZERO),
            CardFactory.createCard(Color.BLUE, Symbol.ZERO),
            CardFactory.createCard(Color.BLUE, Symbol.ZERO),
            CardFactory.createCard(Color.BLUE, Symbol.ZERO),
            CardFactory.createCard(Color.BLUE, Symbol.ZERO),
            CardFactory.createCard(Color.BLUE, Symbol.ZERO),
            CardFactory.createCard(Color.BLUE, Symbol.ZERO),
            CardFactory.createCard(Color.BLUE, Symbol.ZERO),
            CardFactory.createCard(Color.BLUE, Symbol.ZERO),
            CardFactory.createCard(Color.BLUE, Symbol.ZERO),
            CardFactory.createCard(Color.BLUE, Symbol.ZERO)));
    IController anotherCtrl= new NullController(anotherLogic);
    anotherLogic.startTurn(anotherCtrl);
    assertEquals(r2, anotherLogic.getCurrentPlayer());
    assertEquals(7, anotherLogic.getCurrentPlayer().getHandSize());
    anotherLogic.startTurn(anotherCtrl);
    assertEquals(r1, anotherLogic.getCurrentPlayer());
    assertEquals(9, anotherLogic.getCurrentPlayer().getHandSize());
  }

  /*
   * Test that the order of players isn't altered
   */
  @Test
  public void testPlayNumericCard() {
    logic.startTurn(ctrl);
    assertEquals(7, logic.getCurrentPlayer().getHandSize());
    assertEquals(p1, logic.getCurrentPlayer());
    logic.playCard(new BasicCard(Color.BLUE, Symbol.EIGHT), ctrl);
    logic.startTurn(ctrl);
    assertEquals(p2, logic.getCurrentPlayer());
    logic.playCard(new BasicCard(Color.RED, Symbol.EIGHT), ctrl);
    logic.startTurn(ctrl);
    assertEquals(p3, logic.getCurrentPlayer());
    logic.playCard(new BasicCard(Color.YELLOW, Symbol.EIGHT), ctrl);
    logic.startTurn(ctrl);
    assertEquals(p4, logic.getCurrentPlayer());
    logic.playCard(new BasicCard(Color.GREEN, Symbol.EIGHT), ctrl);
    logic.startTurn(ctrl);
    assertEquals(p1, logic.getCurrentPlayer());
    assertTrue(logic.isDrawWellEmpty());
  }

  @Test
  public void testPlaySkipCard() {
    logic.startTurn(ctrl);
    assertEquals(7, logic.getCurrentPlayer().getHandSize());
    assertEquals(p1, logic.getCurrentPlayer());
    for (Color color : Color.values()) {
      if (logic.playCard(new SkipCard(color), ctrl)) {
        break;
      }
    }
    logic.startTurn(ctrl);
    assertEquals(p3, logic.getCurrentPlayer());
  }

  @Test
  public void testPlayInvertCard() {
    logic.startTurn(ctrl);
    assertEquals(7, logic.getCurrentPlayer().getHandSize());
    logic.startTurn(ctrl);
    assertEquals(p2, logic.getCurrentPlayer());
    for (Color color : Color.values()) {
      if (logic.playCard(new InvertCard(color), ctrl)) {
        break;
      }
    }
    logic.startTurn(ctrl);
    assertEquals(p1, logic.getCurrentPlayer());
  }

  @Test
  public void testPlayDraw2Card() {
    assertEquals(7, logic.getCurrentPlayer().getHandSize());
    logic.startTurn(ctrl);
    assertEquals(7, logic.getCurrentPlayer().getHandSize());
    logic.invertDirection();
    logic.startTurn(ctrl);
    logic.invertDirection();
    for (Color color : Color.values()) {
      if (logic.playCard(new Draw2Card(color), ctrl)) {
        assertFalse(logic.isDrawWellEmpty());
        break;
      }
    }
    logic.startTurn(ctrl);
    logic.invertDirection();
    logic.startTurn(ctrl);
    assertEquals(9, logic.getCurrentPlayer().getHandSize());
  }
  
  @Test
  public void testDrawChain() {
    assertEquals(7, logic.getCurrentPlayer().getHandSize());
    logic.startTurn(ctrl);
    
    ArrayList<ICard> mas4 = new ArrayList<ICard>();
    mas4.add(wild4);
    logic.getCurrentPlayer().addToHand(mas4);
    
    assertEquals(8, logic.getCurrentPlayer().getHandSize());
    logic.invertDirection();
    logic.startTurn(ctrl);
    logic.invertDirection();
    for (Color color : Color.values()) {
      if (logic.playCard(new Draw2Card(color), ctrl)) {
        assertFalse(logic.isDrawWellEmpty());
        break;
      }
    }
    logic.startTurn(ctrl);
    logic.invertDirection();
    logic.startTurn(ctrl);
    assertEquals(13, logic.getCurrentPlayer().getHandSize());
    logic.startTurn(ctrl);
    assertEquals(7, logic.getCurrentPlayer().getHandSize());
  }
  
  @Test
  public void testRepetitiveDrawNeed() {
    logic.startTurn(ctrl);
    
    for(int i = logic.getCurrentPlayer().getHandSize() - 1; i > 0 ; i--){
      ICard card = logic.getCurrentPlayer().getHand().get(i); 
      logic.getCurrentPlayer().removeCardFromHand(card);
    }
    
    assertEquals(1,logic.getCurrentPlayer().getHandSize());
    
    boolean cardPlayed = false;
    int j = (logic.getCurrentPlayer().needsToDrawCard(logic.getCurrentPlayedCard())) ? 1 : 0 ;
    while (!cardPlayed) {
      ICard card = logic.getCurrentPlayer().getCardToPlay(logic, ctrl);
      cardPlayed = logic.playCard(card, ctrl);
      if(!cardPlayed) j++;
      
    }
    
    assertEquals(j,logic.getCurrentPlayer().getHandSize());
    
  }
  
  @Test
  public void testPlayExchangeCard() {
    logic.startTurn(ctrl);
    
    ArrayList<ICard> playerHand = new ArrayList<ICard>();
    
    for(ICard card : logic.getCurrentPlayer().getHand())
      playerHand.add(card);
    
    logic.playCard(CardFactory.createCard(logic.getCurrentPlayedCard().getColor(), Symbol.EXCHANGE_ONE_CARD), ctrl);
    
    boolean bool = true;
    
    for(ICard card : playerHand)
        bool = bool && logic.getCurrentPlayer().getHand().contains(card);
    
    assertFalse(bool);
  }
  
  @Test
  public void testPlayWildCard() {
    assertEquals(7, logic.getCurrentPlayer().getHandSize());
    assertTrue(logic.playCard(new WildCard(), ctrl));
    for (Color color : Color.values()) {
      if (logic.playCard(new BasicCard(color, Symbol.ONE), ctrl)) {
        return;
      }
    }
    fail();
  }

  @Test
  public void testPlayWildDraw4Card() {
    assertEquals(7, logic.getCurrentPlayer().getHandSize());
    logic.startTurn(ctrl);
    assertEquals(7, logic.getCurrentPlayer().getHandSize());
    logic.invertDirection();
    logic.startTurn(ctrl);
    logic.invertDirection();
    logic.playCard(new WildDraw4Card(), ctrl);
    assertFalse(logic.isDrawWellEmpty());
    logic.startTurn(ctrl);
    logic.invertDirection();
    logic.startTurn(ctrl);
    assertEquals(11, logic.getCurrentPlayer().getHandSize());
    for (Color color : Color.values()) {
      if (logic.playCard(new BasicCard(color, Symbol.ONE), ctrl)) {
        return;
      }
    }
    fail();
  }
  
  @Test
  public void testPlayWildSeeHandCard() {
    for(IPlayer player : logic.getPlayers())
      assertEquals(7, player.getHandSize());
    
    logic.playCard(wildSeeHand, ctrl);
    
    for(IPlayer player : logic.getPlayers())
      assertEquals(7, player.getHandSize());
  }

  @Test
  public void testPlayerPlay() {
    assertEquals(7, logic.getCurrentPlayer().getHandSize());
    logic.playCard(logic.getCurrentPlayer().getCardToPlay(logic, ctrl), ctrl);
    logic.startTurn(ctrl);
    logic.playCard(logic.getCurrentPlayer().getCardToPlay(logic, ctrl), ctrl);
    logic.startTurn(ctrl);
    logic.playCard(logic.getCurrentPlayer().getCardToPlay(logic, ctrl), ctrl);
    logic.startTurn(ctrl);
    logic.playCard(logic.getCurrentPlayer().getCardToPlay(logic, ctrl), ctrl);
    logic.startTurn(ctrl);
    logic.playCard(logic.getCurrentPlayer().getCardToPlay(logic, ctrl), ctrl);
  }
  
}