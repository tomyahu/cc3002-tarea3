package model.card;

import java.util.ArrayList;

import model.card.deck.IDeckStrategy;
import model.card.deck.NormalUnoDeck;
import model.card.type.ICard;
import model.player.type.IPlayer;

/**
 * Card manager that handles a deck and a discard pile using CompositeCardPiles
 * 
 * @author danno
 *
 */
public class UnoCardPilesManager implements ICardPilesManager {
  private IDeckStrategy deckBuilder;
  private ICardPile deck;
  private ICardPile discarded;

  /**
   * Initializes with a normal Uno deck, and a CompositeCardPile implementation of CardPiles.
   */
  public UnoCardPilesManager() {
    this(new NormalUnoDeck());
  }

  /**
   * Initializes with the given deck generation strategy and CompositeCardPiles.
   * 
   * @param deckStrat the deck construction strategy to use.
   */
  public UnoCardPilesManager(IDeckStrategy deckStrat) {
    this(deckStrat, new CompositeCardPile(), new CompositeCardPile());
  }

  /**
   * Initialized with the given ICardPile implementations.
   * 
   * @param aDeck ICardPile to use with the deck.
   * @param aDiscardPile ICardPile to use with the discard pile.
   */
  public UnoCardPilesManager(ICardPile aDeck, ICardPile aDiscardPile) {
    this(new NormalUnoDeck(), aDeck, aDiscardPile);
  }

  /**
   * Initializes the manager with the given deck generation strategies and CardPile implementations.
   * 
   * @param deckStrat the deck building strategy.
   * @param aDeck ICardPile implementation to use with the deck.
   * @param aDiscardPile ICardPile implementation to use with the discard pile.
   */
  public UnoCardPilesManager(IDeckStrategy deckStrat, ICardPile aDeck, ICardPile aDiscardPile) {
    deck = aDeck;
    discarded = aDiscardPile;
    deckBuilder = deckStrat;
    deck.pushCards(deckBuilder.createDeck());
    while (!deck.peekCard().isFirstPlayable()) {
      deck.shuffle();
    }
    discard(drawCard());
  }
  
  @Override
  public void rebuildDeck() {
    ICard lastCard = discarded.popCard();
    deck.pushCards(discarded);
    deck.shuffle();
    discarded.pushCard(lastCard);
  }
  
  @Override
  public ICard drawCard() {
    if (deck.isEmpty()) {
      rebuildDeck();
    }
    return deck.popCard();
  }
  
  @Override
  public int getDrawableCardsNumber() {
    return deck.getSize() + discarded.getSize() - 1;
  }
  
  @Override
  public ArrayList<ICard> drawCards(int cardsNumber) {
    cardsNumber = cardsNumber < getDrawableCardsNumber() ? cardsNumber : getDrawableCardsNumber();
    ArrayList<ICard> drawnCards = new ArrayList<ICard>();
    while (cardsNumber-- > 0) {
      ICard drawn = drawCard();
      while (!drawn.isDiscardable()) {
        drawn = drawCard();
      }
      drawnCards.add(drawn);
    }
    return drawnCards;
  }
  
  @Override
  public ICard getCurrentPlayedCard() {
    return discarded.peekCard();
  }
  
  @Override
  public void discard(ICard newCard) {
    discarded.pushCard(newCard);
  }
  
  @Override
  public ArrayList<ICard> addCardsToPlayer(IPlayer player, int number) {
    ArrayList<ICard> drawnCards = drawCards(number);
    player.addToHand(drawnCards);
    return drawnCards;
  }

}
