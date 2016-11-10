package com.ga;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ga.environments.GAEnvironment;
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
		Individual fittestsIndividual = runMultipleGenerations(1, 100000, customEnv.getTargetFitness(), false);
		
		Assert.assertTrue(fittestsIndividual.getFitness() == customEnv.getTargetFitness());
	}

	@Test
	public void testMultipleRuns() {
		Individual fittestsIndividual = runMultipleGenerations(5, 10000, customEnv.getTargetFitness(), false);

		Assert.assertTrue(fittestsIndividual.getFitness() == customEnv.getTargetFitness());
	}

	@Override
	public void setGaEnv() {
		this.gaEnv = customEnv;
	}
}