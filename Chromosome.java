package pakete;

import java.util.ArrayList;
import java.util.Random;

public class Chromosome{
	
	
	private int[] chromosome;
	private int[] sudoku;
	private int[] sudokupluschromosome;
	private Node[][] sudokupluschromosome2d;
	private Sudoku sudo;
	private Random rnd = new Random();
	
	public void sudokupluschromosome2bidimensional(){//generate the matrix nodes from SUDOKUPLUSCHROMOSOME
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
		for(int i = 0; i<9 && !exit; i++) {
			if(this.sudokupluschromosome2d[i][n.getCol()].getValue()==n.getValue()
					&& i!=n.getFil())
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
	
	private int countZeroes(int[] s) { //size of the CHROMOSOME array
		int zeroCount = 0;
		for(int i = 0; i < s.length; i++) {
			if(s[i] == 0) {
				zeroCount++;
			}
		}
		return zeroCount;
	}
	
	public Chromosome(Sudoku s) { //creates the CHROMOSOME array(no lo rellena), asgina el sudoku s a sudo y asigna el SUDOKU array a s.get
		chromosome = new int[countZeroes(s.getSudoku())];
		sudo = s;
		sudoku = s.getSudoku();
		joinsudokuchromosome();
		sudokupluschromosome2bidimensional();
	}
	
	public int[] getChromosome() {
		return chromosome;
	}
	
	public void setSudokuchromosome(int[] arr) {
		this.sudokupluschromosome = arr; 
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
	
	public void joinsudokuchromosome() {//Crea el SUDOKUPLUSCHROMOSOME a partir de SUDOKU array y CHROMOSOME array
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
	
	public void generateChromosome() {//generates the chromosome when its empty and fill it
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
	public Chromosome crossover(Chromosome p2) {
		Chromosome child = new Chromosome(sudo);
		int fil = rnd.nextInt(8);
		int[] parent1 = this.getsudokuchromosome();
		int[] parent2 = p2.getsudokuchromosome();
		int[] childArr = new int[parent2.length];
		int n = 8*fil;
		for(int i = 0; i < parent2.length; i++) {
			if(i<=n) {
				childArr[i] = parent1[i];
			}else {
				childArr[i] = parent2[i];
			}
		}
		child.setSudokuchromosome(childArr); //We write on the SUDOKUPLUSCHROMOSOME the crossover.
		child.sudokupluschromosome2bidimensional();
		return child;
	}
	public void mutate() {
		int n = 0;
		int[] childArr = this.getsudokuchromosome();
		int fil = rnd.nextInt(8);
		int aux1 = rnd.nextInt(8);
		while(this.sudoku[aux1] != 0) {
			aux1 = rnd.nextInt(8);
		}
		int aux2 = rnd.nextInt(8);
		while(aux2==aux1 || this.sudoku[aux2] !=0)
			aux2 = rnd.nextInt(8);
		for(int  i = 0; i < 9; i++) {
			n = childArr[aux1*fil];
			childArr[aux1*fil] = childArr[aux2*fil];
			childArr[aux2*fil] = n;
		}
		this.setSudokuchromosome(childArr);
		this.sudokupluschromosome2bidimensional();
	}
	
}