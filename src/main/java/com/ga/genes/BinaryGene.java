package com.ga.genes;

public class BinaryGene implements Gene {
	int value;
	
	public BinaryGene(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String toString() {
		return Integer.toString(value);
	}

	@Override
	public Gene copyGene() {
		BinaryGene gene = new BinaryGene(value);
		return gene;
	}
	
	
}
