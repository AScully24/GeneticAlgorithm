package com.ga.beans;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.ga.environments.GAEnvironment;
import com.ga.individuals.Individual;
import com.ga.individuals.SimpleIndividual;
import com.ga.populations.ClassificationPopulation;
import com.ga.populations.FloatPopulation;
import com.ga.populations.Population;

@Configuration
@ComponentScan("com.ga.populations")
public class GAEnvironmentGenerator {

	@Autowired
	FloatPopulation floatPopulation;

	@Autowired
	ClassificationPopulation classificationPopulation;

	@Bean
	public GAEnvironment simplePopulation() {
		ArrayList<Individual> populationArray = new ArrayList<Individual>();
		int populationSize = 50;
		for (int i = 0; i < populationSize; i++) {
			populationArray.add(new SimpleIndividual(populationSize));
		}
		Population population = new ClassificationPopulation(populationArray, 10);
		return new GAEnvironment(population, "Simple Population", 50);
	}

	// TODO: Allow loading of both classification and float populations
	@Bean
	public GAEnvironment binaryGAEnvironment() {
		return new GAEnvironment(classificationPopulation, classificationPopulation.getProblemName(), classificationPopulation.getTrainingRecords().size());
	}

	@Bean
	public GAEnvironment floatGAEnvironment() {
		return new GAEnvironment(floatPopulation, floatPopulation.getProblemName(), floatPopulation.getTrainingRecords().size());
	}
}
