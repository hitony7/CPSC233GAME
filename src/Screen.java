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


public class Screen extends Application implements EventHandler<ActionEvent>  {
	private Game game = new Game();
	private GridPanel board = new GridPanel(game.getConfig());
	private Toolbar bar= new Toolbar();
	private Startpanel menuP = new Startpanel();
	public Scene startMenu, gameB;
	private Stage primaryStage;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);

	}


	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Connect 4");
		VBox rootPane= new VBox();
		gameB = new Scene(rootPane);
		rootPane.getChildren().addAll(bar.createBorderPane(),board.makeGridPane());
		rootPane.autosize();
	
		
		menuP.pVPButton.setOnAction(this);
		menuP.pVCompButton.setOnAction(this);
		menuP.quitButton.setOnAction(this);
		bar.homeButton.setOnAction(this);
		
		startMenu = new Scene(menuP.makeMenu(),720,620);
		
		this.primaryStage.setScene(startMenu);
		this.primaryStage.show();

	}


	@Override
	public void handle(ActionEvent event) {
		// TODO Auto-generated method stub
		if(event.getSource() == menuP.pVPButton) {
			this.primaryStage.setScene(gameB);
		}
		if(event.getSource() == menuP.pVCompButton) {
			this.primaryStage.setScene(gameB);
		}
		if(event.getSource() == menuP.quitButton) {
			Platform.exit();
		}
		if(event.getSource() == bar.homeButton) {
			this.primaryStage.setScene(startMenu);
		}
		
		}
	
	
	
	}
