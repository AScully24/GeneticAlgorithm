package com.ga.environments;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ga.individuals.Individual;

/**
 * @author user_pc
 *
 */
public class GAEnvironment {

	private static final Logger logger = LoggerFactory.getLogger(GAEnvironment.class);

	ArrayList<Individual> population = new ArrayList<Individual>();

	String problemName;
	int targetFitness;

	public GAEnvironment(ArrayList<Individual> population, String problemName, int targetFitness) {
		this.population = population;
		this.problemName = problemName;
		this.targetFitness = targetFitness;
	}

	/**
	 * @return An ArrayList containing the population, with the number of individuals representing their fitness within the population.
	 */
	public ArrayList<Individual> createRouletteWheel(ArrayList<Individual> popToCheck) {
		double totalFitness = getPopulationTotalFitness(popToCheck);
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
		Individual fittestIndividual = getFittestIndividual();
//		population = selectionCrossover();
		 ArrayList<Individual> offspring = selectionCrossover();
		 population = selectionCompetition(offspring);
		replaceWeakestWithElite(fittestIndividual, population);

	}

	/**
	 * Recreates the population by crossing over and mutating the existing population.
	 * 
	 * @return Offspring of the current population.
	 */
	private ArrayList<Individual> selectionCrossover() {
		ArrayList<Individual> rouletteWheel = createRouletteWheel(population);
//		 ArrayList<Individual> rouletteWheel = selectionCompetition(population);
		ArrayList<Individual> offspring = new ArrayList<Individual>();
		int populationSize = population.size();

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
		int populationSize = population.size();

		for (int i = 0; i < populationSize; i++) {

			Individual parent1 = selectRandomFromArrayList(toCompete);
			Individual parent2 = selectRandomFromArrayList(toCompete);

			offspring.add(compareTwoIndividuals(parent1, parent2));
		}

		replaceWeakestWithElite(getFittestIndividual(), offspring);

		return offspring;
	}

	private Individual compareTwoIndividuals(Individual one, Individual two) {
		if (one.getFitness() > two.getFitness()) {
			return one;
		}

		return two;
	}

	/**
	 * @return The sum of the fitness of all the individuals.
	 */
	public double getPopulationTotalFitness(ArrayList<Individual> popToCheck) {
		double sum = 0;
		for (Individual individual : popToCheck) {
			sum += individual.getFitness();
		}
		return sum;
	}

	/**
	 * @return The average fitness of all the individuals.
	 */
	public double getCurrentPopulationAverageFitness() {
		double count = 0;
		double sum = 0;

		for (Individual individual : population) {
			count++;
			sum += individual.getFitness();
		}
		return (sum / count);
	}

	public void replaceWeakestWithElite(Individual fittestIndividual, ArrayList<Individual> popToChange) {
		Individual weakestIndividual = popToChange.get(0);
		int weakestIndex = 0;

		for (int i = 0; i < popToChange.size(); i++) {
			Individual individual = popToChange.get(i);
			if (individual.getFitness() < weakestIndividual.getFitness()) {
				weakestIndividual = individual;
				weakestIndex = i;
			}
		}

		popToChange.set(weakestIndex, fittestIndividual);
	}

	public Individual getFittestIndividual() {
		Individual fittestIndividual = population.get(0);
		for (Individual individual : population) {
			if (individual.getFitness() > fittestIndividual.getFitness()) {
				fittestIndividual = individual;
			}
		}
		return fittestIndividual;
	}

	public void multipleRuns(int runCount) {
		for (int i = 0; i < runCount; i++) {
			multipleGenerations(runCount, runCount, targetFitness);
		}
	}

	public Individual multipleGenerations(int runNumber, int generationCount, int targetFitness) {

		Individual overallFittesIndividual = getFittestIndividual();
		logger.info("Run_Number,Generation_Number,Max_Fitness,Average_Fitness,Solution_Found,Problem_name");
		for (int generationNumber = 0; generationNumber < generationCount; generationNumber++) {

			generateNewPopulation();

			Individual currentfittestsIndividual = getFittestIndividual();

			// System.out.printf("%d,%d,%2f\n",
			// i,currentfittestsIndividual.getFitness(),
			// getCurrentPopulationAverageFitness());

			if (currentfittestsIndividual.getFitness() > overallFittesIndividual.getFitness()) {
				overallFittesIndividual = currentfittestsIndividual;
			}
			boolean solutionFound = overallFittesIndividual.getFitness() == targetFitness;
			logger.info("{},{},{},{},{},{}", runNumber, generationNumber, currentfittestsIndividual.getFitness(), getCurrentPopulationAverageFitness(), solutionFound, problemName);

			if (solutionFound) {
				return overallFittesIndividual;
			}
		}
		return overallFittesIndividual;
	}

	@Override
	public String toString() {
		return "GAEnvironment [population=" + population + ", problemName=" + problemName + ", targetFitness=" + targetFitness + "]";
	}

	public ArrayList<Individual> getPopulation() {
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
		this.population = population;
	}
}