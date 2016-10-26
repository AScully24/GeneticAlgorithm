package com.ga.beans;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ga.FileLoader;
import com.ga.data.Record;
import com.ga.environments.GAEnvironment;
import com.ga.individuals.ClassificationIndividual;
import com.ga.individuals.Individual;
import com.ga.individuals.SimpleIndividual;

@Configuration
public class PopulationGenerator {

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
	
	@Value("${target-fitness}")
	int targetFitness;
	
	
	@Bean
	public GAEnvironment simplePopulation() {
		ArrayList<Individual> population = new ArrayList<Individual>();

		for (int i = 0; i < populationSize; i++) {
			population.add(new SimpleIndividual(populationSize));
		}

		return new GAEnvironment(population, "Simple Population",32);
	}

	@Bean
	public GAEnvironment classificationPopulation() {
		ArrayList<Individual> population = new ArrayList<Individual>();
		ArrayList<Record> myRecords = FileLoader.loadBitFileToArrayList(fileName, bitInput - 1);
		for (int i = 0; i < populationSize; i++) {
			population.add(new ClassificationIndividual(bitInput * ruleCount, myRecords, bitInput));
		}
		return new GAEnvironment(population, problemName,targetFitness);
	}

	@Bean
	public GAEnvironment classificationFiveBitPopulation() {
		int bitSize = 6;
		ArrayList<Individual> population = new ArrayList<Individual>();
		ArrayList<Record> myRecords = FileLoader.loadBitFileToArrayList("data1.txt", bitSize - 1);
		for (int i = 0; i < populationSize; i++) {
			population.add(new ClassificationIndividual(bitSize * ruleCount, myRecords, bitSize));
		}
		return new GAEnvironment(population, "Five Bit Population",32);
	}

	@Bean
	public GAEnvironment classificationSixBitPopulation() {
		int bitSize = 7;
		ArrayList<Individual> population = new ArrayList<Individual>();
		ArrayList<Record> myRecords = FileLoader.loadBitFileToArrayList("data2.txt", bitSize - 1);
		for (int i = 0; i < populationSize; i++) {
			population.add(new ClassificationIndividual(bitSize * ruleCount, myRecords, bitSize));
		}
		return new GAEnvironment(population, "Six Bit Classification",64);
	}
}
