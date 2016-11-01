package com.ga.genes;

import java.util.ArrayList;

/**
 * @author user_pc
 *
 */
public class FloatingGene implements Gene {
	ArrayList<Float> lower;
	ArrayList<Float> upper;
	int output;

	public FloatingGene(ArrayList<Float> lower, ArrayList<Float> upper, int output) {
		super();
		this.lower = lower;
		this.upper = upper;
		this.output = output;
	}
	
	public boolean inputsWithingRange(ArrayList<Float> input){
		
		for (int i = 0; i < input.size(); i++) {
			Float value = input.get(i);
			Float lowerTarget = lower.get(i);
			Float upperTarget = upper.get(i);
		
			if (value < lowerTarget) {
				return false;
			}
			if (value > upperTarget) {
				return false;
			}
//			if (!(value > lowerTarget) && !(value < upperTarget)) {
//				return false;
//			}
		}
		return true;
	}
	
	public ArrayList<Float> getLower() {
		return lower;
	}

	public void setLower(ArrayList<Float> lower) {
		this.lower = lower;
	}

	public ArrayList<Float> getUpper() {
		return upper;
	}

	public void setUpper(ArrayList<Float> upper) {
		this.upper = upper;
	}

	public int getOutput() {
		return output;
	}

	public void setOutput(int output) {
		this.output = output;
	}

	@Override
	public Gene copyGene() {
		return new FloatingGene(lower, upper, output);
	}

	@Override
	public String toString() {
		return "FloatingGene [lower=" + lower + ", upper=" + upper + ", output=" + output + "]";
	}
	
	
}
