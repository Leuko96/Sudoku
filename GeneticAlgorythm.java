package pakete;

import java.util.ArrayList;
import java.util.Random;

public class GeneticAlgorythm {
	private static Sudoku s;
	public GeneticAlgorythm(Sudoku su) {
		s = su;
	}
	public Chromosome GeneticAlgorithm() {
		Random rnd = new Random();
		ArrayList<Chromosome> population = new ArrayList<Chromosome>();
		ArrayList<Integer> weights = new ArrayList<Integer>();
		ArrayList<Chromosome> population2 = new ArrayList<Chromosome>(); //empty
		int n = 20;
		boolean exit = false;
		Chromosome parent1;
		Chromosome parent2;
		Chromosome child;
		Chromosome finall = new Chromosome(s);
		finall.generateChromosome();
		finall.sudokupluschromosome2bidimensional();
		int limit = 20;
		boolean initial = false;
		while(finall.fitnessFunction()<162 && limit!=0) {
			while(n!=0 && !initial) {
				Chromosome c = new Chromosome(s);
				c.generateChromosome();
				c.joinsudokuchromosome();
				c.sudokupluschromosome2bidimensional();
				population.add(c);
				weights.add(c.fitnessFunction());
				n--;
			}
			initial = true;
			for(int i = 0; i < population.size() && !exit; i++) {
				parent1 = getRandomChromosome(population,weights);
				parent2 = getRandomChromosome(population,weights);
				child = parent1.crossover(parent2);
				if(rnd.nextInt(100) < 10) {
					child.mutate();
				}
				population2.add(child);
				//System.out.println("---------------------------------" + child.fitnessFunction());
				if(child.fitnessFunction() >= 162) {
					exit = true;
				}
			}
			population.clear();
			weights.clear();
			for(int i = 0; i < population2.size(); i++) {
				population.add(population2.get(i));
				weights.add(population.get(i).fitnessFunction());
			}
			population2.clear();
			finall = best(population);
			limit--;
		}
		return finall;
	}
	private Chromosome getRandomChromosome(ArrayList<Chromosome> chromosomeList, ArrayList<Integer> probabilityList) {

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
	private Chromosome best(ArrayList<Chromosome> population) {
		Chromosome aux = population.get(0);
		for(int i = 1; i < population.size(); i++ ) {
			if(aux.fitnessFunction() < population.get(i).fitnessFunction())
				aux = population.get(i); 
		}
		return aux;
	}
}
