package com.ga.genes;

public class IntegerGene implements Gene {
	Integer value;

	public IntegerGene(Integer value) {
		this.value = value;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public String toString() {
		return Integer.toString(value);
	}

	@Override
	public Gene copyGene() {
		IntegerGene gene = new IntegerGene(value);
		return gene;
	}
}
