package com.ga.individuals;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author user_pc
 *
 */
public class SimpleIndividual extends AbstractIndividual{

	// Maximum value of the Gene minus 1 E.g. GENE_MAX_VALUE=4 means that an
	// individual gene can be a value between 0 and 3.
	public static final int GENE_MAX_VALUE = 2;

	public SimpleIndividual(int geneArraySize) {
		super(geneArraySize, GENE_MAX_VALUE);
	}

	public SimpleIndividual(int[] genes) {
		super(genes);
	}
	
	/**
	 * 
	 * @param genesToMutate
	 */
	@Override
	protected void mutateGenes(int[] genesToMutate) {
		for (int i = 0; i < genesToMutate.length; i++) {
			if (ThreadLocalRandom.current().nextInt(MUTATION_DIVIDER) <= mutationRate) {
				genesToMutate[i] = genesToMutate[i] ^ 1;
			}
		}
	}

	/**
	 * Loops through gene array and updates the individual's fitness
	 */
	@Override
	protected void calculateFitness() {
		fitness = 0;
		for (int gene : genes) {
			if (gene == 1) {
				fitness++;
			}
		}
	}

	@Override
	protected AbstractIndividual createChild(int[] genes) {
		return new SimpleIndividual(genes);
	}
}
