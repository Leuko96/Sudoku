package pakete;
import com.qqwing.*;
import java.util.Arrays;
import java.util.HashMap;

import org.jgap.InvalidConfigurationException;
import org.jgap.impl.CrossoverOperator;
import org.jgap.impl.MutationOperator;

public class Driver {
	private static Sudoku s = new Sudoku();
	public static void main(String args[]) throws InvalidConfigurationException {
		Chromosome cromosoma = new Chromosome(s);
		CrossoverOperator leandro = new CrossoverOperator();
		MutationOperator ana = new MutationOperator();
		for(int i = 0; i < 9; i++) {
			System.out.println(Arrays.toString(cromosoma.getsudokuchromosome2d()[i]));
		}
		cromosoma.generateChromosome();
		int cr[] = cromosoma.getChromosome();
		for(int i = 0; i < cr.length; i++) {
			System.out.print(cr[i] + " ");
		}
		
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
		
	}
	
	private Sudoku ruben() {
		HashMap<Chromosome,Integer> population = new HashMap<Chromosome, Integer>();
		Chromosome cro1 = new Chromosome(s);
		Chromosome cro2 = new Chromosome(s);
		int fitness = 0;
		while(fitness<162) {
			//	cro = new Chromosome(s);
			
			fitness = cro.fitnessFunction();
			population.put(cro, fitness);//No entiendo
		}
		return s;
	}
}
