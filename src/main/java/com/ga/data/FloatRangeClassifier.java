package com.ga.data;

import java.util.ArrayList;

import org.apache.commons.lang.math.FloatRange;

/**
 * @author user_pc
 *
 */
public class FloatRangeClassifier{
	ArrayList<Float> lower;
	ArrayList<Float> upper;
	int output;
	
	
	
	public FloatRangeClassifier() {
		super();
		lower = new ArrayList<Float>();
		upper = new ArrayList<Float>();

	}

	public FloatRangeClassifier(ArrayList<Float> lower, ArrayList<Float> upper, int output) {
		this.lower = lower;
		this.upper = upper;
		this.output = output;
	}
	
	public void addLower(Float value){
		lower.add(value);
	}
	
	public void addUpper(Float value){
		upper.add(value);
	}
	
	public boolean inputsWithingRange(ArrayList<Float> input){
		
		for (int i = 0; i < input.size(); i++) {
			Float value = input.get(i);
			Float lowerTarget = lower.get(i);
			Float upperTarget = upper.get(i);
			
//			FloatRange range = new FloatRange(lowerTarget,upperTarget);
//			if (!range.containsFloat(value)) {
//				return false;
//			}
			
			if (value < lowerTarget) {
				return false;
			}
			
			if (value > upperTarget) {
				return false;
			}
			
		}
		return true;
	}
	
	public int inputRangeCount(ArrayList<Float> input){
		int numberOfMatches = 0;
		for (int i = 0; i < input.size(); i++) {
			Float value = input.get(i);
			Float lowerTarget = lower.get(i);
			Float upperTarget = upper.get(i);
			
			FloatRange range = new FloatRange(lowerTarget,upperTarget);
			if (range.containsFloat(value)) {
				numberOfMatches++;
			}
			
		}
		return numberOfMatches;
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
	public String toString() {
		return "FloatingGene [lower=" + lower + ", upper=" + upper + ", output=" + output + "]";
	}
	
	
}
