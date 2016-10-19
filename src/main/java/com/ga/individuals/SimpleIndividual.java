package com.ga.individuals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author user_pc
 *
 */
public class SimpleIndividual implements Individual {
	public static int GENE_SIZE = 50;
	
	protected int[] genes = new int[GENE_SIZE];
	protected int fitness = 0;
	
	public SimpleIndividual() {
		for (int j = 0; j < genes.length; j++) {
			genes[j] = ThreadLocalRandom.current().nextInt(2);
		}
		calculateFitness();
	}
	
	public SimpleIndividual(int[] genes) {
		this.genes = genes;
		calculateFitness();
	}

	/* (non-Javadoc)
	 * @see com.ga.individuals.Individual#createChildren(com.ga.individuals.SimpleIndividual)
	 */
	@Override
	public ArrayList<Individual> createChildren(Individual partner) {
		int randomGenePoint = ThreadLocalRandom.current().nextInt(GENE_SIZE + 1);
		int[] childGenes1 = new int[GENE_SIZE];
		int[] childGenes2 = new int[GENE_SIZE];
		
		ArrayList<Individual> children = new ArrayList<>();
		
		for (int i = 0; i < randomGenePoint; i++) {
			childGenes1[i] = this.getGenes()[i];
			childGenes2[i] = ((SimpleIndividual) partner).getGenes()[i];
		}
		
		for (int i = randomGenePoint; i < GENE_SIZE; i++) {
			childGenes1[i] = ((SimpleIndividual) partner).getGenes()[i];
			childGenes2[i] = this.getGenes()[i];
		}
		
		mutateGenes(childGenes1);
		mutateGenes(childGenes2);
		
		children.add(new SimpleIndividual(childGenes1));
		children.add(new SimpleIndividual(childGenes2));
		
		return children;
		
	}
	
	/**
	 * 
	 * @param genesToMutate
	 */
	void mutateGenes(int[] genesToMutate) {
		for (int i = 0; i < genesToMutate.length; i++) {
			if (ThreadLocalRandom.current().nextInt(1000) <= MUTATION_RATE) {
				genesToMutate[i] = genesToMutate[i] ^ 1;
			}
		}
	}

	/* (non-Javadoc)
	 * @see com.ga.individuals.Individual#getFitness()
	 */
	@Override
	public int getFitness() {
		return fitness;
	}
	
	public int[] getGenes() {
		return genes;
	}

	/**
	 * Loops through gene array and updates the individual's fitness
	 */
	protected void calculateFitness() {
		fitness = 0;
		for (int gene : genes) {
			if (gene == 1) {
				fitness++;
			}
		}
	}

	@Override
	public String toString() {
		return "Individual [genes=" + Arrays.toString(genes) + ", fitness=" + fitness + "]";
	}

}
