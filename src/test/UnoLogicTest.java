package test;

import static org.junit.Assert.*;

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
import model.player.type.RandomPlayer;

public class UnoLogicTest {
  IGameLogic logic;
  IPlayer p1;
  IPlayer p2;
  IPlayer p3;
  IPlayer p4;
  IController ctrl;
  
  @Before
  public void setUp() throws Exception {
    p1 = new RandomPlayer("R1");
    p2 = new RandomPlayer("R2");
    p3 = new RandomPlayer("R3");
    p4 = new RandomPlayer("R4");
    IPlayerListBuilder playerList = new UnoPlayerListBuilder();
    playerList.addPlayer(p1);
    playerList.addPlayer(p2);
    playerList.addPlayer(p3);
    playerList.addPlayer(p4);
    logic = new UnoLogic(playerList, new NumericDeck());
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
    
    logic.playCard(CardFactory.createCard(Color.NONE, Symbol.WILD_SEE_HAND), ctrl);
    
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