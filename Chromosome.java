package pakete;

public class Chromosome {
	
	private int[] chromosome;
	private int[] sudoku;
	private int[] sudokupluschromosome;
	
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
