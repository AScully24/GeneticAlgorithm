package com.ga.individuals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author user_pc
 *
 */
public class SimpleIndividual implements Individual {

	// Maximum value of the Gene minus 1 E.g. GENE_MAX_VALUE=4 means that an
	// individual gene can be a value between 0 and 3.
	public static final int GENE_MAX_VALUE = 2;
	protected int[] genes;
	protected int geneArraySize;
	protected int fitness;

	public SimpleIndividual(int geneArraySize) {
		this.geneArraySize = geneArraySize;
		genes = new int[geneArraySize];

		for (int j = 0; j < genes.length; j++) {
			genes[j] = ThreadLocalRandom.current().nextInt(GENE_MAX_VALUE);
		}
//		calculateFitness();
	}

	public SimpleIndividual(int[] genes) {
		this.genes = genes;
		this.geneArraySize = genes.length;
//		calculateFitness();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ga.individuals.Individual#createChildren(com.ga.individuals.
	 * SimpleIndividual)
	 */
	public ArrayList<Individual> createChildren(Individual partner) {
		int randomGenePoint = ThreadLocalRandom.current().nextInt(genes.length + 1);
		int[] childGenes1 = new int[genes.length];
		int[] childGenes2 = new int[genes.length];

		ArrayList<Individual> children = new ArrayList<>();

		for (int i = 0; i < randomGenePoint; i++) {
			childGenes1[i] = this.getGenes()[i];
			childGenes2[i] = ((SimpleIndividual) partner).getGenes()[i];
		}

		for (int i = randomGenePoint; i < genes.length; i++) {
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
			if (ThreadLocalRandom.current().nextInt(MUTATION_DIVIDER) <= MUTATION_RATE) {
				genesToMutate[i] = genesToMutate[i] ^ 1;
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ga.individuals.Individual#getFitness()
	 */
	@Override
	public int getFitness() {
		calculateFitness();
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

	public int getGeneSize() {
		return geneArraySize;
	}

	public int getGeneArraySize() {
		return geneArraySize;
	}

}
