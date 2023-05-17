package pakete;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;

public class Chromosome {
	
	
	private int[] chromosome;
	private int[] sudoku;
	private int[] sudokupluschromosome;
	private Node[][] sudokupluschromosome2d;
	private Random rnd = new Random();
	
	public void sudokupluschromosome2bidimensional() {
		sudokupluschromosome2d = new Node[9][9];
		int aux = 0;
		int counter = 0;
		int subgrid = 0;
		int rowCounter = 0;
		boolean nextStep = false;
		boolean laststep = false;
		for(int j = 0; j < sudokupluschromosome.length; j++) {
			sudokupluschromosome2d[rowCounter][j%9] = new Node(rowCounter, j%9 , subgrid ,sudokupluschromosome[j]);
			if(j%9 == 8) {
				rowCounter++;
			}
			aux++;
			if(aux == 3 && !nextStep && !laststep) {
				subgrid = (subgrid + 1)%3;
				counter++;
				if(counter == 9) {
					nextStep = true;
					subgrid = 3;
					counter = 0;
				}
				aux = 0;
			}
			if(nextStep && aux == 3) {
				subgrid = (subgrid + 1)%6;
				if(subgrid == 0)
					subgrid = 3;
				counter++;
				aux = 0;
				if(counter == 9) {
					laststep = true;
					nextStep = false;
					subgrid = 6;
					counter = 0;
				}
			}
			
			if(laststep && aux == 3) {
				subgrid = (subgrid + 1)%9;
				counter++;
				if(subgrid == 0) {
					subgrid = 6;
				}
				aux = 0;
				if(counter == 9) {
					laststep = false;
				}
			}
			
		}
	}
	
	public int countcolum(Node n) { //Count of the colum
		int sol = 0;
		boolean exit = false;
		for(int j = 0; j<9 && !exit; j++) {
			if(this.sudokupluschromosome2d[n.getFil()][j]==this.sudokupluschromosome2d[n.getFil()][n.getCol()] && j!=n.getCol())
				exit=true;
		}
		if(!exit) {
			sol=1;
		}
		return sol;
	}
	
	public int countsubgrid(Node n) { //Count of the subgrid
		int sol = 0;
		boolean exit = false;
		for(int i = 0; i < 9 && !exit; i++) {
			for(int j = 0; j < 9 && !exit; j++) {
				if(this.sudokupluschromosome2d[i][j].getSubgrid() == n.getSubgrid() &&
				this.sudokupluschromosome2d[i][j].getValue() == n.getValue() && 
				(i!=n.getFil() && j!=n.getCol()))
					exit = true;
			}
		}
		
		if(!exit) {
			sol=1;
		}
		return sol;
	}
	
	private int countZeroes(int[] s) { //size of the chromosome
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
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				count += countsubgrid(sudokupluschromosome2d[i][j]) + countcolum(sudokupluschromosome2d[i][j]);
			}
		}
		return count;
	}
	
	public int[] getsudokuchromosome() {
		return sudokupluschromosome;
	}
	
	public Node[][] getsudokuchromosome2d() {
		return sudokupluschromosome2d;
	}
	
	public void joinsudokuchromosome() {
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
	
	public void generateChromosome() {
		int counter = 0;
		int rand = 0;
		for(int i = 0; i < 9; i++) {
			ArrayList<Integer> aux = new ArrayList<Integer>();
			for(int j = 1; j < 10; j++) {
				aux.add(j);
			}
			for(int j = 0; j < 9; j++) {
				aux.remove((Integer)sudokupluschromosome2d[i][j].getValue());
			}
			for(int j = 0; j < 9; j++) {
				if(sudokupluschromosome2d[i][j].getValue() == 0) {
					rand = rnd.nextInt(aux.size());
					chromosome[counter] = aux.get(rand);
					aux.remove(rand);
					counter++;
				}
			}
		}
	}
	
}