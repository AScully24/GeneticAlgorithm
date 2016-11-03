package com.ga;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ga.data.BinaryRecord;
import com.ga.data.Record;
import com.ga.environments.GAEnvironment;
import com.ga.environments.GenerationResult;
import com.ga.environments.RunResult;
import com.ga.individuals.ClassificationIndividual;
import com.ga.individuals.Individual;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestBinaryClassification extends AbstractTestGAEnvironment {

	@Autowired
	@Qualifier("binaryGAEnvironment")
	GAEnvironment customEnv;

	@Test
	public void testInitalCreation() {
		runInitalCreation(true);
	}

	@Test
	public void testMultipleGenerations() {
		ArrayList<RunResult> runResults = runMultipleGenerations(1, 100000, customEnv.getTargetFitness(), false);
		System.out.println(runResults);
		Individual fittestsIndividual = runResults.get(0).getFittestIndividualInRun();

		ArrayList<BinaryRecord> fittestRecords = ((ClassificationIndividual) fittestsIndividual).genesToRecordArrayList();

		for (Record record : fittestRecords) {
			System.out.println(record);
		}
		Assert.assertTrue(fittestsIndividual.getFitness() == customEnv.getTargetFitness());
	}

	@Test
	public void testMultipleRuns() {
		ArrayList<RunResult> runResults = runMultipleGenerations(20, 10000, customEnv.getTargetFitness(), false);

		Individual fittestsIndividual = runResults.get(0).getFittestIndividualInRun();
		for (RunResult runResult : runResults) {
			System.out.println(runResult.toString());
			for (GenerationResult generationResult : runResult.getGenerationResults()) {
				System.out.println(runResult.toString() + "," + generationResult.toString());
			}
		}

		ArrayList<BinaryRecord> fittestRecords = ((ClassificationIndividual) fittestsIndividual).genesToRecordArrayList();
		for (Record record : fittestRecords) {
			System.out.println(record);
		}
		Assert.assertTrue(fittestsIndividual.getFitness() == customEnv.getTargetFitness());
	}

	@Override
	public void setGaEnv() {
		this.gaEnv = customEnv;
	}
}