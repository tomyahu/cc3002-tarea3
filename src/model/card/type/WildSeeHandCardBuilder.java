package model.card.type;

public class WildSeeHandCardBuilder implements ICardBuilder {

  @Override
  public ICard buildCard(Color aColor, Symbol aSymbol) {
    return new WildSeeHandCard();
  }

}
