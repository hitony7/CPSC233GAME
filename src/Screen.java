import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class Screen extends Application implements EventHandler<ActionEvent> {
	// Created Instance of Main game from text based ver.
	private Game game;
	// Created Panes
	private GridPanel board;
	private VBox rootPane;
	private Input input;
	private Toolbar bar;
	
	private Startpanel menuP = new Startpanel();
	// Scences that will be switchable
	private Scene startMenu, gameB;
	private Stage primaryStage;

	/**
	 * Main
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);

	}

	/**
	 * Start that main and stage/scean objects
	 * 
	 * @param args
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		// To be able to referance stage outside start
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Connect 4");
		this.primaryStage.setResizable(false);

		// button event handler to this class
		menuP.pVPButton.setOnAction(this);
		menuP.pVCompButton.setOnAction(this);
		menuP.quitButton.setOnAction(this);
		// Main menu class to scene will add more later
		startMenu = new Scene(menuP.makeMenu(), 720, 620);
		// set main menu
		this.primaryStage.setScene(startMenu);
		this.primaryStage.show();

	}

	/**
	 * EventHandler for all buttons in the game
	 */
	@Override
	public void handle(ActionEvent event) {
		// menu to pvp
		if (event.getSource() == menuP.pVPButton) {
			newGame();
			this.primaryStage.setScene(gameB);

		}
		// menu to pvC
		if (event.getSource() == menuP.pVCompButton) {
			newGame();
			this.primaryStage.setScene(gameB);
		}
		// menu to exit
		if (event.getSource() == menuP.quitButton) {
			Platform.exit();
		}
		// game to menu
		if (event.getSource() == bar.homeButton) {
			this.primaryStage.setScene(startMenu);
		}
		// 7 Button for col input
		for (int i = 0; i < input.getInputB().length; i++) {
			if (event.getSource() == input.getInputB()[i]) {
				game.turn(i); // paramter the button number(col) pressed
				update(); // update board
				bar.updateTurn(game.isPlayer1(), game.isGameOver(), game.isGameDraw()); // Change label
			}
		}

	}

	/**
	 * taskbar label changer
	 */
	private void update() {
		board.setPiece(game.getConfig().board, 'x');
	}

	/**
	 * Game Panel Create
	 */
	private void newGame() {
		game = new Game();
		board = new GridPanel(game.getConfig());
		input = new Input();
		bar = new Toolbar();
		
		// Root Pane that will contain the others
		rootPane = new VBox();
		// Scence for the game board
		gameB = new Scene(rootPane);
		// Add three of 3 pane for the grid
		rootPane.getChildren().addAll(bar.createBorderPane(), input.makeInput(), board.makeGridPane());
		rootPane.autosize();
		//Set action handler of this class
		bar.homeButton.setOnAction(this);
		for (int i = 0; i < input.getInputB().length; i++) {
			input.getInputB(i).setOnAction(this);
		}
	}
}
