package com.ga.populations;

import java.util.ArrayList;

import com.ga.individuals.Individual;

public interface Population {

	/**
	 * @param popToCheck
	 *            TODO
	 * @return The sum of the fitness of all the individuals.
	 */
	double getPopulationTotalFitness(ArrayList<Individual> popToCheck);

	void generateNewRandomPopulation();

	/**
	 * @return The average fitness of all the individuals.
	 */
	double getCurrentPopulationAverageFitness();

	Individual getFittestIndividual();

	ArrayList<Individual> getCurrentPopulation();

	void setCurrentPopulation(ArrayList<Individual> population);

}