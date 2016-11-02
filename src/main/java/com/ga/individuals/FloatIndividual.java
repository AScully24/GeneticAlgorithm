package com.ga.individuals;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import com.ga.data.FloatRangeClassifier;
import com.ga.data.FloatingRecord;
import com.ga.genes.FloatGene;
import com.ga.genes.Gene;

public class FloatIndividual extends AbstractIndividual {

	ArrayList<FloatingRecord> correctRecords;

	public FloatIndividual(int geneArraySize) {
		super(geneArraySize);
	}

	public FloatIndividual(ArrayList<Gene> genes, ArrayList<FloatingRecord> correctRecords) {
		super(genes);
		this.correctRecords = correctRecords;
	}

	public FloatIndividual(int geneArraySize, ArrayList<FloatingRecord> correctRecords) {
		super(geneArraySize);
		this.correctRecords = correctRecords;
	}

	@Override
	protected Individual createChild(ArrayList<Gene> childGenes) {
		return new FloatIndividual(genes, correctRecords);
	}

	@Override
	protected void mutateGenes(ArrayList<Gene> childGenes) {
		for (Gene gene : childGenes) {
			FloatGene floatGene = (FloatGene) gene;
			if (ThreadLocalRandom.current().nextInt(MUTATION_DIVIDER) <= mutationRate) {
				floatGene.setValue(ThreadLocalRandom.current().nextFloat());
			}
		}
	}

	@Override
	protected int calculateFitness() {
		int newFitness = 0;
		ArrayList<FloatRangeClassifier> classifiers = genesToRangeClassifier();
		for (FloatingRecord floatingRecord : correctRecords) {

			ArrayList<Float> input = floatingRecord.getInput();
			int output = floatingRecord.getOutput();

			for (FloatRangeClassifier classifer : classifiers) {
//				int toAdd = classifer.inputRangeCount(input);
//				if (classifer.getOutput() == output) {
//					toAdd++;
//				}
//				
//				newFitness += toAdd;
//				if (toAdd >= 7) {
//					break;
//				}
				if (classifer.inputsWithingRange(input)) {
					if (classifer.getOutput() == output) {
						newFitness++;
						break;
					}	
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

	public int catergoriseFloatingRecord(FloatingRecord record) {
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
		// geneArraySize is the number of records in the the individual
		ArrayList<Gene> newGenes = new ArrayList<Gene>();
		for (int i = 0; i < geneArraySize; i++) {
			FloatGene gene = new FloatGene(ThreadLocalRandom.current().nextFloat());
			newGenes.add(gene);
		}
		return newGenes;
	}

	public ArrayList<FloatingRecord> getCorrectRecords() {
		return correctRecords;
	}
}