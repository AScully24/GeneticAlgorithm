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
	}

	public SimpleIndividual(ArrayList<Gene> genes) {
		super(genes);
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
	
//	@Override
//	public ArrayList<Individual> createChildren(Individual partner){
//		int randomGenePoint = ThreadLocalRandom.current().nextInt(genes.size() + 1);
//		ArrayList<Gene> childGenes = new ArrayList<Gene>();
//
//		ArrayList<Individual> children = new ArrayList<>();
//
//		for (int i = 0; i < randomGenePoint; i++) {
//			Gene gene = this.genes.get(i);
//			childGenes.add(new BinaryGene(((BinaryGene) gene).getValue()));
//		}
//
//		for (int i = randomGenePoint; i < genes.size(); i++) {
//			Gene gene = ((AbstractIndividual) partner).getGenes().get(i);
//			childGenes.add(new BinaryGene(((BinaryGene) gene).getValue()));
//		}
//
//		mutateGenes(childGenes);
//
//		children.add(createChild(childGenes));
//		
//		return children;
//
//	}

	/**
	 * 
	 * @param genesToMutate
	 */
	@Override
	protected void mutateGenes(ArrayList<Gene> genesToMutate) {
		for (Gene gene : genesToMutate) {
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
	protected AbstractIndividual createChild(ArrayList<Gene> genes) {
		return new SimpleIndividual(genes);
	}
}
