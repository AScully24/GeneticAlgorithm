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
import com.ga.data.FloatingRecord;
import com.ga.data.Record;
import com.ga.environments.GAEnvironment;
import com.ga.environments.RunResult;
import com.ga.individuals.ClassificationIndividual;
import com.ga.individuals.FloatingIndividual;
import com.ga.individuals.Individual;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestFloatPopulation extends AbstractTestGAEnvironment{

	@Autowired
	@Qualifier("floatingPopulation")
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
		ArrayList<RunResult> runResults = runMultipleGenerations(10, 10000, customEnv.getTargetFitness(), false);

		Individual fittestsIndividual = runResults.get(0).getFittestIndividualInRun();
		ArrayList<BinaryRecord> fittestRecords = ((ClassificationIndividual) fittestsIndividual).genesToRecordArrayList();

		// for (RunResult runResult : runResults) {
		// System.out.println(runResult.toString());
		// for (GenerationResult generationResult : runResult.getGenerationResults()) {
		// System.out.println(runResult.toString() + "," + generationResult.toString());
		// }
		// }

		for (Record record : fittestRecords) {
			System.out.println(record);
		}
		Assert.assertTrue(fittestsIndividual.getFitness() == customEnv.getTargetFitness());
	}
	
	@Test
	public void testFittestIndividual(){
		ArrayList<RunResult> runResults = runMultipleGenerations(1, 10000, customEnv.getTargetFitness(), false);

		FloatingIndividual fittestsIndividual = (FloatingIndividual) runResults.get(0).getFittestIndividualInRun();
		ArrayList<FloatingRecord> testRecords = FileLoader.loadBitFileToArrayListFloat("float-data.txt", 6);
		int score = 0;
		for (FloatingRecord record : testRecords) {
			int targetOutput = record.getOutput();
			int indOutput = fittestsIndividual.catergoriseFloatingRecord(record);
			if (targetOutput == indOutput) {
				
				score++;
			}
//			System.out.println(targetOutput + "," + indOutput);
		}
		
		System.out.printf("Target Correct: %d\nActual Correct: %d\nPerc Correct: %d\n",testRecords.size(),score,(score/testRecords.size() * 100));
		
	}

	@Override
	public void setGaEnv() {
		this.gaEnv = customEnv;
	}
}
