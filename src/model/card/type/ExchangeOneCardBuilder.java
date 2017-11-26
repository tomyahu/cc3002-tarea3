package model.card.type;

public class ExchangeOneCardBuilder implements ICardBuilder {

  @Override
  public ICard buildCard(Color aColor, Symbol aSymbol) {
    return new ExchangeOneCard(aColor);
  }

}
