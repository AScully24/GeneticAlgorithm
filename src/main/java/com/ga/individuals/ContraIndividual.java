package com.ga.individuals;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import com.ga.data.ContraRecord;
import com.ga.genes.Gene;
import com.ga.genes.IntegerGene;

public class ContraIndividual extends AbstractIndividual{

	private int recordLength;
	private static final int GENE_MAX_VALUE = 49;
	ArrayList<ContraRecord> correctRecords;

	public ContraIndividual(int geneArraySize, ArrayList<ContraRecord> correctRecords, int recordLength) {
		super(geneArraySize);
		this.recordLength = recordLength;
		this.correctRecords = correctRecords;
		fitness = calculateFitness();
	}

	public ContraIndividual(ArrayList<Gene> genes, ArrayList<ContraRecord> correctRecords, int recordLength, int mutationRate) {
		super(genes, mutationRate);
		this.recordLength = recordLength;
		this.correctRecords = correctRecords;
		mutateGenes();
		fitness = calculateFitness();
	}

	@Override
	protected ArrayList<Gene> createDefaultGenes() {
		ArrayList<Gene> newGenes = new ArrayList<Gene>();
		for (int j = 0; j < geneArraySize; j++) {
			newGenes.add(new IntegerGene(ThreadLocalRandom.current().nextInt(GENE_MAX_VALUE)));
		}
		return newGenes;
	}

	@Override
	public int calculateFitness() {
		int newFitness = 0;
		ArrayList<ContraRecord> records = genesToRecordArrayList();
		for (ContraRecord correctRecord : correctRecords) {
			for (ContraRecord rule : records) {
				if (rule.compareInputs(correctRecord)) {
					if (rule.compareOutputs(correctRecord)) {
						newFitness++;
					}
					break;
				}
			}
		}
		return newFitness;
	}

	// TODO: Needs to be made more efficient.
	private int randomNumberExcluding(int upper, int exclude) {
		while (true) {
			int nextInt = ThreadLocalRandom.current().nextInt(upper);
			if (nextInt != exclude) {
				return nextInt;
			}
		}
	}

	@Override
	public void mutateGenes() {
		for (Gene gene : genes) {
			if (ThreadLocalRandom.current().nextInt(MUTATION_DIVIDER) <= mutationRate) {
				IntegerGene binaryGene = (IntegerGene) gene;
				binaryGene.setValue(randomNumberExcluding(GENE_MAX_VALUE, binaryGene.getValue()));
			}
		}

	}

	public ArrayList<ContraRecord> genesToRecordArrayList() {
		ArrayList<ContraRecord> records = new ArrayList<ContraRecord>();
		for (int i = 0; i < genes.size(); i += recordLength) {

			ArrayList<Integer> input = new ArrayList<Integer>();
			for (int j = 0; j < recordLength - 1; j++) {
				input.add(((IntegerGene) genes.get(i + j)).getValue());
			}

			int output = ((IntegerGene) genes.get(i + recordLength - 1)).getValue();

			ContraRecord newRecord = new ContraRecord();
			newRecord.setInput(input);
			newRecord.setOutput(output);
			records.add(newRecord);
		}
		return records;
	}

	@Override
	protected Individual createChild(ArrayList<Gene> genes) {
		return new ContraIndividual(genes, correctRecords, recordLength, mutationRate);
	}

	public void setCorrectRecords(ArrayList<ContraRecord> correctRecords) {
		this.correctRecords = correctRecords;
	}

	@Override
	public String toString() {
		ArrayList<ContraRecord> records = genesToRecordArrayList();
		String string = "";
		for (ContraRecord contraRecord : records) {
			string += contraRecord + "\n";
		}
		return string;
	}
	
	@Override
	public int getFitness() {
		return fitness;
	}
}
