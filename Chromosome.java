package pakete;

public class Chromosome {
	
	private int[] chromosome;
	private int[] sudoku;
	private int[] sudokupluschromosome;
	private int[][] sudokupluschromosome2d;
	
	private void sudokupluschromosome2bidimensional() {
		sudokupluschromosome2d = new int[9][9];
		int rowCounter = 0;
		for(int i = 0; i < sudokupluschromosome.length; i++) {
			sudokupluschromosome2d[rowCounter][i%9] = sudokupluschromosome[i];
			if(i%9 == 8) {
				rowCounter++;
			}
		}
	}
	
	public int countcolum(int fil, int col) {
		int sol = 0;
		boolean exit = false;
		for(int j = 0; j<9 && !exit; j++) {
			if(this.sudokupluschromosome2d[fil][j]==this.sudokupluschromosome2d[fil][col] && j!=col)
				exit=true;
		}
		if(!exit) {
			sol=1;
		}
		return sol;
	}
	
	private int countZeroes(int[] s) {
		int zeroCount = 0;
		for(int i = 0; i < s.length; i++) {
			if(s[i] == 0) {
				zeroCount++;
			}
		}
		return zeroCount;
	}
	
	public Chromosome(Sudoku s) {
		chromosome = new int[countZeroes(s.getSudoku())];
		sudoku = s.getSudoku();
		joinsudokuchromosome();
		sudokupluschromosome2bidimensional();
	}
	
	public int[] getChromosome() {
		return chromosome;
	}
	
	public int fitnessFunction() {
		int count = 0;
		
		
		return count;
	}
	
	public int[] getsudokuchromosome() {
		return sudokupluschromosome;
	}
	
	public int[][] getsudokuchromosome2d() {
		return sudokupluschromosome2d;
	}
	
	private void joinsudokuchromosome() {
		sudokupluschromosome = new int[sudoku.length];
		int counterChromosome = 0;
		for(int i = 0; i < sudoku.length; i++) {
			if(sudoku[i] == 0) {
				sudokupluschromosome[i] = chromosome[counterChromosome];
				counterChromosome++;
			}else {
				sudokupluschromosome[i] = sudoku[i];
			}
		}
	}
}
