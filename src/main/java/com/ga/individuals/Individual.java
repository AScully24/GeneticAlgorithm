package com.ga.individuals;

import java.util.ArrayList;

public interface Individual {

	// Mutation rate is based on mutation_rate/1000. E.g. 100/1000 would mean a
	// mutation rate of 10%
	public static int MUTATION_RATE = 15;
	
	public ArrayList<Individual> createChildren(Individual partner);
	
	public int getFitness();

}