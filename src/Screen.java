import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;


public class Screen extends Application {
	public 

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);

	}


	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Connect 4");
		Scene scene= new Scene(makeGridPane(),720,620);
		primaryStage.setScene(scene);
		primaryStage.show();

	}
	
	 public GridPane makeGridPane(){
	        GridPane grid = new GridPane();
	        
	        grid.setPadding(new Insets(10,10,10,10));
	        grid.setStyle("-fx-background-color: DODGERBLUE;");

	        for (int i = 6; i > 0; i--){
	            for (int j = 0; j < 7; j++){
	                Circle circle = new Circle(50, Color.WHITE);
	                circle.setStroke(Color.BLACK);
	                grid.add(circle, j, i-1);
	            }
	        }

	        return grid;
	    }

}
