/**
 * Start Menu.
 *
 * @author T02-1 - Minnie Thai
 * @version 1.0
 */
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;


public class Startpanel  {
	private VBox layout = new VBox();
	private Label title = new Label("CONNECT 4");
	public Button pVPButton= new Button(); 
	public Button pVCompButton = new Button();
	public Button quitButton = new Button();
	private Label welcomeLabel =  new Label("Welcome to Connect Four!");
	
	public VBox makeMenu() {
		pVPButton.setText("Start Human vs. Human game");
		pVCompButton.setText("Start Human vs. PC game");
		quitButton.setText("Quit Game");
		layout.getChildren().addAll(welcomeLabel, pVPButton, pVCompButton, quitButton);
		layout.setAlignment(Pos.CENTER);
		
		return layout;
	}


	
	

}
