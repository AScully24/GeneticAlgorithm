package com.ga.environments;

import java.util.ArrayList;

import com.ga.individuals.Individual;

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
			fittestIndividual = GAEnvironment.compareTwoIndividuals(fittestIndividual, currentIndividual);
		}
		
		return fittestIndividual;
	}
}
