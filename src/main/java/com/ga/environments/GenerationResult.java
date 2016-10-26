package com.ga.environments;

import com.ga.individuals.Individual;

public class GenerationResult {
	
	Individual fittestIndividual;
	int highestFitness;
	int generationCount;
	boolean solutionFound;
	
	public GenerationResult(Individual fittestIndividual,  int generationCount, boolean solutionFound) {
		this.fittestIndividual = fittestIndividual;
		this.highestFitness = fittestIndividual.getFitness();
		this.generationCount = generationCount;
		this.solutionFound = solutionFound;
	}

	public int getGenerationCount() {
		return generationCount;
	}

	public void setGenerationCount(int generationCount) {
		this.generationCount = generationCount;
	}

	public Individual getFittestIndividual() {
		return fittestIndividual;
	}

	public void setFittestIndividual(Individual fittestIndividual) {
		this.fittestIndividual = fittestIndividual;
	}

	public int getHighestFitness() {
		return highestFitness;
	}

	public void setHighestFitness(int highestFitness) {
		this.highestFitness = highestFitness;
	}

	public boolean isSolutionFound() {
		return solutionFound;
	}

	public void setSolutionFound(boolean solutionFound) {
		this.solutionFound = solutionFound;
	}
}
