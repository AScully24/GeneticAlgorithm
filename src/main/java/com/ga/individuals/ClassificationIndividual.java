package com.ga.individuals;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.ga.data.Record;

public class ClassificationIndividual extends AbstractIndividual {

	private int recordLength;
	public static final int GENE_MAX_VALUE = 3;

	@Autowired
	@Qualifier("correctRecords")
	ArrayList<Record> correctRecords;

	public ClassificationIndividual(int geneArraySize, int recordLength) {
		super(geneArraySize, GENE_MAX_VALUE);
		this.recordLength = recordLength;
		createGenesWithHash();
	}

	public ClassificationIndividual(int[] genes, int recordLength) {
		super(genes);
		this.recordLength = recordLength;
	}

	public ClassificationIndividual(int geneArraySize, ArrayList<Record> correctRecords, int recordLength) {
		super(geneArraySize, GENE_MAX_VALUE);
		this.recordLength = recordLength;
		createGenesWithHash();
		this.correctRecords = correctRecords;
	}

	public ClassificationIndividual(int[] genes, ArrayList<Record> correctRecords, int recordLength) {
		super(genes);
		this.recordLength = recordLength;
		this.correctRecords = correctRecords;
	}

	private void createGenesWithHash() {
		for (int j = 0; j < genes.length; j++) {
			genes[j] = ThreadLocalRandom.current().nextInt(GENE_MAX_VALUE);
		}
	}

	@Override
	public void calculateFitness() {
		int newFitness = 0;
		ArrayList<Record> records = genesToRecordArrayList();

		for (Record correctRecord : correctRecords) {
//			if (records.contains(correctRecord)) {
//				newFitness++;
//			}
			for (Record record : records) {
				
				if (record.compareInputs(correctRecord) && record.getHashCount() <=3) {
//					newFitness++;
					if (record.compareOutputs(correctRecord)) {
						newFitness++;
						break;
					}
				}
			}
		}
		
		fitness = newFitness;
	}

	/**
	 * 
	 * @param genesToMutate
	 */
	@Override
	protected void mutateGenes(int[] genesToMutate) {
		for (int i = 0; i < genesToMutate.length; i++) {
			if (ThreadLocalRandom.current().nextInt(MUTATION_DIVIDER) <= MUTATION_RATE) {				
				genesToMutate[i] = ThreadLocalRandom.current().nextInt(GENE_MAX_VALUE);
//				genesToMutate[i] = randomNumberExcluding(GENE_MAX_VALUE,genesToMutate[i]);
			}
		}
	}
	
	// TODO: Needs to be made more efficient.
	@SuppressWarnings("unused")
	private int randomNumberExcluding(int upper,int exclude){
		while (true) {
			int nextInt = ThreadLocalRandom.current().nextInt(upper);
			if (nextInt != exclude) {
				return nextInt;
			}
		}
	}

	public ArrayList<Record> genesToRecordArrayList() {
		ArrayList<Record> records = new ArrayList<Record>();
		for (int i = 0; i < genes.length; i += recordLength) {
			int[] input = new int[recordLength - 1];
			for (int j = 0; j < input.length; j++) {
				input[j] = genes[i + j];
			}

			int output = genes[i + recordLength - 1];
			Record newRecord = new Record();
			newRecord.setInput(input);
			newRecord.setOutput(output);
			records.add(newRecord);
		}
		return records;
	}

	@Override
	protected AbstractIndividual createChild(int[] genes) {
		return new ClassificationIndividual(genes, correctRecords,recordLength);
	}
}