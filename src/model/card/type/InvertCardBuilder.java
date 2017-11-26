package model.card.type;

public class InvertCardBuilder implements ICardBuilder {
  
  @Override
  public ICard buildCard(Color aColor, Symbol aSymbol) {
    return new InvertCard(aColor);
  }

}
