package com.ga.populations;

import java.util.ArrayList;

import com.ga.data.BinaryRecord;
import com.ga.individuals.ClassificationIndividual;
import com.ga.individuals.Individual;

public class ClassificationPopulation extends AbstractPopulation{
	
	private ArrayList<BinaryRecord> trainingRecords;
	protected int bitInput;
	
	public ClassificationPopulation(int ruleCount, int bitInput, int populationSize, int mutationRate, ArrayList<BinaryRecord> trainingRecords) {
		super(ruleCount, populationSize, mutationRate);
		this.bitInput = bitInput;
		this.trainingRecords = trainingRecords;
		generateNewRandomPopulation();
	}

	public ClassificationPopulation(ArrayList<Individual> population, int mutationRate) {
		super(population, mutationRate);
	}

	/* (non-Javadoc)
	 * @see com.ga.environments.Population#generateNewRandomPopulation()
	 */
	@Override
	public void generateNewRandomPopulation() {
		ArrayList<Individual> populationArray = new ArrayList<Individual>();
		for (int i = 0; i < populationSize; i++) {
			ClassificationIndividual newIndividual = new ClassificationIndividual(bitInput * ruleCount, trainingRecords, bitInput);
			newIndividual.setMutationRate(mutationRate);
			newIndividual.setCorrectRecords(trainingRecords);
			populationArray.add(newIndividual);
		}
		currentPopulation = populationArray;
	}

}