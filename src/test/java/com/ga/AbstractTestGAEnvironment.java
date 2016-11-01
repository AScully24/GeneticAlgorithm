package com.ga;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;

import com.ga.environments.GAEnvironment;
import com.ga.environments.RunResult;
import com.ga.individuals.Individual;
import com.ga.populations.ClassificationPopulation;

public abstract class AbstractTestGAEnvironment {

	protected GAEnvironment gaEnv;

	@Before
	public void init() {
		setGaEnv();
	}
	
	public void runInitalCreation(boolean checkResult) {
		for (Individual individual : gaEnv.getPopulation().getCurrentPopulation()) {
			System.out.println(individual.toString());
		}
		if (checkResult) {
			Assert.assertTrue(gaEnv.getPopulation().getCurrentPopulation().size() > 0);
		}
	}

	public ArrayList<RunResult> runMultipleGenerations(int runLimit, int generationCount, int targetFitness, boolean checkResult) {
		ArrayList<RunResult> runResults = gaEnv.multipleRuns(runLimit, generationCount);
		Individual fittestIndividual = runResults.get(0).getFittestIndividualInRun();
		
		for (RunResult runResult : runResults) {
			Individual currentIndividual = runResult.getFittestIndividualInRun();
			fittestIndividual  = ClassificationPopulation.compareTwoIndividuals(fittestIndividual, currentIndividual);
		}
		
		int maxFitness = fittestIndividual.getFitness();

		if (checkResult) {
			Assert.assertTrue(maxFitness == targetFitness);
		}
		return runResults;
	}

	public abstract void setGaEnv();

}