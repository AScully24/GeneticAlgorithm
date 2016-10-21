package com.ga;

import java.util.ArrayList;

import org.junit.Assert;

import com.ga.beans.PopulationGenerator;
import com.ga.environments.GAEnvironment;
import com.ga.individuals.Individual;
import com.ga.individuals.SimpleIndividual;

public abstract class AbstractTestGAEnvironment {

	GAEnvironment gaEnv;

	
	public void runInit() {
		PopulationGenerator eb = new PopulationGenerator();
		gaEnv = eb.simplePopulation();
	}
	
	public void runInitalCreation(GAEnvironment gaEnv) {
		for (Individual individual : gaEnv.getPopulation()) {
			System.out.println(individual.toString());
		}
		Assert.assertTrue(gaEnv.getPopulation().size() > 0);
	}

	public void runMultipleGenerations(GAEnvironment gaEnv) {
		int generationCount = 50;

		Individual fittestIndividual = gaEnv.multipleGenerations(generationCount);

		int targetGeneSize = ((SimpleIndividual) fittestIndividual).getGeneSize();
		int maxGeneSize = fittestIndividual.getFitness();
		Assert.assertTrue(maxGeneSize == targetGeneSize);
	}
	
	public void runRouletteWheel(GAEnvironment gaEnv) {
		ArrayList<Individual> population = new ArrayList<Individual>();

		// Create a fake population
		for (int i = 0; i < 10; i++) {

			int[] genes = new int[50];

			for (int j = 0; j < genes.length; j++) {
				genes[j] = 0;
			}

			for (int j = 0; j < i; j++) {
				genes[j] = 1;
			}

			population.add(new SimpleIndividual(genes));
		}

		ArrayList<Individual> rw = gaEnv.createRouletteWheel(population);

		for (Individual individual : population) {
			System.out.println(individual.toString());
		}

		System.out.println("\n\n\n\n");

		for (Individual individual : rw) {
			System.out.println(individual.getFitness());
		}
	}
}