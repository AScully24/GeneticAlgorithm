package com.ga.individuals;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import com.ga.genes.Gene;

public abstract class AbstractIndividual implements Individual {

	protected ArrayList<Gene> genes;
	protected int geneArraySize;
	protected int fitness;
	
	//TODO: Get this to set via a property value
	protected int mutationRate=10;

	public AbstractIndividual(int geneArraySize) {
		this.geneArraySize = geneArraySize;
		genes = createDefaultGenes();
	}

	public AbstractIndividual(ArrayList<Gene> genes) {
		this.genes = genes;
		this.geneArraySize = genes.size();
	}
	
	public ArrayList<Individual> createChildren(Individual partner){
		int randomGenePoint = ThreadLocalRandom.current().nextInt(genes.size() + 1);
		ArrayList<Gene> childGenes = new ArrayList<Gene>();

		ArrayList<Individual> children = new ArrayList<>();

		for (int i = 0; i < randomGenePoint; i++) {
			childGenes.add(this.genes.get(i).copyGene());
		}

		for (int i = randomGenePoint; i < genes.size(); i++) {
			Gene gene = ((AbstractIndividual) partner).getGenes().get(i);
			childGenes.add(gene.copyGene());
		}

		mutateGenes(childGenes);

		children.add(createChild(childGenes));
		
		return children;

	}
	
	protected abstract Individual createChild(ArrayList<Gene> childGenes);

	protected abstract void mutateGenes(ArrayList<Gene> childGenes);
	
	/**
	 * Loops through gene array and updates the individual's fitness
	 */
	protected abstract void calculateFitness();
	
	protected abstract ArrayList<Gene>createDefaultGenes();
	
	@Override
	public int getFitness() {
		calculateFitness();
		return fitness;
	}
		
	public ArrayList<Gene> getGenes() {
		return genes;
	}

	@Override
	public String toString() {
		return "AbstractIndividual [genes=" + genes + ", geneArraySize=" + geneArraySize + ", fitness=" + getFitness() + "]";
	}

}
