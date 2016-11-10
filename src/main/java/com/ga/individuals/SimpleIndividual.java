package com.ga.individuals;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import com.ga.genes.BinaryGene;
import com.ga.genes.Gene;

/**
 * @author user_pc
 *
 */
public class SimpleIndividual extends AbstractIndividual {

	private static final int GENE_MAX_VALUE = 2;

	public SimpleIndividual(int geneArraySize) {
		super(geneArraySize);
		fitness = calculateFitness();
	}

	public SimpleIndividual(ArrayList<Gene> genes, int mutationRate) {
		super(genes, mutationRate);
		mutateGenes();
		fitness = calculateFitness();
	}

	@Override
	protected ArrayList<Gene> createDefaultGenes() {
		ArrayList<Gene> newGenes = new ArrayList<Gene>();
		for (int i = 0; i < geneArraySize; i++) {
			BinaryGene binaryGene = new BinaryGene(ThreadLocalRandom.current().nextInt(GENE_MAX_VALUE));
			newGenes.add(binaryGene);
		}
		return newGenes;
	}

	@Override
	public void mutateGenes() {
		for (Gene gene : genes) {
			if (ThreadLocalRandom.current().nextInt(MUTATION_DIVIDER) <= mutationRate) {
				BinaryGene binaryGene = (BinaryGene) gene;
				binaryGene.setValue(binaryGene.getValue() ^ 1);
			}
		}
	}

	/**
	 * Loops through gene array and updates the individual's fitness
	 */
	@Override
	public int calculateFitness() {
		int newFitness = 0;
		for (Gene gene : genes) {
			if (((BinaryGene) gene).getValue() == 1) {
				newFitness++;
			}
		}
		return newFitness;
	}

	@Override
	protected Individual createChild(ArrayList<Gene> genes) {
		return new SimpleIndividual(genes, mutationRate);
	}

	@Override
	public int getFitness() {
		return fitness;
	}
}
