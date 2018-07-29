import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Input {
	private Button[] inputB = new Button[7];
	private HBox layout = new HBox(15);
	
	public HBox makeInput() {
		for (int i = 0; i < inputB.length; i++) {
			inputB[i] = new Button("Column: " + (i+1));
			System.out.println(inputB[i]);
			layout.getChildren().add(inputB[i]);
	}

		layout.setAlignment(Pos.CENTER);
		
		return layout;
		
	}


}
