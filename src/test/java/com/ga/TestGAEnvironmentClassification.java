package com.ga;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ga.data.Record;
import com.ga.environments.GAEnvironment;
import com.ga.environments.RunResult;
import com.ga.individuals.ClassificationIndividual;
import com.ga.individuals.Individual;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestGAEnvironmentClassification extends AbstractTestGAEnvironment{

	@Autowired
	@Qualifier("classificationPopulation")
	GAEnvironment customEnv;
	
	@Test
	public void testInitalCreation() {
		runInitalCreation(true);
	}

	@Test
	public void testMultipleGenerations() {
		ArrayList<RunResult> runResults = runMultipleGenerations(1, 100000,customEnv.getTargetFitness(), false);
		System.out.println(runResults);
		Individual fittestsIndividual = gaEnv.getFittestIndividual();
		ArrayList<Record> fittestRecords = ((ClassificationIndividual)fittestsIndividual).genesToRecordArrayList();
		
		for (Record record : fittestRecords) {
			System.out.println(record);
		}
		Assert.assertTrue(gaEnv.getFittestIndividual().getFitness() == customEnv.getTargetFitness());
	}
	
	@Test
	public void testMultipleRuns() {
		runMultipleGenerations(1, 100000,customEnv.getTargetFitness(), false);
		Individual fittestsIndividual = gaEnv.getFittestIndividual();
		ArrayList<Record> fittestRecords = ((ClassificationIndividual)fittestsIndividual).genesToRecordArrayList();
		
		for (Record record : fittestRecords) {
			System.out.println(record);
		}
		Assert.assertTrue(gaEnv.getFittestIndividual().getFitness() == customEnv.getTargetFitness());
	}
	
	@Override
	public void setGaEnv() {
		this.gaEnv = customEnv;
	}
}