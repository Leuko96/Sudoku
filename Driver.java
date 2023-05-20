package pakete;


import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;


public class Driver {
	private static Sudoku s = new Sudoku();
	public static void main(String args[]) throws IOException {
		Chromosome cromosoma = new Chromosome(s);
		cromosoma.generateChromosome();
		cromosoma.joinsudokuchromosome();
		cromosoma.sudokupluschromosome2bidimensional();
		Node[][] sudo = cromosoma.getsudokuchromosome2d();
		
		System.out.println();
		System.out.println();
		
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				System.out.print(sudo[i][j].getValue() + " ");
			}
			System.out.println("");
		}
		System.out.println();
		System.out.println();
		GeneticAlgorythm sol = new GeneticAlgorythm(s);
		Chromosome solcr = sol.GeneticAlgorithm();
		for(int i = 0; i < solcr.getsudokuchromosome().length; i++) {
			System.out.print(solcr.getsudokuchromosome()[i]);
			System.out.print(" ");
		}
		solcr.sudokupluschromosome2bidimensional();
		System.out.println("\n\n");
		System.out.println("_Final_ \n");
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				System.out.print(solcr.getsudokuchromosome2d()[i][j].getValue() + " ");
			}
			System.out.println("");
		}
		//System.out.println(solcr.fitnessFunction());
		try (FileWriter myWriter = new FileWriter("output.txt")) {
			myWriter.write(toString(solcr.getsudokuchromosome2d()));
		}
	}
	public static String toString(Node[][] solution) {
		String sol = "";
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				sol+=solution[i][j].getValue() + " ";
			}
			sol+="\n";
		}
		return sol;
	}
}
	
