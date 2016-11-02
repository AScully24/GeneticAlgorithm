package com.ga.genes;

/**
 * @author user_pc
 *
 */
public class FloatGene implements Gene {

	Float value;

	public FloatGene(Float value) {
		super();
		this.value = value;
	}

	public Float getValue() {
		return value;
	}

	public void setValue(Float value) {
		this.value = value;
	}

	@Override
	public Gene copyGene() {
		return new FloatGene(value);
	}

	@Override
	public String toString() {
		return value.toString();
	}

}
