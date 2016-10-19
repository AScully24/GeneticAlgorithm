package com.ga.beans;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ga.environments.GAEnvironment;
import com.ga.individuals.Individual;
import com.ga.individuals.SimpleIndividual;

@Configuration
public class EnvironmentBeans {
	
	static int POPULATION_SIZE = 50;
	
	@Bean
	public GAEnvironment simplePopulation() {
		ArrayList<Individual> population = new ArrayList<Individual>();
		
		for (int i = 0; i < POPULATION_SIZE; i++) {
			population.add(new SimpleIndividual());
		}
		
		return new GAEnvironment(population);
	}
}
