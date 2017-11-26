import controller.ConsoleController;
import model.IGameLogic;
import model.UnoLogic;
import model.card.deck.NormalUnoDeck;
import model.player.IPlayerListBuilder;
import model.player.UnoPlayerListBuilder;
import model.player.type.HumanPlayer;
import model.player.type.IPlayer;
import model.player.type.RandomPlayer;
import view.ConsoleView;

/**
 * Main class of UNO Game
 * 
 * It instantiates model, view and controller and makes the turn loop
 * while the game hasn't ended.
 * @author eriveros
 *
 */
public class Main {

  public static void main(String[] args) {
    IPlayerListBuilder playerBuilder = new UnoPlayerListBuilder();
    IPlayer p1 = new HumanPlayer("Jugador 1");
    IPlayer p2 = new RandomPlayer("CPU 1");
    IPlayer p3 = new RandomPlayer("CPU 2");
    IPlayer p4 = new RandomPlayer("CPU 3");
    playerBuilder.addPlayer(p1);
    playerBuilder.addPlayer(p2);
    playerBuilder.addPlayer(p3);
    playerBuilder.addPlayer(p4);
    IGameLogic game = new UnoLogic(playerBuilder, new NormalUnoDeck());
    ConsoleView view = new ConsoleView(game);
    ConsoleController ctrl = new ConsoleController(game, view);
    while (!game.hasEnded()) {
      ctrl.playTurn();
    }
    game.announceWinner(ctrl);
  }
}
