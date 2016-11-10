package com.ga;

import org.assertj.core.data.Percentage;
import org.junit.Test;

import com.ga.individuals.FloatIndividual;
import com.ga.individuals.FloatIndividualTester;

public class TestFloatIndividualTester {
	private static final String SIMPLE_FILE_LOCATION = "Float-Genes-Simple.txt";
	private static final String COMPLEX_FILE_LOCATION = "Float-Genes-Complex.txt";
	
	@Test
	public void testFloatIndividualLoader(){
		FloatIndividual individual = FloatIndividualTester.loadIndividualFromFile(SIMPLE_FILE_LOCATION);
		System.out.println(individual);
	}
	
	@Test
	public void testLoadedFloatIndividualSimpleGenes(){
		FloatIndividual individual = FloatIndividualTester.loadIndividualFromFile(SIMPLE_FILE_LOCATION);
		printScoreDetails(individual,SIMPLE_FILE_LOCATION);
	}
	
	@Test
	public void testLoadedFloatIndividualComplexGenes(){
		FloatIndividual individual = FloatIndividualTester.loadIndividualFromFile(COMPLEX_FILE_LOCATION);
		System.out.println(individual);
		printScoreDetails(individual,SIMPLE_FILE_LOCATION);
	}

	@Test
	public void testFloatSimpleAndComplexPerformance(){
		FloatIndividual simpleIndividual = FloatIndividualTester.loadIndividualFromFile(SIMPLE_FILE_LOCATION);
		FloatIndividual complexIndividual = FloatIndividualTester.loadIndividualFromFile(COMPLEX_FILE_LOCATION);
		
		printScoreDetails(complexIndividual, COMPLEX_FILE_LOCATION);
		printScoreDetails(simpleIndividual, SIMPLE_FILE_LOCATION);
	}
	
	private void printScoreDetails(FloatIndividual individual,String name) {
		Percentage percentage = FloatIndividualTester.testDataPerformancePercentage(individual);
		int score = FloatIndividualTester.testDataPerformance(individual);
		System.out.println(name);
		System.out.println("Score: " + score);
		System.out.println("Percentage: " + percentage + "\n");
	}
	
}
