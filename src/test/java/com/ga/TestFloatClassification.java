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
import com.ga.environments.RunResult;
import com.ga.individuals.FloatIndividual;
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
		ArrayList<FloatRecord> correctRecords = individual.getCorrectRecords();
		FloatRecord record = correctRecords.get(0);
		
		System.out.println(individual);
		System.out.println(record);
		System.out.println(individual.getFitness());
		System.out.println();
		
	}

	@Test
	public void testMultipleGenerations() {
		ArrayList<RunResult> runResults = runMultipleGenerations(1, 1000, customEnv.getTargetFitness(), false);
		System.out.println(runResults);
		
		FloatIndividual fittestsIndividual = (FloatIndividual) runResults.get(0).getFittestIndividualInRun();
		Assert.assertTrue(fittestsIndividual.getFitness() == customEnv.getTargetFitness());
	}

	@Test
	public void testMultipleRuns() {
		ArrayList<RunResult> runResults = runMultipleGenerations(5, 10000, customEnv.getTargetFitness(), false);
		
		Individual fittestsIndividual = runResults.get(0).getFittestIndividualInRun();
		Assert.assertTrue(fittestsIndividual.getFitness() == customEnv.getTargetFitness());
	}
	
	@Test
	public void testFittestIndividual(){
		ArrayList<RunResult> runResults = runMultipleGenerations(1, 5000, customEnv.getTargetFitness(), false);
		
		//TODO: Test the fittest individual against the test data every X generations and graph the progress 
		
		FloatIndividual fittestsIndividual = (FloatIndividual) runResults.get(0).getFittestIndividualInRun();
		ArrayList<FloatRecord> testRecords = FileLoader.loadBitFileToArrayListFloat("test-data.txt", 6);
		
		int score = 0;
		for (FloatRecord record : testRecords) {
			int targetOutput = record.getOutput();
			int indOutput = fittestsIndividual.catergoriseFloatingRecord(record);
			if (targetOutput == indOutput) {
				score++;
			}
		}
		
		System.out.println("Fitness: " + fittestsIndividual.getFitness());
		float value = ((float)score/(float)testRecords.size()) * 100;
		Percentage percentage = Percentage.withPercentage(value);
		System.out.printf("Target Correct: %d\nActual Correct: %d\nPerc Correct: %s\n",testRecords.size(),score,percentage);	
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
