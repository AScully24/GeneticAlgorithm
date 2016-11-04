package com.ga;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ga.environments.GAEnvironment;
import com.ga.individuals.FloatIndividual;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestSimplePopulation extends AbstractTestGAEnvironment{

	@Autowired
	@Qualifier("simpleGAEnvironment")
	GAEnvironment customEnv;

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