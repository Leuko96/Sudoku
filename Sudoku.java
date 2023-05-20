package pakete;

import com.qqwing.QQWing;

public class Sudoku {
	
	private int[] sudoku;
	
	public Sudoku() {
		QQWing qq = new QQWing();
		qq.generatePuzzle();
		sudoku = qq.getPuzzle();
	}
	
	public int[] getSudoku() {
		return sudoku;
	}
}