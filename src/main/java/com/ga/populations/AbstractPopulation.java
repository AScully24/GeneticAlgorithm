package com.ga.populations;

import java.util.ArrayList;

import com.ga.individuals.Individual;

public abstract class AbstractPopulation implements Population {

	protected ArrayList<Individual> currentPopulation;
	protected PopulationData data = new PopulationData();

	public AbstractPopulation() {
		super();
	}

	public AbstractPopulation(int ruleCount, int populationSize, int mutationRate) {
		super();
		this.data.setGeneSize(ruleCount);
		this.data.setPopulationSize(populationSize);
		this.data.setMutationRate(mutationRate);
	}

	public AbstractPopulation(ArrayList<Individual> population, int mutationRate) {
		this.currentPopulation = population;
		this.data.setMutationRate(mutationRate);
	}

	@Override
	public double getPopulationTotalFitness(ArrayList<Individual> popToCheck) {
		double sum = 0;
		for (Individual individual : popToCheck) {
			sum += individual.getFitness();
		}
		return sum;
	}

	@Override
	public abstract void generateNewRandomPopulation();

	@Override
	public double getCurrentPopulationAverageFitness() {
		double count = 0;
		double sum = 0;

		for (Individual individual : currentPopulation) {
			count++;
			sum += individual.getFitness();
		}
		return (sum / count);
	}

	@Override
	public Individual getFittestIndividual() {
		Individual fittestIndividual = currentPopulation.get(0);
		for (Individual individual : currentPopulation) {
			if (individual.getFitness() >= fittestIndividual.getFitness()) {
				fittestIndividual = individual;
			}
		}
		return fittestIndividual;
	}

	@Override
	public Individual getWeakestIndividual() {
		Individual weakestIndividual = currentPopulation.get(0);
		for (Individual individual : currentPopulation) {
			if (individual.getFitness() <= weakestIndividual.getFitness()) {
				weakestIndividual = individual;
			}
		}
		return weakestIndividual;
	}
	
	public static Individual compareTwoIndividuals(Individual one, Individual two) {

		if (one.getFitness() > two.getFitness()) {
			return one;
		}
		return two;
	}

	@Override
	public ArrayList<Individual> getCurrentPopulation() {
		return currentPopulation;
	}

	@Override
	public void setCurrentPopulation(ArrayList<Individual> population) {
		this.currentPopulation = population;
	}

	public int getGeneSize() {
		return data.getGeneSize();
	}

	public String getProblemName() {
		return data.getProblemName();
	}

	public PopulationData getData() {
		return data;
	}

	@Override
	public void setPopulationData(PopulationData data) {
		this.data = data;
	}

	@Override
	public PopulationData getPopulationData() {
		return data;
	}

}
