
public class Logic4InRow {
	
	final int NUM_OF_ROWS = 6;
	final int NUM_OF_COLUMNS = 7;
	private String winner = "";
	private int[] circleCounterInColumn = new int[NUM_OF_COLUMNS]; 	// helps to know when column is full
	private int[][] redCircles = new int[NUM_OF_COLUMNS][NUM_OF_ROWS];
	private int[][] blueCircles = new int[NUM_OF_COLUMNS][NUM_OF_ROWS];
	
	public Logic4InRow() {
		this.newGame();		
	}
	
	public void newGame() {
		this.winner = "";
		for (int q = 0; q < circleCounterInColumn.length; q++) {
			circleCounterInColumn[q] = 0;
		}			
		for (int i = 0; i < NUM_OF_COLUMNS; i++) {
			for (int j = 0; j < NUM_OF_ROWS; j++) {
				this.redCircles[i][j] = 0;
				this.blueCircles[i][j] = 0;
			}		
		}	
	}
	
	public String getWinner() {
		return this.winner;
	}
	
	public int addCircle(int x, boolean redTurn) {
		int y = 0;
		if (!(this.isColumnFull(x))){
			y = circleCounterInColumn[x];
			if (redTurn)
				this.redCircles[x][y] = 1;
			else
				this.blueCircles[x][y] = 1;
			circleCounterInColumn[x]++;
			this.winnerCheack(redTurn, x, y);
			return circleCounterInColumn[x] - 1;			
		}
		else {
			this.winnerCheack(redTurn, x, y);
			return -1;		
		}
	}
	
	private boolean isColumnFull(int x) {
		if (this.circleCounterInColumn[x] < this.NUM_OF_ROWS)
			return false;
		else
			return true;
	}
	
	private void winnerCheack(boolean redTurn, int x, int y) {
		if (redTurn) { 
			if (count(this.redCircles, x, y) >= 4)
				winner = "RED";
		}
		else {
			if (count(this.blueCircles, x, y) >= 4)
				winner = "BLUE";	
		}		
	}

	// return the max count of same color
	private int count(int[][] playerStatus, int x, int y) {	
				
		int left =0 , right = 0, up = 0, down = 0, leftUp = 0, 
				leftDown = 0, rightUp = 0, rightDown = 0;
		
		if (x > 0)
			left = counter(playerStatus, x, y, -1, 0);
		if (x < this.NUM_OF_COLUMNS-1)
			right = counter(playerStatus, x, y, 1, 0);
		if (y < this.NUM_OF_ROWS-1)
			up = counter(playerStatus, x, y, 0, 1);
		if (y > 0)
			down = counter(playerStatus, x, y, 0, -1);
		if ((x > 0) && (y < this.NUM_OF_ROWS-1))
			leftUp = counter(playerStatus, x, y, -1, 1);
		if ((x > 0) && (y > 0))
			leftDown = counter(playerStatus, x, y, -1, -1);
		if ((y < this.NUM_OF_ROWS-1) && (x < this.NUM_OF_COLUMNS-1))
			rightUp = counter(playerStatus, x, y, 1, 1);
		if ((x < this.NUM_OF_COLUMNS-1) && (y > 0))
			rightDown = counter(playerStatus, x, y, 1, -1);
				
		int row = 1 + left + right;
		int column = 1 + up + down;
		int diagonal1 = 1 + leftUp + rightDown;
		int diagonal2 = 1 + leftDown + rightUp;

		return Math.max(Math.max(row,column), Math.max(diagonal2, diagonal1));		
	}
	
	// count by nextX and nextY
	private int counter(int[][] playerStatus, int x, int y, int nextX, int nextY) {
		
		int inf = Integer.MAX_VALUE;
		int minX = -inf, maxX = inf, minY = -inf, maxY = inf;
		if (nextX > 0)
			maxX = this.NUM_OF_COLUMNS-1;
		if (nextX < 0)
			minX = 0;
		if (nextY > 0)
			maxY = this.NUM_OF_ROWS-1;
		if (nextY < 0)
			minY = 0;
		
		// count +1 if the next if same color
		if ((x > minX) && (x < maxX) && (y > minY) && (y < maxY)) {
			if (playerStatus[x + nextX][y + nextY] == 1)
				return 1 + counter(playerStatus, x + nextX, y + nextY, nextX, nextY);
			else
				return 0;
		}
		else
			return 0;
	}	
}
