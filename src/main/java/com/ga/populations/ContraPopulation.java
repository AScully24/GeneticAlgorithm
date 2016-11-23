package com.ga.populations;

import java.util.ArrayList;

import javax.annotation.PostConstruct;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.ga.FileLoader;
import com.ga.data.ContraRecord;
import com.ga.individuals.ContraIndividual;
import com.ga.individuals.Individual;

@Component
@ConfigurationProperties(prefix = "contra")
public class ContraPopulation extends AbstractPopulation {

	ArrayList<ContraRecord> trainingRecords;
	String fileName;
	int bitInput;

	public ContraPopulation() {
		super();
	}

	public ContraPopulation(int ruleCount, int bitInput, int populationSize, int mutationRate, ArrayList<ContraRecord> trainingRecords) {
		super(ruleCount, populationSize, mutationRate);
		this.bitInput = bitInput;
		this.trainingRecords = trainingRecords;
		generateNewRandomPopulation();
	}

	public ContraPopulation(ArrayList<Individual> population, int mutationRate) {
		super(population, mutationRate);
	}

	@PostConstruct
	public void init() {
		trainingRecords = FileLoader.loadContraFileToArrayList(fileName, bitInput - 1, ",");
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
		for (int i = 0; i < data.getPopulationSize(); i++) {
			ContraIndividual newIndividual = new ContraIndividual(bitInput * data.getGeneSize(), trainingRecords, bitInput);
			newIndividual.setMutationRate(data.getMutationRate());
			newIndividual.setCorrectRecords(trainingRecords);
			populationArray.add(newIndividual);
		}
		currentPopulation = populationArray;
	}

	public ArrayList<ContraRecord> getTrainingRecords() {
		return trainingRecords;
	}

	public void setTrainingRecords(ArrayList<ContraRecord> trainingRecords) {
		this.trainingRecords = trainingRecords;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public int getBitInput() {
		return bitInput;
	}

	public void setBitInput(int bitInput) {
		this.bitInput = bitInput;
	}
	
	
	
}
