package model.card;

import java.util.ArrayList;

import model.card.type.ICard;

/**
 * Class that implements a shuffle strategy storing the CardPile's contents into an ArrayList and
 * taking random elements from it to push back into the CardPile.
 * 
 * @author danno
 *
 */
public class ShuffleWithArrayList implements IShuffleStrategy {
  
  @Override
  public void shuffle(ICardPile aPile) {
    ArrayList<ICard> cardsInPile = new ArrayList<ICard>();
    while (!aPile.isEmpty()) {
      cardsInPile.add(aPile.popCard());
    }

    while (!cardsInPile.isEmpty()) {
      int i = (int) (Math.random() * cardsInPile.size());
      aPile.pushCard(cardsInPile.remove(i));
    }
  }
}
