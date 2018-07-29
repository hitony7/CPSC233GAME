import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Token extends Circle{
	private char type;
	
	public Token() {
		super(50,Color.WHITE);
	}
	public Token(char type) {
		
		this.type = type;
		
	}
}
