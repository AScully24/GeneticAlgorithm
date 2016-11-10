package com.ga.genes;

/**
 * @author user_pc
 *
 */
public class FloatGene implements Gene {

	private static final float SCALE = 2f;
	Float value;

	public FloatGene(Float value) {
		super();
//		this.value = value;
		this.value = roundToNearestHalf(value);
	}

	private float roundToNearestHalf(Float value) {
		return Math.round(value * SCALE) / SCALE;
	}

	public Float getValue() {
		return value;
	}

	public void setValue(Float value) {
		this.value = value;
		this.value = roundToNearestHalf(value);
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
