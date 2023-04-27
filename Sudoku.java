package uma.es.Leandro;
import com.qqwing.*;
import org.jgap.*;
import java.util.HashSet;
import java.util.Random;

public class Sudoku {
	
	private int [][]sudoku;
	private final int Rows = 9;
	private final int Cols = 9;
	public Sudoku() {
		sudoku = new int[Rows][Cols];
	}
	public void generateSudoku() {
		Random rand = new Random();
		for(int i = 0; i < Rows; i++) {
			for(int j = 0; j < Cols; j++) {
				rand.nextInt(1,9);
			}
		}
	}
	public boolean validPos(int row, int col) {
		boolean ok = true;
		for(int i = 0; i < 10 && ok; i++) {
			if(sudoku[row][col] == sudoku[i][col] && row!=i) {
				ok = false;
			}
		}
		for(int j = 0; j < 10 && ok; j++) {
			if(sudoku[row][col] == sudoku[row][j] && col!=j) {
				ok = false;
			}
		}
		if(row<3 && col<3){
			//sector1			
		}
		return false;
	}
	/*public static int[] computePuzzleByDifficulty(Difficulty d) {
		QQWing qq = new QQWing();
		qq.setRecordHistory(true);
		qq.setLogHistory(false);
		boolean go_on = true;
		while (go_on) {
			qq.generatePuzzle();
			qq.printPuzzle();
			qq.solve();
			Difficulty actual_d = qq.getDifficulty();
			System.out.println("Difficulty: "+actual_d.getName());
			go_on = !actual_d.equals(d);
		}
		int []puzzle = qq.getPuzzle();
		return puzzle;
	}
	
	//cheat by creating absurdly simple sudoku, with a given number of holes per row
	public static int[] computePuzzleWithNHolesPerRow(int numHolesPerRow) {
		Random rnd = new Random();
		QQWing qq = new QQWing();

		qq.setRecordHistory(true);
		qq.setLogHistory(false);
		qq.generatePuzzle();
		qq.solve();
		int []solution = qq.getSolution();
		HashSet<Integer> set = new HashSet<Integer>();
		for (int i=0; i<9; i++) {
			set.clear();
			while(set.size()<numHolesPerRow) {
				int n = rnd.nextInt(9);
				if (set.contains(n)) continue;
				set.add(n);
			}
			for (Integer hole_idx : set) {
				solution[i*9+hole_idx] = 0;
			}
		}
		qq.printPuzzle();
		return solution;
	}
	*/
}	
