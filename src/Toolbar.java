/**
 * @author nat
 */


import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.control.Button;
import javafx.scene.text.TextAlignment;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class Toolbar {
	private String[] players = new String[] { "Player1", "Player2" };
	private int turn;
	public Button homeButton = new Button();
	private Label turnLabel = new Label(players[0] + "'s \n Turn");

	public BorderPane createBorderPane() {
		BorderPane border = new BorderPane();
		HBox contentContainer = new HBox();

		// Creating labels
		Label connect4Label, playerLabel, otherPlayerLabel, returnToHomeLabel, timeLabel;

		// Title at top of screen
		connect4Label = new Label("Connect4");
		connect4Label.setFont(Font.font("Times New Roman", FontWeight.BOLD, 50));
		connect4Label.setTextAlignment(TextAlignment.CENTER);
		connect4Label.setTranslateX(250);
		connect4Label.setPrefWidth(350);

		// First player label
		playerLabel = new Label(players[0]);
		playerLabel.setFont(Font.font("Courier New", FontWeight.BOLD, 24));
		playerLabel.setTranslateY(40);

		// Second player label
		otherPlayerLabel = new Label(players[1]);
		otherPlayerLabel.setFont(Font.font("Courier New", FontWeight.BOLD, 24));
		otherPlayerLabel.setTranslateY(40);

		// Exit game label
		Image homeIcon = new Image("http://simpleicon.com/wp-content/uploads/home-3-64x64.png");
		homeButton.setGraphic(new ImageView(homeIcon));
		homeButton.setStyle("-fx-focus-color: transparent;");
		homeButton.setTranslateX(-100);
		homeButton.setTranslateY(-55);
		// Tracking time labels

		// Turn label
		
		turnLabel.setFont(Font.font("Courier New", FontWeight.BOLD, 34));
		turnLabel.setStyle("-fx-text-fill: red;");
		turnLabel.setTranslateX(100);
		turnLabel.setTranslateY(15);

		// Background image
		//Image background = new Image("https://i1.silvergames.com/p/b/connect-4.png");
		//ImageView seeBackground = new ImageView(background);
		//seeBackground.setFitWidth(730);
		//seeBackground.setFitHeight(150);
		//border.getChildren().add(seeBackground);
		border.setVisible(true);
		border.setPrefSize(730, 150);

		// Containe
		r setting
		contentContainer.getChildren().addAll(connect4Label, playerLabel, homeButton, turnLabel, otherPlayerLabel);


		
		// Border setting
		border.setTop(connect4Label);
		border.setLeft(playerLabel);
		border.setRight(otherPlayerLabel);
		border.setCenter(contentContainer);
		return border;
	}

	public void setPlayers(String first, String second) {
		players[0] = first;
		players[1] = second;
	}

	public String[] getPlayers() {
		return players;
	}
	/**
	 * Label changer
	 * @param player1
	 * @param gameWon
	 * @param draw
	 */
	
	public void updateTurn(boolean player1, boolean gameWon, boolean draw) {
		if (draw) {
			turnLabel.setText("Draw!");
			turnLabel.setStyle("-fx-text-fill: Black;");
		} else if (player1 && gameWon) {
			turnLabel.setText(players[0]+ " has Won!");	
		} else if (!player1 && gameWon) {
			turnLabel.setText(players[1]+ " has Won!");
		} else if (player1) {
			turnLabel.setText(players[0] + "'s \n Turn");
			turnLabel.setStyle("-fx-text-fill: red;");
		} else {
			turnLabel.setText(players[1] + "'s \n Turn ");
			turnLabel.setStyle("-fx-text-fill: Blue;");			
		}
		
	}
}