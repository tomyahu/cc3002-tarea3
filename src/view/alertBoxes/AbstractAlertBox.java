package view.alertBoxes;

import javafx.stage.Stage;

public abstract class AbstractAlertBox implements IAlertBox {
  
  protected Stage window;
  
  @Override
  public IAlertBox setTitle(String title) {
    window.setTitle(title);
    return this;
  }

  @Override
  public IAlertBox setMinWidth(int width) {
    window.setMinWidth(width);
    return this;
  }

  @Override
  public abstract IAlertBox addMessage(String message);

  @Override
  public abstract IAlertBox addButton(String message, int returnValue);

  @Override
  public abstract int display();

}
