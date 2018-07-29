/*
 *Nathaniel's Class
 *To Do:
 *ties everything 
 *Create instance of objects
 *
 */
/**
 * Game starts a game of Connect4 in a text based environment between two human players (currently).
 *
 * <p>Game creates objects from GameConfiguration, HumanPlayer, Move, and Winner classes. It swaps
 * between two players prompting them to place their token in a column. Once a player has made a
 * line of 4 tokens in a row - whether that is horizontal, vertical, or diagonal - the player wins the game.
 *
 * @author T02-1 - Nathaniel Habtegergesa
 * @version 1.0
 */
public class Game {
	public  Game () {
		
	}

  /**
   * Main method for playing/testing the Game class implementation.
   */
 // public static void main(String[] args) {

   // Game connect4 = new Game();

   // connect4.play();

  //}

  /**
   * Creates a Connect4 board object using the GameConfiguration class.
   */
  private GameConfiguration config = new GameConfiguration();

  /**
   * Creates a first player object using the HumanPlayer class, prompts the first user for input.
   */
  private HumanPlayer p1 = new HumanPlayer();

  /**
   * Creates a second player object using the HumanPlayer class, prompts the second user for input.
   */
  private HumanPlayer p2 = new HumanPlayer();

  /**
   * Creates an object of the Move class to make the player's move if possible.
   */
  private Move move = new Move();

  /**
   * Creates an object of the Winner class to determine who the winner is.
   */
  private Winner winner = new Winner();

  /**
   * play method starts the game of Connect4.
   */
  public void play() {

    System.out.println("Welcome to the game of Connect4!"); //Greets the players

    getConfig().draw(); //Draws the initial empty board.

    game: //Declares the main loop of the game.
    while(true) { //Loops until the game has a winner or ends in a draw.

      while(true) { //Start of Player 1's turn.

        System.out.println("Player 1's it's your turn to play!");

        if(move.getMove(getConfig().board, p1.getColumn(), getConfig().PP1)) { //Sets the move of Player 1 after the make their input.

          if(winner.getWinner(getConfig().board) == getConfig().PP1) { //Checks to see if Player 1 has won.

            getConfig().draw(); //Draws the winning board.
            System.out.println("Player 1 wins the game!!!");
            break game; //Exits the main loop and thus the game ends.

          }

          getConfig().draw(); //Draws the board before the next player's turn.
          break; //Player 1's turn is over.

        }
      }

      while(true) { //Start of Player 2's turn.

        System.out.println("Player 2's it's your turn to play!");

        if(move.getMove(getConfig().board, p2.getColumn(), getConfig().PP2)) { //Sets the move of Player 2 after the make their input.

          if(winner.getWinner(getConfig().board) == getConfig().PP2) { //Checks to see if Player 2 has won.

            getConfig().draw(); //Draws the winning board.
            System.out.println("Player 2 wins the game!!!");
            break game; //Exits the main loop and thus the game ends.

          }

          getConfig().draw(); //Draws the board before the next player's turn.
          break; //Player 1's turn is over.

        }
      }

      if (winner.getWinner(getConfig().board) == 'D') { //Checks to see if the game has ended in a draw. Draws only occur
                                           //after an equal amount of moves from both players have happened (21).

        System.out.println("Game ended in a draw!");
        break; //Exits the main loop and thus the game ends.

      }

    }
  }


public GameConfiguration getConfig() {
	return config;
}

public void setConfig(GameConfiguration config) {
	this.config = config;
}
}