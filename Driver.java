package pakete;
import com.qqwing.*;

public class Driver {
	public static void main(String args[]) {
		QQWing qq = new QQWing();
		qq.generatePuzzle();
		qq.printPuzzle();
		int sudoku[] = qq.getPuzzle();
		for(int i = 0; i < sudoku.length; i++) {
			System.out.print(sudoku[i]);
		}
	}
}
