package com.ga;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.ga.beans.PopulationGenerator;
import com.ga.data.Record;
import com.ga.environments.GAEnvironment;
import com.ga.individuals.Individual;
import com.ga.individuals.SimpleIndividual;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class TestGAEnvironmentSimple {

	// @Autowired
	GAEnvironment gaEnv;
	PopulationGenerator eb;
	
	@Before
	public void init() {
		eb = new PopulationGenerator();
		gaEnv = eb.simplePopulation();
	}

	@Test
	public void testInitalCreation() {
		for (Individual individual : gaEnv.getPopulation()) {
			System.out.println(individual.toString());
		}
		Assert.assertTrue(gaEnv.getPopulation().size() > 0);
	}

	@Test
	public void testMultipleGenerations() {
		int generationCount = 50;

		Individual fittestIndividual = gaEnv.multipleGenerations(generationCount);

		int targetGeneSize = ((SimpleIndividual) fittestIndividual).getGeneSize();
		int maxGeneSize = fittestIndividual.getFitness();
		Assert.assertTrue(maxGeneSize == targetGeneSize);
	}

	@Test
	public void testRouletteWheel() {
		ArrayList<Individual> population = new ArrayList<Individual>();

		// Create a fake population
		for (int i = 0; i < 10; i++) {

			int[] genes = new int[50];

			for (int j = 0; j < genes.length; j++) {
				genes[j] = 0;
			}

			for (int j = 0; j < i; j++) {
				genes[j] = 1;
			}

			population.add(new SimpleIndividual(genes));
		}

		ArrayList<Individual> rw = gaEnv.createRouletteWheel(population);

		for (Individual individual : population) {
			System.out.println(individual.toString());
		}

		System.out.println("\n\n\n\n");

		for (Individual individual : rw) {
			System.out.println(individual.getFitness());
		}
		// TODO: Add assertion to make sure that the breakdown works.
	}
	
	@Test
	public void testFileReader(){
		eb.correctRecords();
	}
	
	@Test
	public void genesToRecordArrayList() {
		ArrayList<Record> records = new ArrayList<>();
		int[] genes = new int[60];
		for (int i = 0; i < genes.length; i++) {
			genes[i] = i;
		}
		
		for (int i = 0; i < genes.length; i+=6) {
			int[] input = new int[5];
			for (int j = 0; j < input.length; j++) {
				input[j] = genes[i+j];
			}
			int output = genes[i+5];
			Record newRecord = new Record();
			newRecord.setInput(input);
			newRecord.setOutput(output);
			records.add(newRecord);
		}
		
		for (Record record : records) {
			System.out.println(record.toString());
		}
	}
	
}