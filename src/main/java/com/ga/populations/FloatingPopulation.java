package com.ga.populations;

import java.util.ArrayList;

import com.ga.data.FloatingRecord;
import com.ga.individuals.FloatIndividual;
import com.ga.individuals.Individual;

public class FloatingPopulation extends AbstractPopulation{

	private ArrayList<FloatingRecord> trainingRecords;

	public FloatingPopulation(int ruleCount, int populationSize, int mutationRate, ArrayList<FloatingRecord> trainingRecords) {
		super(ruleCount,populationSize, mutationRate);
		this.trainingRecords = trainingRecords;
		generateNewRandomPopulation();
	}

	public FloatingPopulation(ArrayList<Individual> population, int mutationRate) {
		super(population, mutationRate);
	}
	
	@Override
	public void generateNewRandomPopulation() {
		ArrayList<Individual> populationArray = new ArrayList<Individual>();
		for (int i = 0; i < populationSize; i++) {
			FloatIndividual newIndividual = new FloatIndividual(ruleCount,trainingRecords);
			newIndividual.setMutationRate(mutationRate);
			populationArray.add(newIndividual);
		}
		currentPopulation = populationArray;
	}
}
