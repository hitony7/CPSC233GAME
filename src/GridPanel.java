/**
 * Reads Game config and draw it on to the pane for the GUI
 *
 * @author T02-1 - Tony Wong
 * @version 1.0
 */

import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class GridPanel {
	//Size of grid
	private int ROW;
	private int COL;
	private GridPane grid = new GridPane();

	/**
	 * constructor for the gridpanel
	 * @param board
	 */
	public GridPanel(GameConfiguration board) {
		this.ROW = board.ROW;
		this.COL = board.COL;
	}
		/**
		 * Creates the pane.
		 * @return GridPane
		 */
	 public GridPane makeGridPane(){
		 	
	        //Style
	        grid.setPadding(new Insets(10,10,10,10));
	        grid.setStyle("-fx-background-color: DODGERBLUE;");
	        for (int x = ROW; x > 0; x--){
	            for (int y = 0; y < COL; y++){
	            	//Currently creating token class will replace cirlces
	                Circle circle = new Circle(50, Color.WHITE);
	                circle.setStroke(Color.BLACK);
	                grid.add(circle, y, x-1);
	            }
	        }

	        return grid;
	    }
	 /**
	  * Changes single piece in the array
	  * @param x
	  * @param y
	  * @param token
	  */
	 public void setPiece(int x, int y,char token) {
		 	Circle circle = new Circle(50);
		 	if (token == 'x') {
		 		circle.setFill(Color.RED);
                circle.setStroke(Color.BLACK);
		 	} else if (token == 'o') {
		 		circle.setFill(Color.BLUE);
		 		circle.setStroke(Color.BLACK);
		 	} else {
		 		circle.setFill(Color.WHITE);
		 		circle.setStroke(Color.BLACK);
		 	}
		   grid.add(circle, y, x);
		   
	 }
	 /**
	  * update all piece of the board
	  * will create a more effienct method
	  * @param board
	  * @param token
	  */
	public void setPiece(char[][] board, char token ) {
		// TODO Auto-generated method stub
		Circle circle = new Circle(50);
		for (int i = 0; i < (board.length); i++) {
			for (int j = 0; j < (board[0].length); j++) {
				setPiece(i, j, board[i][j]);
				
			}
		
	}
	 
}
}
