package com.ga.individuals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public abstract class AbstractIndividual implements Individual {

	// Maximum value of the Gene minus 1 E.g. GENE_MAX_VALUE=4 means that an
	// individual gene can be a value between 0 and 3.
	protected int[] genes;
	protected int geneArraySize;
	protected int fitness;

	public AbstractIndividual(int geneArraySize, int geneMaxValue) {
		this.geneArraySize = geneArraySize;
		genes = new int[geneArraySize];

		for (int j = 0; j < genes.length; j++) {
			genes[j] = ThreadLocalRandom.current().nextInt(geneMaxValue);
		}
	}

	public AbstractIndividual(int[] genes) {
		this.genes = genes;
		this.geneArraySize = genes.length;
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
			childGenes2[i] = ((AbstractIndividual) partner).getGenes()[i];
		}

		for (int i = randomGenePoint; i < genes.length; i++) {
			childGenes1[i] = ((AbstractIndividual) partner).getGenes()[i];
			childGenes2[i] = this.getGenes()[i];
		}

		mutateGenes(childGenes1);
		mutateGenes(childGenes2);

		children.add(createChild(childGenes1));
		children.add(createChild(childGenes2));

		return children;

	}
	
	protected abstract AbstractIndividual createChild(int[] genes);

	protected abstract void mutateGenes(int[] genesToMutate);

	/**
	 * Loops through gene array and updates the individual's fitness
	 */
	protected abstract void calculateFitness();

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
	public int getGeneSize() {
		return geneArraySize;
	}

	public int getGeneArraySize() {
		return geneArraySize;
	}

	@Override
	public String toString() {
		return "Individual [genes=" + Arrays.toString(genes) + ", fitness=" + getFitness() + "]";
	}

}
