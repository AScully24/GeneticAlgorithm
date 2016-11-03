package com.ga.populations;

import java.util.ArrayList;

import com.ga.individuals.Individual;

public abstract class AbstractPopulation implements Population {

	protected ArrayList<Individual> currentPopulation;
	// TODO: Change this name to represent the gene count instead of rule count.
	protected String problemName;
	protected int geneSize;
	protected int populationSize;
	protected int mutationRate;

	public AbstractPopulation() {
		super();
	}

	public AbstractPopulation(int ruleCount, int populationSize, int mutationRate) {
		super();
		this.geneSize = ruleCount;
		this.populationSize = populationSize;
		this.mutationRate = mutationRate;
	}

	public AbstractPopulation(ArrayList<Individual> population, int mutationRate) {
		this.currentPopulation = population;
		this.mutationRate = mutationRate;
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
		return geneSize;
	}

	public void setGeneSize(int geneSize) {
		this.geneSize = geneSize;
	}

	public int getPopulationSize() {
		return populationSize;
	}

	public void setPopulationSize(int populationSize) {
		this.populationSize = populationSize;
	}

	public int getMutationRate() {
		return mutationRate;
	}

	public void setMutationRate(int mutationRate) {
		this.mutationRate = mutationRate;
	}

	public String getProblemName() {
		return problemName;
	}

	public void setProblemName(String problemName) {
		this.problemName = problemName;
	}

}
