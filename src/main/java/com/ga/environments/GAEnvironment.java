package com.ga.environments;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import com.ga.individuals.Individual;

public class GAEnvironment {
	static int POPULATION_SIZE = 50;
	ArrayList<Individual> population = new ArrayList<Individual>();

	public GAEnvironment(ArrayList<Individual> population) {
		this.population = population;
	}

	/**
	 * @return An ArrayList containing the population, with the number of
	 *         individuals representing their fitness within the population.
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
		ArrayList<Individual> offspring = selectionCrossover();
		population = selectionCompetition(offspring);
//		selectionNewPopulationFromOffspring(offspring);
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
		replaceWeakestWithElite(getFittestsIndividual(), newPopulation);
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
//		ArrayList<Individual> rouletteWheel = selectionCompetition(population);
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
	 * Recreates the population by comparing the fitness of the individuals, and
	 * allowing the higher fitness individual into the next generation..
	 * @return 
	 */
	protected ArrayList<Individual> selectionCompetition(ArrayList<Individual> toCompete) {
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
		
		replaceWeakestWithElite(getFittestsIndividual(),offspring);
		
		return offspring;
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
//		for (Individual individual : popToChange) {
//			if (individual.getFitness() < weakestIndividual.getFitness()) {
//				weakestIndividual = individual;
//			}
//		}
		
		popToChange.set(weakestIndex, fittestIndividual);
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

	public Individual multipleGenerations(int generationCount, int targetFitness) {

		Individual overallFittesIndividual = getFittestsIndividual();
		System.out.println("Generation,Max Fitness,Average Fitness");
		for (int i = 0; i < generationCount; i++) {

			generateNewPopulation();

			Individual currentfittestsIndividual = getFittestsIndividual();
			
//			System.out.printf("Generation: %d, Average Fitness: %f, Max Fitness: %d\n",i,getCurrentPopulationAverageFitness(),currentfittestsIndividual.getFitness());
//			// REMOVE: Remove output when from final release
//			for (Individual individual : population) {
//				System.out.println(individual);
//			}
//			
//			System.out.println("\n");
			System.out.printf("%d,%d,%2f\n", i,currentfittestsIndividual.getFitness(), getCurrentPopulationAverageFitness());

			if (currentfittestsIndividual.getFitness() > overallFittesIndividual.getFitness()) {
				overallFittesIndividual = currentfittestsIndividual;
			}
			if (overallFittesIndividual.getFitness() == targetFitness) {
				return overallFittesIndividual;
			}
		}

		return overallFittesIndividual;
	}

	public ArrayList<Individual> getPopulation() {
		return population;
	}
}