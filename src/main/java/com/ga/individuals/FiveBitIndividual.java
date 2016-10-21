package com.ga.individuals;

import java.util.ArrayList;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.ga.data.Record;

public class FiveBitIndividual extends SimpleIndividual {

	@Autowired
	@Qualifier("correctRecords")
	ArrayList<Record> correctRecords;

	public FiveBitIndividual(int geneArraySize) {
		super(geneArraySize);
	}

	public FiveBitIndividual(int[] genes) {
		super(genes);
	}

	public FiveBitIndividual(int i, ArrayList<Record> myRecords) {
		super(i);
		this.correctRecords = myRecords;
	}

	@Override
	@PostConstruct
	public void calculateFitness() {
		int newFitness = 0;
		ArrayList<Record> records = genesToRecordArrayList();
		
		// REMOVE
//		System.out.println("record check: " + correctRecords);
		for (Record record : records) {
			if (correctRecords.contains(record)) {
				newFitness++;
			}
		}
		fitness = newFitness;
	}

	private ArrayList<Record> genesToRecordArrayList() {
		ArrayList<Record> records = new ArrayList<Record>();
		for (int i = 0; i < genes.length; i += 6) {
			int[] input = new int[5];
			for (int j = 0; j < input.length; j++) {
				input[j] = genes[i + j];
			}

			int output = genes[i + 5];
			Record newRecord = new Record();
			newRecord.setInput(input);
			newRecord.setOutput(output);
			records.add(newRecord);
		}
		return records;
	}
}
