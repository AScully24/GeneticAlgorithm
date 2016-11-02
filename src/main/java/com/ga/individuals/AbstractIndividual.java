package com.ga.individuals;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import com.ga.genes.Gene;

public abstract class AbstractIndividual implements Individual {

	protected ArrayList<Gene> genes;
	protected int geneArraySize;
//	protected int fitness;
	protected int mutationRate;

	public AbstractIndividual(int geneArraySize) {
		this.geneArraySize = geneArraySize;
		genes = createDefaultGenes();
	}

	public AbstractIndividual(ArrayList<Gene> genes) {
		this.genes = genes;
		this.geneArraySize = genes.size();
	}

	public ArrayList<Individual> createChildren(Individual partner) {
		int randomGenePoint = ThreadLocalRandom.current().nextInt(genes.size() + 1);

		ArrayList<Individual> children = new ArrayList<>();
		
		ArrayList<Gene> childGenes1 = crossoverGenes(this, partner, randomGenePoint);
		ArrayList<Gene> childGenes2 = crossoverGenes(partner,this, randomGenePoint);
		
		mutateGenes(childGenes1);
		mutateGenes(childGenes2);

		Individual child1 = createChild(childGenes1);
		child1.setMutationRate(mutationRate);
		children.add(child1);

//		Individual child2 = createChild(childGenes1);
//		child2.setMutationRate(mutationRate);
//		children.add(child2);
//		
		return children;

	}

	private ArrayList<Gene> crossoverGenes(Individual parent1, Individual parent2, int randomGenePoint) {
		ArrayList<Gene> childGenes = new ArrayList<Gene>();
		for (int i = 0; i < randomGenePoint; i++) {
			childGenes.add(((AbstractIndividual) parent1).getGenes().get(i).copyGene());
		}

		for (int i = randomGenePoint; i < genes.size(); i++) {
			Gene gene = ((AbstractIndividual) parent2).getGenes().get(i);
			childGenes.add(gene.copyGene());
		}
		return childGenes;
	}

	protected abstract Individual createChild(ArrayList<Gene> childGenes);

	protected abstract void mutateGenes(ArrayList<Gene> childGenes);

	/**
	 * Loops through gene array and updates the individual's fitness
	 */
	protected abstract int calculateFitness();

	protected abstract ArrayList<Gene> createDefaultGenes();

	@Override
	public int getFitness() {
		return calculateFitness();
	}

	public ArrayList<Gene> getGenes() {
		return genes;
	}

	@Override
	public String toString() {
		return "AbstractIndividual [genes=" + genes + ", geneArraySize=" + geneArraySize + ", fitness=" + getFitness() + "]";
	}

	public int getMutationRate() {
		return mutationRate;
	}

	public void setMutationRate(int mutationRate) {
		this.mutationRate = mutationRate;
	}

}
