package com.ga.populations;

import java.util.ArrayList;

import javax.annotation.PostConstruct;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.ga.FileLoader;
import com.ga.data.FloatRecord;
import com.ga.individuals.FloatIndividual;
import com.ga.individuals.Individual;

/**
 * @author user_pc
 *
 */
@Component
@ConfigurationProperties(prefix = "data3")
public class FloatPopulation extends AbstractPopulation {
	
	String fileName;// = "data3.txt";
	ArrayList<FloatRecord> trainingRecords;
	
	public FloatPopulation() {
		super();
	}

	public FloatPopulation(int ruleCount, int populationSize, int mutationRate, ArrayList<FloatRecord> trainingRecords) {
		super(ruleCount, populationSize, mutationRate);
		this.trainingRecords = trainingRecords;
		generateNewRandomPopulation();
	}

	@PostConstruct
	public void init() {
		trainingRecords = FileLoader.loadBitFileToArrayListFloat(fileName, 6);
		generateNewRandomPopulation();
	}

	public FloatPopulation(ArrayList<Individual> population, int mutationRate) {
		super(population, mutationRate);
	}

	@Override
	public void generateNewRandomPopulation() {
		ArrayList<Individual> populationArray = new ArrayList<Individual>();
		for (int i = 0; i < data.getPopulationSize(); i++) {
			FloatIndividual newIndividual = new FloatIndividual(data.getGeneSize(), trainingRecords);
			newIndividual.setMutationRate(data.getMutationRate());
			populationArray.add(newIndividual);
		}
		currentPopulation = populationArray;
	}

	public void setTrainingRecords(ArrayList<FloatRecord> trainingRecords) {
		this.trainingRecords = trainingRecords;
	}

	public ArrayList<FloatRecord> getTrainingRecords() {
		return trainingRecords;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}	
}
