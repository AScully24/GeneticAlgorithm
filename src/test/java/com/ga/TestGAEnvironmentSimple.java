package com.ga;

import org.junit.Before;
import org.junit.Test;

import com.ga.beans.GAEnvironmentGenerator;
import com.ga.environments.GAEnvironment;

public class TestGAEnvironmentSimple extends AbstractTestGAEnvironment{

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
	
}