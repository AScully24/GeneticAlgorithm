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
		System.out.println(gaEnv.getProblemName());
		for (Individual individual : gaEnv.getPopulation().getCurrentPopulation()) {
			System.out.println(individual.toString());
		}
		if (checkResult) {
			Assert.assertTrue(gaEnv.getPopulation().getCurrentPopulation().size() > 0);
		}
	}

	public Individual runMultipleGenerations(int runLimit, int generationCount, int targetFitness, boolean checkResult) {
		Individual fittestIndividual = gaEnv.multipleRuns(runLimit, generationCount);
				
		int maxFitness = fittestIndividual.getFitness();
		System.out.println(fittestIndividual);
		if (checkResult) {
			Assert.assertTrue(maxFitness == targetFitness);
		}
		return fittestIndividual;
	}

	public abstract void setGaEnv();

}