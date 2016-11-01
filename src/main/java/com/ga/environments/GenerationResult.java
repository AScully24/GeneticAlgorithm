package com.ga.environments;

import com.ga.individuals.Individual;

public class GenerationResult {

	Individual fittestIndividual;
	int highestFitness;
	int generationCount;
	boolean solutionFound;
	boolean isLastGeneration;

	public GenerationResult(Individual fittestIndividual, int generationCount, boolean solutionFound, boolean isLastGeneration) {
		this.fittestIndividual = fittestIndividual;
		this.generationCount = generationCount;
		this.solutionFound = solutionFound;
		highestFitness = fittestIndividual.getFitness();
		this.isLastGeneration = isLastGeneration;
	}

	public boolean isLastGeneration() {
		return isLastGeneration;
	}

	public void setLastGeneration(boolean isLastGeneration) {
		this.isLastGeneration = isLastGeneration;
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

	public int getGenerationCount() {
		return generationCount;
	}

	public void setGenerationCount(int generationCount) {
		this.generationCount = generationCount;
	}

	public boolean isSolutionFound() {
		return solutionFound;
	}

	public void setSolutionFound(boolean solutionFound) {
		this.solutionFound = solutionFound;
	}

	@Override
	public String toString() {
		return generationCount + "," + highestFitness + "," + solutionFound;
	}
}
