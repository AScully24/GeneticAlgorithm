package com.ga.individuals;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import com.ga.data.FloatingRecord;
import com.ga.genes.FloatingGene;
import com.ga.genes.Gene;

public class FloatingIndividual extends AbstractIndividual {

	ArrayList<FloatingRecord> correctRecords;

	public FloatingIndividual(int geneArraySize) {
		super(geneArraySize);
	}

	public FloatingIndividual(ArrayList<Gene> genes, ArrayList<FloatingRecord> correctRecords) {
		super(genes);
		this.correctRecords = correctRecords;
	}

	public FloatingIndividual(int geneArraySize, ArrayList<FloatingRecord> correctRecords) {
		super(geneArraySize);
		this.correctRecords = correctRecords;
	}

	@Override
	protected Individual createChild(ArrayList<Gene> childGenes) {
		return new FloatingIndividual(genes, correctRecords);
	}

	@Override
	protected void mutateGenes(ArrayList<Gene> childGenes) {
		for (Gene gene : childGenes) {
			FloatingGene floatingGene = (FloatingGene) gene;
			mutateFloatArray(floatingGene.getLower());
			mutateFloatArray(floatingGene.getUpper());
			
			if (ThreadLocalRandom.current().nextInt(MUTATION_DIVIDER) <= mutationRate) {				
				floatingGene.setOutput(floatingGene.getOutput() ^ 1);
			}
			
		}
	}

	private void mutateFloatArray(ArrayList<Float> floatArray) {
		for (int i = 0; i < floatArray.size(); i++) {
			if (ThreadLocalRandom.current().nextInt(MUTATION_DIVIDER) <= mutationRate) {
				floatArray.set(i, ThreadLocalRandom.current().nextFloat());
			}
		}
		
//		for (Float value : floatArray) {
//			if (ThreadLocalRandom.current().nextInt(MUTATION_DIVIDER) <= mutationRate) {
//				value = ThreadLocalRandom.current().nextFloat();
//			}
//		}
	}

	@Override
	protected void calculateFitness() {
		int newFitness = 0;
		for (FloatingRecord floatingRecord : correctRecords) {
			
			ArrayList<Float> input = floatingRecord.getInput();
			int output = floatingRecord.getOutput();
			
			for (Gene gene : genes) {
				FloatingGene floatingGene = (FloatingGene) gene;
				if (floatingGene.inputsWithingRange(input)) {
					if (floatingGene.getOutput() == output) {
						newFitness++;
						break;
					}
				}
			}
		}
		fitness = newFitness;
	}
	
	public int catergoriseFloatingRecord(FloatingRecord record){
		for (Gene gene : genes) {
			FloatingGene floatingGene = (FloatingGene) gene;
			if (floatingGene.inputsWithingRange(record.getInput())) {
				return floatingGene.getOutput();
			}
		}
		return 2;
	}
	
	@Override
	protected ArrayList<Gene> createDefaultGenes() {
		// geneArraySize is the number of records in the the individual
		ArrayList<Gene> newGenes = new ArrayList<Gene>();
		for (int i = 0; i < geneArraySize; i++) {
			ArrayList<Float> lower = generateRandomFloatArray();
			ArrayList<Float> upper = generateRandomFloatArray();
			int output = ThreadLocalRandom.current().nextInt(2);
			FloatingGene gene = new FloatingGene(lower, upper, output);
			newGenes.add(gene);
		}
		return newGenes;
	}

	private ArrayList<Float> generateRandomFloatArray() {
		ArrayList<Float> floatArray = new ArrayList<Float>();
		for (int j = 0; j < 6; j++) {
			floatArray.add(ThreadLocalRandom.current().nextFloat());
		}

		return floatArray;
	}
}
