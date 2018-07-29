import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class GridPanel {
	private int ROW;
	private int COL;
	private GridPane grid = new GridPane();
	
	public GridPanel(GameConfiguration board) {
		this.ROW = board.ROW;
		this.COL = board.COL;
	}
	
	 public GridPane makeGridPane(){
		 	
	        
	        grid.setPadding(new Insets(10,10,10,10));
	        grid.setStyle("-fx-background-color: DODGERBLUE;");

	        for (int x = ROW; x > 0; x--){
	        	System.out.println("x");
	            for (int y = 0; y < COL; y++){
	            	System.out.println("y");
	            	//Currently creating token class will replace cirlces
	                Circle circle = new Circle(50, Color.WHITE);
	                circle.setStroke(Color.BLACK);
	                grid.add(circle, y, x-1);
	            }
	        }

	        return grid;
	    }
	 
	 public void setPiece(int x, int y,char token) {
		 	Circle circle = new Circle(50);
		 	if (token == 'x') {
		 		circle.setFill(Color.RED);
                circle.setStroke(Color.BLACK);
		 	} else
		 		circle.setFill(Color.BLUE);
		 		circle.setStroke(Color.BLACK);
		   grid.add(circle, y, x-1);
	 }
}
