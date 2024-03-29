package com.ga.environments;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ga.individuals.FloatIndividual;
import com.ga.individuals.FloatIndividualTester;
import com.ga.individuals.Individual;
import com.ga.populations.AbstractPopulation;
import com.ga.populations.Population;
import com.ga.populations.PopulationData;

/**
 * @author user_pc
 *
 */
public class GAEnvironment {

	private static final Logger logger = LoggerFactory.getLogger(GAEnvironment.class);

	Population population;

	String problemName;
	int targetFitness;

	public GAEnvironment(Population population, String problemName, int targetFitness) {
		this.population = population;
		this.problemName = problemName;
		this.targetFitness = targetFitness;
	}

	/**
	 * @return An ArrayList containing the population, with the number of individuals representing their fitness within the population.
	 */
	public ArrayList<Individual> createRouletteWheel(ArrayList<Individual> popToCheck) {
		double totalFitness = population.getPopulationTotalFitness(popToCheck);
		ArrayList<Individual> rouletteWheel = new ArrayList<Individual>();

		for (Individual individual : popToCheck) {
			long rouletteCount = (long) Math.ceil((individual.getFitness() / totalFitness) * 100);
			for (int i = 0; i < rouletteCount; i++) {
				rouletteWheel.add(individual);
			}
		}
		return rouletteWheel;
	}

	public void generateNewPopulation() {
		Individual fittestIndividual = population.getFittestIndividual();

		population.setCurrentPopulation(selectionCrossover());

		// ArrayList<Individual> offspring = selectionCrossover();
		// population.setCurrentPopulation(selectionCompetition(offspring));

		ArrayList<Individual> newPop = replaceWeakestWithElite(fittestIndividual, population.getCurrentPopulation());
		population.setCurrentPopulation(newPop);

	}

	/**
	 * Recreates the population by crossing over and mutating the existing population.
	 * 
	 * @return Offspring of the current population.
	 */
	private ArrayList<Individual> selectionCrossover() {
		// ArrayList<Individual> rouletteWheel = createRouletteWheel(population.getCurrentPopulation());
		ArrayList<Individual> rouletteWheel = selectionCompetition(population.getCurrentPopulation());
		ArrayList<Individual> offspring = new ArrayList<Individual>();
		int populationSize = population.getCurrentPopulation().size();

		// offspring.add(population.getFittestIndividual());

		for (int i = 0; i < populationSize; i++) {
			Individual parent1 = selectRandomFromArrayList(rouletteWheel);
			Individual parent2 = selectRandomFromArrayList(rouletteWheel);

			ArrayList<Individual> children = parent1.createChildren(parent2);
			for (Individual individual : children) {
				offspring.add(individual);
			}
		}

		return offspring;
	}

	private Individual selectRandomFromArrayList(ArrayList<Individual> array) {
		int size = array.size();
		return array.get(ThreadLocalRandom.current().nextInt(size));
	}

	/**
	 * Recreates the population by comparing the fitness of the individuals, and allowing the higher fitness individual into the next generation..
	 * 
	 * @return
	 */
	protected ArrayList<Individual> selectionCompetition(ArrayList<Individual> toCompete) {
		ArrayList<Individual> offspring = new ArrayList<Individual>();
		int populationSize = population.getCurrentPopulation().size();

		for (int i = 0; i < populationSize; i++) {

			Individual parent1 = selectRandomFromArrayList(toCompete);
			Individual parent2 = selectRandomFromArrayList(toCompete);

			offspring.add(AbstractPopulation.compareTwoIndividuals(parent1, parent2));
		}

		return offspring;
	}

	public ArrayList<Individual> replaceWeakestWithElite(Individual fittestIndividual, ArrayList<Individual> popToChange) {
		Individual weakestIndividual = popToChange.get(0);
		int weakestIndex = 0;

		for (int i = 0; i < popToChange.size(); i++) {
			Individual individual = popToChange.get(i);
			if (individual.getFitness() < weakestIndividual.getFitness()) {
				weakestIndividual = individual;
				weakestIndex = i;
			}
		}

		if (weakestIndividual.getFitness() < fittestIndividual.getFitness()) {
			popToChange.set(weakestIndex, fittestIndividual);
		}

		return popToChange;
	}

	
	//TODO: move multipleRuns and multipleGenerations to the GAEnvironmentRunner class.
	public Individual multipleRuns(int runLimit, int generationLimit) {
//		ArrayList<RunResult> runResults = new ArrayList<RunResult>();
		Individual ind = null;
		logger.info("Run_Number,Generation_Number,Max_Fitness,Weakest_Fitness,Average_Fitness,Solution_Found,mutation_rate,gene_size,pop_size,Problem_name,Float_Test_Performance");
		for (int runCount = 1; runCount <= runLimit; runCount++) {
			// TODO: Prevent double creation of a new generation.
			population.generateNewRandomPopulation();
			ind = multipleGenerations(runCount, generationLimit, targetFitness);
		}
		return ind;
	}
	
	public Individual multipleGenerations(int runNumber, int generationLimit, int targetFitness) {
		Individual overallFittesIndividual = population.getFittestIndividual();
		PopulationData data = population.getPopulationData();
		for (int generationNumber = 1; generationNumber <= generationLimit; generationNumber++) {

			generateNewPopulation();

			Individual currentfittestsIndividual = population.getFittestIndividual();
			if (currentfittestsIndividual.getFitness() >= overallFittesIndividual.getFitness()) {
				overallFittesIndividual = currentfittestsIndividual;
			}
			
			Individual weakestIndividual = population.getWeakestIndividual();
			boolean solutionFound = overallFittesIndividual.getFitness() == targetFitness;

			// Test FloatIndividual performance
			String fittestTestResult = "-";
			if (overallFittesIndividual instanceof FloatIndividual && (generationNumber % 20) == 0) {
				fittestTestResult = FloatIndividualTester.testDataPerformancePercentage((FloatIndividual) overallFittesIndividual).toString();
			}
			logger.info("{},{},{},{},{},{},{},{},{},{},{}", runNumber, generationNumber, currentfittestsIndividual.getFitness(), weakestIndividual.getFitness(), population.getCurrentPopulationAverageFitness(), solutionFound, data.getMutationRate(), data.getGeneSize(), data.getPopulationSize(), problemName, fittestTestResult);

			if (solutionFound) {
				return overallFittesIndividual;
			}
		}

		return overallFittesIndividual;
	}

	public Population getPopulation() {
		return population;
	}

	public String getProblemName() {
		return problemName;
	}

	public void setProblemName(String problemName) {
		this.problemName = problemName;
	}

	public int getTargetFitness() {
		return targetFitness;
	}

	public void setTargetFitness(int targetFitness) {
		this.targetFitness = targetFitness;
	}

	public void setPopulation(ArrayList<Individual> population) {
		this.population.setCurrentPopulation(population);
	}

	@Override
	public String toString() {
		return "GAEnvironment [problemName=" + problemName + ", targetFitness=" + targetFitness + "]";
	}
}