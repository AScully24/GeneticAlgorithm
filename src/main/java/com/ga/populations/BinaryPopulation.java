package com.ga.populations;

import java.util.ArrayList;

import javax.annotation.PostConstruct;

import com.ga.FileLoader;
import com.ga.data.BinaryRecord;
import com.ga.individuals.BinaryIndividual;
import com.ga.individuals.Individual;

public class BinaryPopulation extends AbstractPopulation {

	ArrayList<BinaryRecord> trainingRecords;
	String fileName;
	int bitInput;

	public BinaryPopulation() {
		super();
	}

	public BinaryPopulation(int ruleCount, int bitInput, int populationSize, int mutationRate, ArrayList<BinaryRecord> trainingRecords) {
		super(ruleCount, populationSize, mutationRate);
		this.bitInput = bitInput;
		this.trainingRecords = trainingRecords;
		generateNewRandomPopulation();
	}

	public BinaryPopulation(ArrayList<Individual> population, int mutationRate) {
		super(population, mutationRate);
	}

	@PostConstruct
	public void init() {
		trainingRecords = FileLoader.loadBitFileToArrayList(fileName, bitInput - 1);
		generateNewRandomPopulation();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ga.environments.Population#generateNewRandomPopulation()
	 */
	@Override
	public void generateNewRandomPopulation() {
		ArrayList<Individual> populationArray = new ArrayList<Individual>();
		for (int i = 0; i < populationSize; i++) {
			BinaryIndividual newIndividual = new BinaryIndividual(bitInput * geneSize, trainingRecords, bitInput);
			newIndividual.setMutationRate(mutationRate);
			newIndividual.setCorrectRecords(trainingRecords);
			populationArray.add(newIndividual);
		}
		currentPopulation = populationArray;
	}

	public ArrayList<BinaryRecord> getTrainingRecords() {
		return trainingRecords;
	}

	public void setTrainingRecords(ArrayList<BinaryRecord> trainingRecords) {
		this.trainingRecords = trainingRecords;
	}

	public int getBitInput() {
		return bitInput;
	}

	public void setBitInput(int bitInput) {
		this.bitInput = bitInput;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Override
	public String toString() {
		return "ClassificationPopulation [trainingRecords=" + trainingRecords + ", fileName=" + fileName + ", bitInput=" + bitInput + "]";
	}

}