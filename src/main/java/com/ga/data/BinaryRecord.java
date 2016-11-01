package com.ga.data;

import java.util.Arrays;

public class BinaryRecord extends AbstractRecord{
	private static final int HASH_VALUE = 2;
	int[] input;
	int output;
	
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
	
	@Override
	public boolean compareInputs(BinaryRecord otherRecord){
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
	
	@Override
	public boolean compareOutputs(BinaryRecord otherRecord){
		return output == otherRecord.getOutput();
	}

	@Override
	public String toString() {
		return "Record [input=" + Arrays.toString(input) + ", output=" + output + "]";
	}

}
