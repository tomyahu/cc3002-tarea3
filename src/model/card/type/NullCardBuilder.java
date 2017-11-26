package model.card.type;

public class NullCardBuilder implements ICardBuilder {
  
  @Override
  public ICard buildCard(Color aColor, Symbol aSymbol) {
    return NullCard.instance();
  }

}
