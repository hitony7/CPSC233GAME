import javax.swing.ButtonModel;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
/**
 * For fancer circle in the future
 * @author Tony
 *
 */

public class Token extends Circle {
	private char type;
	
	public Token() {
		super(50,Color.WHITE);
	}
	public Token(char type) {
		this.type = type;
	}
	
}
