package pakete;
import com.qqwing.*;
import java.util.Arrays;

public class Driver {
	public static void main(String args[]) {
		QQWing qq = new QQWing();
		qq.generatePuzzle();
		qq.printPuzzle();
		Sudoku s = new Sudoku(qq.getPuzzle());
		Chromosome cromosoma = new Chromosome(s);
		
		for(int i = 0; i < 9; i++) {
			System.out.println(Arrays.toString(cromosoma.getsudokuchromosome2d()[i]));
		}
	}
}
