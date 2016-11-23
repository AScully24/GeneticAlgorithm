package com.ga.data;

import java.util.ArrayList;

public class ContraRecord {

	ArrayList<Integer> input;
	Integer output;
	
	public ArrayList<Integer> getInput() {
		return input;
	}

	public void setInput(ArrayList<Integer> input) {
		this.input = input;
	}

	public int getOutput() {
		return output;
	}
	
	public void setOutput(int output) {
		this.output = output;
	}
	
	public boolean compareInputs(ContraRecord otherRecord){
		ArrayList<Integer> correctInput = otherRecord.getInput();
		
		for (int i = 0; i < correctInput.size(); i++) {
				if (input.get(i) != correctInput.get(i)) {
					return false;
			}
		}
		return true;
	}
	
	public boolean compareOutputs(ContraRecord otherRecord){
		return output == otherRecord.getOutput();
	}

	@Override
	public String toString() {
		return "Record [input=" + input + ", output=" + output + "]";
	}

}
