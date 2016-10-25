package com.ga.data;

import java.util.Arrays;

public class Record {
	private static final int HASH_VALUE = 2;
	int[] input;
	int output;
	
	public int getHashCount() {
		int count = 0;
		for (int i : input) {
			if (i == HASH_VALUE) {
				count++;
			}
		}
		return count;
	}
	
	public int[] getInput() {
		return input;
	}

	public void setInput(int[] input) {
		this.input = input;
	}

	public int getOutput() {
		return output;
	}
	
	public void setOutput(int output) {
		this.output = output;
	}
	
	public boolean compareInputs(Record otherRecord){
		int[] correctInput = otherRecord.getInput();
		
		for (int i = 0; i < correctInput.length; i++) {
			if (input[i] != HASH_VALUE && correctInput[i] != HASH_VALUE) {
				if (input[i] != correctInput[i]) {
					return false;
				}
			}
		}
		return true;
	}
	
	public boolean compareOutputs(Record otherRecord){
		return output == otherRecord.getOutput();
	}

	@Override
	public boolean equals(Object obj) {

		if (!(obj instanceof Record)) {
			return false;
		}
		if (obj == this) {
			return true;
		}

		Record otherRecord = (Record) obj;
		
		if (!compareInputs(otherRecord)) {
			return false;
		}
		
		return compareOutputs(otherRecord);
	}

	@Override
	public String toString() {
		return "Record [input=" + Arrays.toString(input) + ", output=" + output + "]";
	}

}
