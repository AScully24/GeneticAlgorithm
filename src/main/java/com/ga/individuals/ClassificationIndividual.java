package com.ga.individuals;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import com.ga.data.BinaryRecord;
import com.ga.data.Record;
import com.ga.genes.BinaryGene;
import com.ga.genes.Gene;

public class ClassificationIndividual extends AbstractIndividual {
	
	private int recordLength;
	private static final int GENE_MAX_VALUE = 3;
	ArrayList<BinaryRecord> correctRecords;
	
	public ClassificationIndividual(int geneArraySize, ArrayList<BinaryRecord> correctRecords, int recordLength) {
		super(geneArraySize);
		this.recordLength = recordLength;
		this.correctRecords = correctRecords;
	}

	public ClassificationIndividual(ArrayList<Gene> genes, ArrayList<BinaryRecord> correctRecords, int recordLength) {
		super(genes);
		this.recordLength = recordLength;
		this.correctRecords = correctRecords;
	}

	@Override
	protected ArrayList<Gene> createDefaultGenes() {
		ArrayList<Gene> newGenes = new ArrayList<Gene>();
		for (int j = 0; j < geneArraySize; j++) {
			newGenes.add(new BinaryGene(ThreadLocalRandom.current().nextInt(GENE_MAX_VALUE)));
		}
		return newGenes;
	}

	@Override
	public int calculateFitness() {
		int newFitness = 0;
		ArrayList<BinaryRecord> records = genesToRecordArrayList();
		for (BinaryRecord correctRecord : correctRecords) {
			for (Record rule : records) {
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
	private int randomNumberExcluding(int upper,int exclude){
		while (true) {
			int nextInt = ThreadLocalRandom.current().nextInt(upper);
			if (nextInt != exclude) {
				return nextInt;
			}
		}
	}
	
	@Override
	protected void mutateGenes(ArrayList<Gene> genesToMutate) {
		
		for (Gene gene : genesToMutate) {
			if (ThreadLocalRandom.current().nextInt(MUTATION_DIVIDER) <= mutationRate) {				
//				genesToMutate[i] = ThreadLocalRandom.current().nextInt(GENE_MAX_VALUE);
				BinaryGene binaryGene = (BinaryGene) gene;
				binaryGene.setValue(randomNumberExcluding(GENE_MAX_VALUE,binaryGene.getValue()));
			}
		}
		
	}

	public ArrayList<BinaryRecord> genesToRecordArrayList() {
		ArrayList<BinaryRecord> records = new ArrayList<BinaryRecord>();
		for (int i = 0; i < genes.size(); i += recordLength) {
			
			int[] input = new int[recordLength - 1];
			for (int j = 0; j < input.length; j++) {
				input[j] = ((BinaryGene) genes.get(i + j)).getValue();
			}
			
			int output = ((BinaryGene) genes.get(i + recordLength - 1)).getValue();
			
			BinaryRecord newRecord = new BinaryRecord();
			newRecord.setInput(input);
			newRecord.setOutput(output);
			records.add(newRecord);
		}
		return records;
	}
	
	@Override
	protected Individual createChild(ArrayList<Gene> genes) {
		return new ClassificationIndividual(genes, correctRecords,recordLength);
	}

	public void setCorrectRecords(ArrayList<BinaryRecord> correctRecords) {
		this.correctRecords = correctRecords;
	}
	
	
}