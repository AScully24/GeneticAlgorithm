package com.ga;

import org.junit.Before;
import org.junit.Test;

import com.ga.beans.PopulationGenerator;
import com.ga.environments.GAEnvironment;

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
		runMultipleGenerations(1, 1000,50, true);
	}
	
	@Override
	public void setGaEnv() {
		this.gaEnv = customEnv;
	}
	
}