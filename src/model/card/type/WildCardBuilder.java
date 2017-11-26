package model.card.type;

public class WildCardBuilder implements ICardBuilder {
  
  @Override
  public ICard buildCard(Color aColor, Symbol aSymbol) {
    return new WildCard();
  }

}
