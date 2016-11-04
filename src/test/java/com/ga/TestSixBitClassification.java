package com.ga;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ga.environments.GAEnvironment;
import com.ga.environments.RunResult;
import com.ga.individuals.Individual;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestSixBitClassification extends AbstractTestGAEnvironment {

	@Autowired
	@Qualifier("sixBitGAEnvironment")
	GAEnvironment customEnv;
	
	@Test
	public void testInitalCreation() {
		runInitalCreation(true);
	}

	@Test
	public void testMultipleGenerations() {
		ArrayList<RunResult> runResults = runMultipleGenerations(1, 100000, customEnv.getTargetFitness(), false);
		
		Individual fittestsIndividual = runResults.get(0).getFittestIndividualInRun();
		Assert.assertTrue(fittestsIndividual.getFitness() == customEnv.getTargetFitness());
	}

	@Test
	public void testMultipleRuns() {
		ArrayList<RunResult> runResults = runMultipleGenerations(20, 10000, customEnv.getTargetFitness(), false);

		Individual fittestsIndividual = runResults.get(0).getFittestIndividualInRun();
		Assert.assertTrue(fittestsIndividual.getFitness() == customEnv.getTargetFitness());
	}

	@Override
	public void setGaEnv() {
		this.gaEnv = customEnv;
	}
}