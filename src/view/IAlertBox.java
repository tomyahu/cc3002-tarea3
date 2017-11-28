package view;

public interface IAlertBox {
  
  /**
   * Sets the title of the alert box.
   * @param Title The title of the alert box.
   * @return This same alert box.
   */
  IAlertBox setTitle(String title);
  
  /**
   * Sets the alert box minimum width.
   * @param Width The minimum width.
   * @return This same alert box.
   */
  IAlertBox setMinWidth(int width);
  
  /**
   * Adds a message to the alert box.
   * @param Message The message.
   * @return This same alert box.
   */
  IAlertBox addMessage(String message);
  
  /**
   * Adds a button to the alert box that returns a certain value
   * for it to be treated.
   * @param message The text in the button.
   * @param returnValue The value it returns.
   * @return this same alert box.
   */
  IAlertBox addButton(String message, int returnValue);
  
  /**
   * Displays the alert box and waits until the user presses a button.
   * @return The value of the button.
   */
  int display();

}
