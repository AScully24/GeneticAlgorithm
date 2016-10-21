package com.ga.data;

import java.util.Arrays;

public class Record {
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
	public boolean equals(Object obj) {
		if (!(obj instanceof Record)) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		
		Record otherRecord = (Record) obj;
		int[] otherInput = otherRecord.getInput();
		for (int i = 0; i < otherInput.length; i++) {
			if (input[i] != otherInput[i]) {
				return false;
			}
		}
		
		if (otherRecord.getOutput() == output) {
			return true;
		}
		
		return false;
	}

	@Override
	public String toString() {
		return "Record [input=" + Arrays.toString(input) + ", output=" + output + "]";
	}
	
	
}
