package com.ga.data;

import java.util.ArrayList;

public class FloatingRecord extends AbstractRecord {
	ArrayList<Float> input;
	int output;

	public ArrayList<Float> getInput() {
		return input;
	}

	public void setInput(ArrayList<Float> input) {
		this.input = input;
	}

	public int getOutput() {
		return output;
	}

	public void setOutput(int output) {
		this.output = output;
	}

	@Override
	public boolean compareInputs(BinaryRecord otherRecord) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean compareOutputs(BinaryRecord otherRecord) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String toString() {
		return "FloatingRecord [input=" + input + ", output=" + output + "]";
	}
	
	

}
