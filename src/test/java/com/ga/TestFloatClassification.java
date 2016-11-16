package com.ga;

import java.util.ArrayList;

import org.apache.commons.lang.math.FloatRange;
import org.assertj.core.data.Percentage;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ga.data.FloatRecord;
import com.ga.environments.GAEnvironment;
import com.ga.individuals.FloatIndividual;
import com.ga.individuals.FloatIndividualTester;
import com.ga.individuals.Individual;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestFloatClassification extends AbstractTestGAEnvironment{

	@Autowired
	@Qualifier("floatGAEnvironment")
	GAEnvironment customEnv;

	@Test
	public void testInitalCreation() {
		runInitalCreation(true);
	}
	
	@Test
	public void testInputComparison(){
		System.out.println();
		FloatIndividual individual = (FloatIndividual) customEnv.getPopulation().getFittestIndividual();
		ArrayList<FloatRecord> correctRecords = individual.getTrainingRecords();
		FloatRecord record = correctRecords.get(0);
		
		System.out.println(individual);
		System.out.println(record);
		System.out.println(individual.getFitness());
		System.out.println();
		
	}

	@Test
	public void testMultipleGenerations() {
		FloatIndividual fittestsIndividual = (FloatIndividual) runMultipleGenerations(1, 10000, customEnv.getTargetFitness(), false);
		Assert.assertTrue(fittestsIndividual.getFitness() == customEnv.getTargetFitness());
	}	

	@Test
	public void testMultipleRuns() {
		Individual fittestsIndividual = runMultipleGenerations(5, 10000, customEnv.getTargetFitness(), false);
		
		Assert.assertTrue(fittestsIndividual.getFitness() == customEnv.getTargetFitness());
	}
	
	@Test
	public void testFittestIndividual(){
		FloatIndividual fittestsIndividual = (FloatIndividual) runMultipleGenerations(5, 2000, customEnv.getTargetFitness(), false);
				
		System.out.println("Fitness: " + fittestsIndividual.getFitness());
		int score = FloatIndividualTester.testDataPerformance(fittestsIndividual);
		Percentage percentage = FloatIndividualTester.testDataPerformancePercentage(fittestsIndividual);
		int targetRecordSize = FloatIndividualTester.getTargetRecordSize();
		System.out.printf("Target Correct: %d\nActual Correct: %d\nPerc Correct: %s\n",targetRecordSize,score,percentage);	
		Assert.assertTrue(percentage.value > 90f);
	}

	@Override	
	public void setGaEnv() {
		this.gaEnv = customEnv;
	}
	
	@Test
	public void testFloatRange(){
		Float lowerTarget= 0.3f;
		Float upperTarget = 0.6f;
		Float value = 0.2f;
		FloatRange range = new FloatRange(lowerTarget,upperTarget);
		
		System.out.println(range.containsFloat(value));
	}
	
}