package com.ga.beans;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ga.FileLoader;
import com.ga.data.BinaryRecord;
import com.ga.environments.GAEnvironment;
import com.ga.individuals.Individual;
import com.ga.individuals.SimpleIndividual;
import com.ga.populations.ClassificationPopulation;
import com.ga.populations.Population;

@Configuration
public class GAEnvironmentGenerator {

	@Value("${problem-name}")
	String problemName;
	
	@Value("${file-name}")
	String fileName;
	
	@Value("${rule-count}")
	int ruleCount;
	
	@Value("${bit-input}")
	int bitInput;
	
	@Value("${population-size}")
	int populationSize;
	
//	@Value("${target-fitness}")
//	int targetFitness;

	@Value("${mutation-rate}")
	int mutationRate;
	
	@Bean
	public GAEnvironment simplePopulation() {		
		ArrayList<Individual> populationArray = new ArrayList<Individual>();
		populationSize = 50;
		for (int i = 0; i < populationSize; i++) {
			populationArray.add(new SimpleIndividual(populationSize));
		}
		Population population = new ClassificationPopulation(populationArray, mutationRate);
		return new GAEnvironment(population, "Simple Population",50);
	}

	@Bean
	public GAEnvironment classificationPopulation() {
		ArrayList<BinaryRecord> trainingRecords = FileLoader.loadBitFileToArrayList(fileName, bitInput - 1);
		Population population = new ClassificationPopulation(ruleCount, bitInput, populationSize, mutationRate, trainingRecords);
		return new GAEnvironment(population, problemName,trainingRecords.size());
	}
	
//	@Bean
//	public GAEnvironment floatingPopulation() {
//		ArrayList<FloatingRecord> trainingRecords = FileLoader.loadBitFileToArrayListFloat("data3.txt", 6);
//		FloatingPopulation population = new FloatingPopulation(ruleCount, populationSize, mutationRate, trainingRecords);
//		return new GAEnvironment(population, problemName,trainingRecords.size());
//	}
}
