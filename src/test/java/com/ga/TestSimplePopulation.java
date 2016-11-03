package com.ga;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.ga.beans.GAEnvironmentGenerator;
import com.ga.environments.GAEnvironment;
import com.ga.individuals.FloatIndividual;

public class TestSimplePopulation extends AbstractTestGAEnvironment{

	GAEnvironment customEnv;
	GAEnvironmentGenerator eb;
	
	@Before
	public void init() {
		eb = new GAEnvironmentGenerator();
		gaEnv = eb.simplePopulation();
	}

	@Test
	public void testInitalCreation() {
		runInitalCreation(true);
	}

	@Test
	public void testMultipleGenerations() {
		runMultipleGenerations(1, 1000,50, true);
	}
	
	@Override
	public void setGaEnv() {
		this.gaEnv = customEnv;
	}
	
	@Test
	public void testRandFloat(){
		boolean testStatus = true;
		for (int i = 0; i < 100; i++) {
			
			float randomInRange = FloatIndividual.randomInRange(0f, 0.1f);
			if (randomInRange > 0.1f) {
				testStatus = false;
				break;
			}
		}
		Assert.assertTrue(testStatus);
	}
}