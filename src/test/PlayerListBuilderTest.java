package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import model.card.UnoCardPilesManager;
import model.card.deck.NormalUnoDeck;
import model.player.UnoPlayerListBuilder;
import model.player.type.IPlayer;
import model.player.type.RandomPlayer;

public class PlayerListBuilderTest {
  UnoPlayerListBuilder listBuilder;
  RandomPlayer p1, p2, p3, p4;
  UnoCardPilesManager manager;
  
  @Before
  public void setUp() throws Exception {
    p1 = new RandomPlayer("CPU 1");
    p2 = new RandomPlayer("CPU 2");
    p3 = new RandomPlayer("CPU 3");
    p4 = new RandomPlayer("CPU 4");
    manager = new UnoCardPilesManager(new NormalUnoDeck());
    listBuilder = new UnoPlayerListBuilder();
  }

  @Test
  public void test() {
    p1.addToHand(manager.drawCards(5));
    p2.addToHand(manager.drawCards(5));
    p3.addToHand(manager.drawCards(5));
    p4.addToHand(manager.drawCards(5));
    listBuilder.addPlayer(p1);
    listBuilder.addPlayer(p2);
    listBuilder.addPlayer(p3);
    listBuilder.addPlayer(p4);
    ArrayList<IPlayer> players = listBuilder.buildPlayerList();
    assertTrue(players.contains(p1));
    assertTrue(players.contains(p2));
    assertTrue(players.contains(p3));
    assertTrue(players.contains(p4));
    assertTrue(listBuilder.buildPlayerList().isEmpty());
  }
}
