import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBase;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Input {
	private Button[] inputB = new Button[7];
	private HBox layout = new HBox(15);
	
	public HBox makeInput() {
		for (int i = 0; i < getInputB().length; i++) {
			getInputB()[i] = new Button("Column: " + (i+1));
			System.out.println(getInputB()[i]);
			layout.getChildren().add(getInputB()[i]);
	}

		layout.setAlignment(Pos.CENTER);
		
		return layout;
		
	}

	public Button[] getInputB() {
		return inputB;
	}

	public void setInputB(Button[] inputB) {
		this.inputB = inputB;
	}

	public Button getInputB(int i) {
		// TODO Auto-generated method stub
		return inputB[i];
	}


}
