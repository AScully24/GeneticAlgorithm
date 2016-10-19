package com.ga.environments;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import com.ga.individuals.Individual;
import com.ga.individuals.SimpleIndividual;

public class GAEnvironment {
	static int POPULATION_SIZE = 50;
	ArrayList<Individual> population = new ArrayList<Individual>();
	
	public GAEnvironment(ArrayList<Individual> population) {
		this.population = population;
	}
	
	/**
	 * Default constructor creates a simple population.
	 */
	public GAEnvironment() {
		for (int i = 0; i < POPULATION_SIZE; i++) {
			population.add(new SimpleIndividual());
		}
	}

	/**
	 * @return An ArrayList containing the population, with the number of
	 *         individuals representing their fitness within the population.
	 */
	public ArrayList<Individual> createRouletteWheel(ArrayList<Individual> popToCheck) {
		double totalFitness = getPopulationTotalFitness(popToCheck);
		ArrayList<Individual> rouletteWheel = new ArrayList<Individual>();

		for (Individual individual : popToCheck) {
			long rouletteCount = Math.round((individual.getFitness() / totalFitness) * 100);
			for (int i = 0; i < rouletteCount; i++) {
				rouletteWheel.add(individual);
			}
		}
		return rouletteWheel;
	}

	public void generateNewPopulation() {
		ArrayList<Individual> offspring = selectionCrossover();
		selectionCompetition(offspring);
		// selectionNewPopulationFromOffspring(offspring);
	}

	@SuppressWarnings("unused")
	private void selectionNewPopulationFromOffspring(ArrayList<Individual> offspring) {
		ArrayList<Individual> rouletteWheel = createRouletteWheel(offspring);
		ArrayList<Individual> newPopulation = new ArrayList<Individual>();
		int populationSize = population.size();

		for (int i = 0; i < populationSize; i++) {
			int random = ThreadLocalRandom.current().nextInt(rouletteWheel.size());
			newPopulation.add(rouletteWheel.get(random));
		}

		population = newPopulation;
	}

	/**
	 * Recreates the population by crossing over and mutating the existing
	 * population.
	 * 
	 * @return Offspring of the current population.
	 */
	private ArrayList<Individual> selectionCrossover() {

		ArrayList<Individual> rouletteWheel = createRouletteWheel(population);
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
		return array.get(ThreadLocalRandom.current().nextInt(array.size()));
	}

	/**
	 * Recreates the population by comparing the fitness of the individuals, and
	 * allowing the higher fitness individual into the next generation..
	 */
	protected void selectionCompetition(ArrayList<Individual> toCompete) {
		ArrayList<Individual> offspring = new ArrayList<Individual>();
		int populationSize = population.size();

		for (int i = 0; i < populationSize; i++) {

			Individual parent1 = selectRandomFromArrayList(toCompete);
			Individual parent2 = selectRandomFromArrayList(toCompete);

			if (parent1.getFitness() > parent2.getFitness()) {
				offspring.add(parent1);
			} else {
				offspring.add(parent2);
			}
		}
		population = offspring;
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

	public Individual getFittestsIndividual() {
		Individual fittestIndividual = population.get(0);
		for (Individual individual : population) {
			if (individual.getFitness() > fittestIndividual.getFitness()) {
				fittestIndividual = individual;
			}
		}
		return fittestIndividual;
	}

	public ArrayList<Individual> getPopulation() {
		return population;
	}
}