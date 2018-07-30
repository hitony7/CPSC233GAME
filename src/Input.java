
/**
 * Input bar with 7 buttons
 * @author T02-1 - Tony Wong
 * @version 1.0
 */
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBase;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Input {
	private Button[] inputB = new Button[7];
	private HBox layout = new HBox(15);

	/**
	 * creates an hbox with 7 buttons
	 * 
	 * @return
	 */
	public HBox makeInput() {
		for (int i = 0; i < getInputB().length; i++) {
			getInputB()[i] = new Button("Column: " + (i + 1));
			layout.getChildren().add(getInputB()[i]);
		}

		layout.setAlignment(Pos.CENTER);

		return layout;

	}

	// button array getter
	public Button[] getInputB() {
		return inputB;
	}

	// button array setter
	public void setInputB(Button[] inputB) {
		this.inputB = inputB;
	}

	// button array setter
	public Button getInputB(int i) {
		return inputB[i];
	}

}
