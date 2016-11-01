package com.ga.environments;

import java.util.ArrayList;

import com.ga.individuals.Individual;
import com.ga.populations.ClassificationPopulation;

public class RunResult {
	String problemName;
	int runNumber;
	ArrayList<GenerationResult> generationResults;
	
	public RunResult(String problemName, int runNumber, ArrayList<GenerationResult> generationResults) {
		this.problemName = problemName;
		this.runNumber = runNumber;
		this.generationResults = generationResults;
	}
	
	public Individual getFittestIndividualInRun(){
		Individual fittestIndividual = generationResults.get(0).getFittestIndividual();
		
		for (GenerationResult generationResult : generationResults) {
			Individual currentIndividual = generationResult.getFittestIndividual();
			fittestIndividual = ClassificationPopulation.compareTwoIndividuals(fittestIndividual, currentIndividual);
		}
		
		return fittestIndividual;
	}
	
	public GenerationResult getMostRecentGeneration(){
		GenerationResult returnGen = null;
		for (GenerationResult generationResult : generationResults) {
			if (generationResult.isLastGeneration()) {
				returnGen = generationResult;
			}
		}
		return returnGen;
	}
	
	

	public ArrayList<GenerationResult> getGenerationResults() {
		return generationResults;
	}

	public void setGenerationResults(ArrayList<GenerationResult> generationResults) {
		this.generationResults = generationResults;
	}

	@Override
	public String toString() {
		return problemName + "," + runNumber + "," + getMostRecentGeneration();
	}
	
}
