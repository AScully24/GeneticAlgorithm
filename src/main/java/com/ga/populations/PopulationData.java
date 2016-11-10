package com.ga.populations;

public class PopulationData {
	private String problemName;
	private int geneSize;
	private int populationSize;
	private int mutationRate;

	public PopulationData() {
	}

	public String getProblemName() {
		return problemName;
	}

	public void setProblemName(String problemName) {
		this.problemName = problemName;
	}

	public int getGeneSize() {
		return geneSize;
	}

	public void setGeneSize(int geneSize) {
		this.geneSize = geneSize;
	}

	public int getPopulationSize() {
		return populationSize;
	}

	public void setPopulationSize(int populationSize) {
		this.populationSize = populationSize;
	}

	public int getMutationRate() {
		return mutationRate;
	}

	public void setMutationRate(int mutationRate) {
		this.mutationRate = mutationRate;
	}
}