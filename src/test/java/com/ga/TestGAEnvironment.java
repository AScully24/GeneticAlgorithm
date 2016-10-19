package com.ga;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.ga.beans.EnvironmentBeans;
import com.ga.environments.GAEnvironment;
import com.ga.individuals.Individual;
import com.ga.individuals.SimpleIndividual;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class TestGAEnvironment {

	// @Autowired
	GAEnvironment gaEnv;

	@Before
	public void init() {
		EnvironmentBeans eb = new EnvironmentBeans();
		gaEnv = eb.simplePopulation();
	}

	@Test
	public void testInitalCreation() {
		for (Individual individual : gaEnv.getPopulation()) {
			System.out.println(individual.toString());
		}
		Assert.assertTrue(gaEnv.getPopulation().size() > 0);
	}

	@Test
	public void testMultipleGenerations() {
		int generationCount = 50;

		Individual overallFittesIndividual = gaEnv.getFittestsIndividual();

		for (int i = 0; i < generationCount; i++) {
			gaEnv.generateNewPopulation();
			Individual currentfittestsIndividual = gaEnv.getFittestsIndividual();
			System.out.println(currentfittestsIndividual.getFitness());

			if (currentfittestsIndividual.getFitness() > overallFittesIndividual.getFitness()) {
				overallFittesIndividual = currentfittestsIndividual;

			}
		}

		Assert.assertTrue(overallFittesIndividual.getFitness() == SimpleIndividual.GENE_SIZE);
	}

	@Test
	public void testRouletteWheel() {
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

		// TODO: Add assertion to make sure that the breakdown works.

	}
}