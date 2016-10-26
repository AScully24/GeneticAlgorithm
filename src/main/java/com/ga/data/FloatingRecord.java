package com.ga.data;

import java.util.Arrays;

public class FloatingRecord {
	private static final int HASH_VALUE = 2;
	double[] input;
	int output;
	
	public int getHashCount() {
		int count = 0;
		for (double i : input) {
			if (i == HASH_VALUE) {
				count++;
			}
		}
		return count;
	}
	
	public double[] getInput() {
		return input;
	}

	public void setInput(double[] input) {
		this.input = input;
	}

	public int getOutput() {
		return output;
	}
	
	public void setOutput(int output) {
		this.output = output;
	}
	
	public boolean compareInputs(FloatingRecord otherRecord){
		double[] correctInput = otherRecord.getInput();
		
		for (int i = 0; i < correctInput.length; i++) {
			if (input[i] != HASH_VALUE && correctInput[i] != HASH_VALUE) {
				if (input[i] != correctInput[i]) {
					return false;
				}
			}
		}
		return true;
	}
	
	public boolean compareOutputs(FloatingRecord otherRecord){
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

		FloatingRecord otherRecord = (FloatingRecord) obj;
		
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
