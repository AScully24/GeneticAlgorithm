package com.ga;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import com.ga.beans.PopulationGenerator;
import com.ga.environments.GAEnvironment;
import com.ga.individuals.Individual;
import com.ga.individuals.SimpleIndividual;

public class TestGAEnvironmentSimple extends AbstractTestGAEnvironment{

	GAEnvironment customEnv;
	PopulationGenerator eb;
	
	@Before
	public void init() {
		eb = new PopulationGenerator();
		gaEnv = eb.simplePopulation();
	}

	@Test
	public void testInitalCreation() {
		runInitalCreation(true);
	}

	@Test
	public void testMultipleGenerations() {
		runMultipleGenerations(50, 32,true);
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

			SimpleIndividual simpleIndividual = new SimpleIndividual(genes);
			population.add(simpleIndividual);
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
	
	public void runRouletteWheel() {
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

			SimpleIndividual simpleIndividual = new SimpleIndividual(genes);
			population.add(simpleIndividual);
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
	
	@Override
	public void setGaEnv() {
		this.gaEnv = customEnv;
	}
	
}