package com.ga.individuals;

import java.util.ArrayList;

public interface Individual {

	// Mutation rate is based on MUTATION_RATE/MUTATION_DIVIDER. E.g. 100/1000 would mean a mutation rate of 10%
	public static int MUTATION_DIVIDER = 1000;

	public ArrayList<Individual> createChildren(Individual partner);

	public int getFitness();
	
	public void setMutationRate(int mutationRate);
	
	public int getMutationRate();

	void mutateGenes();
}