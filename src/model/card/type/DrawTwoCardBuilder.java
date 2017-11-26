package model.card.type;

public class DrawTwoCardBuilder implements ICardBuilder {
  
  @Override
  public ICard buildCard(Color aColor, Symbol aSymbol) {
    return new Draw2Card(aColor);
  }

}
