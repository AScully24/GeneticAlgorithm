package com.ga.beans;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ga.data.Record;
import com.ga.environments.GAEnvironment;
import com.ga.individuals.ClassificationIndividual;
import com.ga.individuals.Individual;
import com.ga.individuals.SimpleIndividual;

@Configuration
public class PopulationGenerator {

	int POPULATION_SIZE = 1000;

	@Autowired
	ArrayList<Record> myRecords;

	@Bean
	public GAEnvironment simplePopulation() {
		ArrayList<Individual> population = new ArrayList<Individual>();

		for (int i = 0; i < POPULATION_SIZE; i++) {
			population.add(new SimpleIndividual(POPULATION_SIZE));
		}

		return new GAEnvironment(population);
	}

	@Bean
	public GAEnvironment classificationPopulation() {
		ArrayList<Individual> population = new ArrayList<Individual>();

		for (int i = 0; i < POPULATION_SIZE; i++) {
			population.add(new ClassificationIndividual(60, myRecords, 6));
		}
		return new GAEnvironment(population);
	}

	public ArrayList<Record> correctRecords2() {

		ArrayList<Record> records = new ArrayList<>();
		File file = new File("data2.txt");

		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line;

			while ((line = br.readLine()) != null) {
				String[] split = line.split(" ");
				Record newRecord = new Record();

				int[] intArr = new int[6];
				String charArr = split[0];

				for (int i = 0; i < charArr.length(); i++) {
					char c = charArr.charAt(i);
					intArr[i] = Character.getNumericValue(c);
				}

				newRecord.setInput(intArr);
				newRecord.setOutput(Integer.parseInt(split[1]));
				records.add(newRecord);
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return records;
	}
	
	@Bean
	public ArrayList<Record> correctRecords() {

		ArrayList<Record> records = new ArrayList<>();
		File file = new File("data1.txt");

		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line;

			while ((line = br.readLine()) != null) {
				String[] split = line.split(" ");
				Record newRecord = new Record();

				int[] intArr = new int[5];
				String charArr = split[0];

				for (int i = 0; i < charArr.length(); i++) {
					char c = charArr.charAt(i);
					intArr[i] = Character.getNumericValue(c);
				}

				newRecord.setInput(intArr);
				newRecord.setOutput(Integer.parseInt(split[1]));
				records.add(newRecord);
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return records;
	}
}
