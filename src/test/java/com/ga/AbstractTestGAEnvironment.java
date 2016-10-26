package com.ga;

import org.junit.Assert;
import org.junit.Before;

import com.ga.environments.GAEnvironment;
import com.ga.individuals.Individual;

public abstract class AbstractTestGAEnvironment {


	protected GAEnvironment gaEnv;

	@Before
	public void init() {
		setGaEnv();
	}
	
	public void runInitalCreation(boolean checkResult) {
		for (Individual individual : gaEnv.getPopulation()) {
			System.out.println(individual.toString());
		}
		if (checkResult) {
			Assert.assertTrue(gaEnv.getPopulation().size() > 0);
		}
	}

	public void runMultipleGenerations(int generationCount, int targetFitness, boolean checkResult) {
		Individual fittestIndividual = gaEnv.multipleGenerations(1, generationCount, targetFitness);
		int maxFitness = fittestIndividual.getFitness();

		if (checkResult) {
			Assert.assertTrue(maxFitness == targetFitness);
		}
	}

	public abstract void setGaEnv();

}