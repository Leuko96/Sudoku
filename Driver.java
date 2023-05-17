package pakete;
import com.qqwing.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

import org.jgap.IChromosome;
import org.jgap.InvalidConfigurationException;
import org.jgap.impl.CrossoverOperator;
import org.jgap.impl.MutationOperator;

public class Driver {
	private static Sudoku s = new Sudoku();
	public static void main(String args[]) throws InvalidConfigurationException {
		Chromosome cromosoma = new Chromosome(s);
		cromosoma.generateChromosome();
		for(int i = 0; i < 9; i++) {
			System.out.println(Arrays.toString(cromosoma.getsudokuchromosome2d()[i]));
		}
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
		System.out.println();
		System.out.println();
		
		for(int i = 0; i < GeneticAlgorithm().getsudokuchromosome().length; i++) {
			System.out.print((GeneticAlgorithm().getsudokuchromosome()[i]));
			System.out.print(" ");
		}
		
	}
	
	private static Chromosome GeneticAlgorithm() {
		//HashMap<Chromosome,Integer> population = new HashMap<Chromosome, Integer>();
		Random rnd = new Random();
		ArrayList<Chromosome> population = new ArrayList<Chromosome>();
		ArrayList<Integer> weights = new ArrayList<Integer>();
		ArrayList<Chromosome> population2 = new ArrayList<Chromosome>(); //empty
		int n = 20;
		Chromosome parent1;
		Chromosome parent2;
		Chromosome child;
		Chromosome finall = new Chromosome(s);
		finall.generateChromosome();
		finall.sudokupluschromosome2bidimensional();
		while(finall.fitnessFunction()!=162) {
			while(n!=0) {
				Chromosome c = new Chromosome(s);
				c.generateChromosome();
				population.add(c);
				weights.add(c.fitnessFunction());
				n--;
			}
			n = 20;
			boolean exit = false;
			for(int i = 0; i < population.size() && !exit; i++) {
				parent1 = getRandomChromosome(population,weights);
				parent2 = getRandomChromosome(population,weights);
				child = parent1.crossover(parent2);
				if(rnd.nextInt(100) < 10) {
					child.mutate();
				}
				population2.add(child);
				if(child.fitnessFunction() == 162)
					exit = true;
				population = population2;
			}
			finall = best(population);
		}
		return finall;
	}
	public static Chromosome getRandomChromosome(ArrayList<Chromosome> chromosomeList, ArrayList<Integer> probabilityList) {

	        int totalProbability = 0;
	        for (int probability : probabilityList) {
	            totalProbability += probability;
	        }

	        Random random = new Random();
	        int randomValue = random.nextInt(totalProbability);

	        int cumulativeProbability = 0;
	        for (int i = 0; i < chromosomeList.size(); i++) {
	            cumulativeProbability += probabilityList.get(i);
	            if (randomValue < cumulativeProbability) {
	                return chromosomeList.get(i);
	            }
	        }
	        return chromosomeList.get(random.nextInt(19));
	        // This line should not be reached unless the probability list is empty.
	        //throw new IllegalStateException("Probability list cannot be empty.");
	    }
	public static Chromosome best(ArrayList<Chromosome> population) {
		Chromosome aux = population.get(0);
		for(int i = 1; i < population.size(); i++ ) {
			if(aux.fitnessFunction() < population.get(i).fitnessFunction())
				aux = population.get(i); 
		}
		return aux;
	}
}
