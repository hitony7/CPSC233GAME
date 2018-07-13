/*
 * Tony's Class
 * To Do 
 *
 */
public class GameConfiguration {
	int[][] boardSize;
	private int x;
	private int y;
	//CONSTANT
	//Player piece's represenation as strings
	final String PP1 = "x";
	final String PP2 = "o";
	final String nullSpace = "_";
	
	//Constructor 
	public GameConfiguration(int x, int y) {
		this.y = y;
		this.x = x;
		boardSize = new int[this.x][this.y]; 
	}
	
	public int getPiece(int x, int y) {
		return boardSize[x][y];
	}
	
	public void setPiece(int pieceType,int x, int y) {
		 boardSize[x][y] = pieceType;
	}
	
	public boolean validity(int x, int y) {
		return false;
		
	}
	

}
