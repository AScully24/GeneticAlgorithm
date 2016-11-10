package com.ga.individuals;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import com.ga.data.FloatRangeClassifier;
import com.ga.data.FloatRecord;
import com.ga.genes.FloatGene;
import com.ga.genes.Gene;

public class FloatIndividual extends AbstractIndividual {

	ArrayList<FloatRecord> trainingRecords;
	ArrayList<FloatRecord> testRecords;
	int fitness = 0;

	public FloatIndividual(int geneArraySize, ArrayList<FloatRecord> correctRecords) {
		super(geneArraySize);
		this.trainingRecords = correctRecords;
		fitness = calculateFitness();
	}

	public FloatIndividual(ArrayList<Gene> genes, ArrayList<FloatRecord> correctRecords, int mutationRate) {
		super(genes, mutationRate);
		this.trainingRecords = correctRecords;
		this.mutationRate = mutationRate;
		mutateGenes();
		fitness = calculateFitness();
	}

	@Override
	protected Individual createChild(ArrayList<Gene> childGenes) {
		return new FloatIndividual(childGenes, trainingRecords, mutationRate);
	}

	@Override
	public void mutateGenes() {
//		mutateGenesSimple(genes);
		 mutateGenesAdavanced(genes);
	}

	protected void mutateGenesSimple(ArrayList<Gene> genesToMutate) {
		for (Gene gene : genesToMutate) {
			FloatGene floatGene = (FloatGene) gene;
			if (ThreadLocalRandom.current().nextInt(MUTATION_DIVIDER) <= mutationRate) {
				floatGene.setValue(ThreadLocalRandom.current().nextFloat());
			}
		}
	}

	protected void mutateGenesAdavanced(ArrayList<Gene> genesToMutatate) {
		for (int i = 0; i < genesToMutatate.size(); i += 13) {
			for (int j = 0; j < 6; j++) {
				if (ThreadLocalRandom.current().nextInt(MUTATION_DIVIDER) <= mutationRate) {
					FloatGene gene = (FloatGene) genesToMutatate.get(i + j);
					gene.setValue(randomInRange(0.0f, 0.5f));
				}
			}

			for (int j = 6; j < 12; j++) {
				if (ThreadLocalRandom.current().nextInt(MUTATION_DIVIDER) <= mutationRate) {
					FloatGene gene = (FloatGene) genesToMutatate.get(i + j);
					gene.setValue(randomInRange(0.5f, 1.0f));
				}
			}

			if (ThreadLocalRandom.current().nextInt(MUTATION_DIVIDER) <= mutationRate) {
				FloatGene gene = (FloatGene) genesToMutatate.get(i + 12);
				gene.setValue(randomInRange(0.0f, 1.0f));
			}
		}

	}

	@Override
	protected int calculateFitness() {
		int newFitness = 0;
		ArrayList<FloatRangeClassifier> classifiers = genesToRangeClassifier();
		for (FloatRecord floatingRecord : trainingRecords) {

			ArrayList<Float> input = floatingRecord.getInput();
			int output = floatingRecord.getOutput();

			for (FloatRangeClassifier classifer : classifiers) {
				if (classifer.inputsWithingRange(input)) {
					if (classifer.getOutput() == output) {
						newFitness++;
					}
					break;
				}
			}
		}
		return newFitness;
	}

	public ArrayList<FloatRangeClassifier> genesToRangeClassifier() {
		ArrayList<FloatRangeClassifier> arrayList = new ArrayList<FloatRangeClassifier>();

		for (int i = 0; i < genes.size(); i += 13) {
			FloatRangeClassifier classifier = new FloatRangeClassifier();
			for (int j = 0; j < 6; j++) {
				Float value = ((FloatGene) genes.get(i + j)).getValue();
				classifier.addLower(value);
			}

			for (int j = 6; j < 12; j++) {
				Float value = ((FloatGene) genes.get(i + j)).getValue();
				classifier.addUpper(value);

			}

			Float output = ((FloatGene) genes.get(i + 12)).getValue();
			classifier.setOutput(Math.round(output));
			arrayList.add(classifier);
		}

		return arrayList;

	}

	public int catergoriseFloatingRecord(FloatRecord record) {
		ArrayList<FloatRangeClassifier> classifiers = genesToRangeClassifier();
		for (FloatRangeClassifier classifier : classifiers) {
			if (classifier.inputsWithingRange(record.getInput())) {
				return classifier.getOutput();
			}
		}
		return 2;
	}

	@Override
	protected ArrayList<Gene> createDefaultGenes() {
		return createDefaultGenesSimple();
		// return createDefaultGenesAdvanced();
	}

	protected ArrayList<Gene> createDefaultGenesSimple() {
		// geneArraySize is the number of records in the the individual
		ArrayList<Gene> newGenes = new ArrayList<Gene>();
		for (int i = 0; i < geneArraySize; i++) {
			FloatGene gene = new FloatGene(ThreadLocalRandom.current().nextFloat());
			newGenes.add(gene);
		}
		return newGenes;
	}

	public ArrayList<FloatRecord> getTrainingRecords() {
		return trainingRecords;
	}

	public static float randomInRange(float min, float max) {
		return (float) (Math.random() < 0.5 ? ((1 - Math.random()) * (max - min) + min) : (Math.random() * (max - min) + min));
	}

	/**
	 * Adds genes based on whether they are upper or lower values
	 */
	protected ArrayList<Gene> createDefaultGenesAdvanced() {

		ArrayList<Gene> newGenes = new ArrayList<Gene>();
		for (int i = 0; i < geneArraySize; i += 13) {

			for (int j = 0; j < 6; j++) {
				// Float value = ThreadLocalRandom.current().nextFloat() * 0.5f;
				Float value = randomInRange(0.0f, 0.5f);
				FloatGene newGene = new FloatGene(value);
				newGenes.add(newGene);
			}

			for (int j = 6; j < 12; j++) {
				// Float value = ThreadLocalRandom.current().nextFloat() * 0.5f;
				Float value = randomInRange(0.5f, 1.0f);
				FloatGene newGene = new FloatGene(value);
				newGenes.add(newGene);
			}

			Float output = randomInRange(0.0f, 1.0f);
			;
			FloatGene newGene = new FloatGene(output);
			newGenes.add(newGene);
		}

		return newGenes;
	}

	@Override
	public int getFitness() {
		return fitness;
	}

	@Override
	public String toString() {
		ArrayList<FloatRangeClassifier> classifiers = genesToRangeClassifier();
		String string = "";
		for (FloatRangeClassifier classifier : classifiers) {
			ArrayList<Float> lower = classifier.getLower();
			ArrayList<Float> upper = classifier.getUpper();

			string += lower + "\n";
			string += upper + "\n";
			string += classifier.getOutput() + "\n\n";
		}
		return string;
	}

	
}