package model.player.type;

import java.util.ArrayList;

import controller.IController;
import model.IGameLogic;
import model.card.type.Color;
import model.card.type.ICard;
import model.card.type.NullCard;
import model.card.type.NullCardBuilder;
import model.card.type.Symbol;

/**
 * Class that encapsulates a players hand behaviour. Delegates the requests for classes that extend
 * this one.
 * 
 * @author danno
 */
public abstract class AbstractPlayer implements IPlayer {

  protected ArrayList<ICard> hand = new ArrayList<ICard>();
  private String name;
  private boolean saidUno = false;

  /**
   * Initializes a player with the given name.
   * 
   * @param aName a name for this player.
   */
  protected AbstractPlayer(String aName) {
    name = aName;
  }

  @Override
  public void addToHand(ArrayList<ICard> hand) {
    this.hand.addAll(hand);
    cleanHand();
  }

  @Override
  public boolean hasWon() {
    return hand.isEmpty();
  }

  @Override
  abstract public ICard getCardToPlay(IGameLogic game, IController ctrl);

  @Override
  abstract public Color selectColor(IGameLogic game, IController ctrl);

  @Override
  public int getHandSize() {
    return hand.size();
  }

  @Override
  public boolean hasOneCard() {
    return hand.size() == 1;
  }

  @Override
  public ArrayList<ICard> getHand() {
    return hand;
  }

  @Override
  public void setSaidUNO(boolean status) {
    saidUno = status;
  }

  @Override
  public boolean hasSaidUNO() {
    return saidUno;
  }

  @Override
  public void removeCardFromHand(ICard card) {
    hand.remove(card);
  }

  @Override
  public boolean needsToDrawCard(ICard currentCard) {
    boolean needsTo = true;
    for (ICard card : hand) {
      needsTo = needsTo && !card.isPlayableOver(currentCard);
    }
    return needsTo;
  }

  @Override
  public ICard getCardFromHand(int number) {
    try {
      return hand.get(number);
    } catch (IndexOutOfBoundsException e) {
      return NullCard.instance();
    }
  }

  @Override
  public String toString() {
    return name;
  }

  @Override
  public boolean equals(Object o) {
    if (o instanceof IPlayer) {
      return ((IPlayer) o).getHand().equals(hand);
    }
    return false;
  }

  private void cleanHand() {
    hand.removeIf(card -> !card.isDiscardable());
  }
  
  @Override
  public boolean hasDrawCard() {
    boolean bool = false;
    
    for(ICard card : hand)
      bool = bool || card.isDrawCard();
    
    return bool;
  }

  @Override
  public ICard getDrawCard() {
    ICard drawCard = new NullCardBuilder().buildCard(Color.NONE, Symbol.NONE);
    
    for(ICard card : hand)
      if(card.isDrawCard()) drawCard = card;
    
    return drawCard;
  }
}
