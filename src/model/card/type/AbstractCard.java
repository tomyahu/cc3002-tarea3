package model.card.type;

import controller.IController;
import model.IGameLogic;

/**
 * Card that represents any possible card in Uno, including auxiliary cards for special game
 * functions, such as UsedWildCard
 * 
 * @author danno
 *
 */
public abstract class AbstractCard implements ICard {
  protected Color color;
  protected Symbol symbol;

  /**
   * Initializes the card to have the given color and symbol.
   * 
   * @param color resulting card's color.
   * @param symbol resulting card's symbol.
   */
  public AbstractCard(Color color, Symbol symbol) {
    this.color = color;
    this.symbol = symbol;
  }
  
  @Override
  public boolean isPlayableOver(ICard otherCard) {
    return color.equals(otherCard.getColor()) || symbol.equals(otherCard.getSymbol());
  }
  
  @Override
  public Color getColor() {
    return color;
  }
  
  @Override
  public Symbol getSymbol() {
    return symbol;
  }
  
  @Override
  public void executeAction(IGameLogic game, IController ctrl) {
    ctrl.updatePlayedCard();
  }
  
  @Override
  public boolean isDiscardable() {
    return true;
  }

  @Override
  public String toString() {
    return symbol.getName() + " " + color.getName();
  }

  @Override
  public boolean equals(Object o) {
    if (o instanceof ICard) {
      ICard card = (ICard) o;
      return card.getColor().equals(color) && card.getSymbol().equals(symbol);
    }
    return false;
  }
  
  @Override
  public boolean isDrawCard() {
    return false;
  }
}
