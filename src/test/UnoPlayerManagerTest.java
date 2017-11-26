package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import model.card.UnoCardPilesManager;
import model.card.deck.NormalUnoDeck;
import model.player.UnoPlayerListBuilder;
import model.player.UnoPlayerManager;
import model.player.type.IPlayer;
import model.player.type.RandomPlayer;

public class UnoPlayerManagerTest {
  RandomPlayer p1,p2,p3,p4;
  UnoPlayerManager playerManager;
  UnoPlayerListBuilder builder;

  @Before
  public void setUp() throws Exception {
    p1 = new RandomPlayer("CPU 1");
    p2 = new RandomPlayer("CPU 2");
    p3 = new RandomPlayer("CPU 3");
    p4 = new RandomPlayer("CPU 4");
    UnoCardPilesManager cardManager = new UnoCardPilesManager(new NormalUnoDeck());
    cardManager.addCardsToPlayer(p1, 5);
    cardManager.addCardsToPlayer(p2, 5);
    cardManager.addCardsToPlayer(p3, 5);
    cardManager.addCardsToPlayer(p4, 5);
    builder = new UnoPlayerListBuilder();
  }

  @Test
  public void test() {
    builder.addPlayer(p1);
    builder.addPlayer(p2);
    builder.addPlayer(p3);
    builder.addPlayer(p4);
    playerManager = new UnoPlayerManager(builder);
    ArrayList<IPlayer> players = playerManager.getPlayers();
    assertTrue(players.contains(p1));
    assertTrue(players.contains(p2));
    assertTrue(players.contains(p3));
    assertTrue(players.contains(p4));
    assertEquals(p1, playerManager.getCurrentPlayer());
    playerManager.startTurn();
    assertEquals(p1, playerManager.getCurrentPlayer());
    playerManager.startTurn();
    assertEquals(p2, playerManager.getCurrentPlayer());
    playerManager.startTurn();
    assertEquals(p3, playerManager.getCurrentPlayer());
    playerManager.startTurn();
    assertEquals(p4, playerManager.getCurrentPlayer());
    playerManager.startTurn();
    assertEquals(p1, playerManager.getCurrentPlayer());
    playerManager.invertDirection();
    assertEquals(p1, playerManager.getCurrentPlayer());
    playerManager.startTurn();
    assertEquals(p4, playerManager.getCurrentPlayer());
    playerManager.skipPlayer();
    playerManager.startTurn();
    assertEquals(p2, playerManager.getCurrentPlayer());
  }
  
  @Test
  public void test2Players() {
    builder.addPlayer(p1);
    builder.addPlayer(p2);
    playerManager = new UnoPlayerManager(builder);
    assertEquals(p1, playerManager.getCurrentPlayer());
    playerManager.startTurn();
    assertEquals(p1, playerManager.getCurrentPlayer());
    playerManager.startTurn();
    assertEquals(p2, playerManager.getCurrentPlayer());
    playerManager.startTurn();
    assertEquals(p1, playerManager.getCurrentPlayer());
    playerManager.startTurn();
    assertEquals(p2, playerManager.getCurrentPlayer());
    playerManager.startTurn();
    assertEquals(p1, playerManager.getCurrentPlayer());
    playerManager.invertDirection();
    assertEquals(p1, playerManager.getCurrentPlayer());
    playerManager.startTurn();
    assertEquals(p1, playerManager.getCurrentPlayer());
    playerManager.skipPlayer();
    playerManager.startTurn();
    assertEquals(p1, playerManager.getCurrentPlayer());
  }

}
