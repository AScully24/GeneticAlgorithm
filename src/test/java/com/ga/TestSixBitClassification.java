package com.ga;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ga.environments.GAEnvironment;
import com.ga.genes.Gene;
import com.ga.individuals.BinaryIndividual;
import com.ga.individuals.Individual;
import com.ga.populations.Population;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestSixBitClassification extends AbstractTestGAEnvironment {

	@Autowired
	@Qualifier("sixBitGAEnvironment")
	GAEnvironment customEnv;
	
	@Autowired
	@Qualifier("createSixBitPopulation")
	Population population;
	
	@Test
	public void testInitalCreation() {
		runInitalCreation(true);
	}

	@Test
	public void testMultipleGenerations() {
		Individual fittestsIndividual = runMultipleGenerations(1, 100000, customEnv.getTargetFitness(), false);
		
		Assert.assertTrue(fittestsIndividual.getFitness() == customEnv.getTargetFitness());
	}

	@Test
	public void testMultipleRuns() {
		Individual fittestsIndividual = runMultipleGenerations(10, 10000, customEnv.getTargetFitness(), false);

		Assert.assertTrue(fittestsIndividual.getFitness() == customEnv.getTargetFitness());
	}
	
	@Test
	public void testCustomIndividual(){
		ArrayList<Gene> genes = FileLoader.geneLoaderBinary("data/Six-Bit-Genes.txt");
		BinaryIndividual individual = (BinaryIndividual) population.getFittestIndividual();
		individual.setGenes(genes);
		
		System.out.println(individual.calculateFitness());
		
	}

	@Override
	public void setGaEnv() {
		this.gaEnv = customEnv;
	}
}