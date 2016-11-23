package com.ga;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.ga.data.BinaryRecord;
import com.ga.data.ContraRecord;
import com.ga.data.FloatRecord;
import com.ga.genes.FloatGene;
import com.ga.genes.Gene;
import com.ga.genes.IntegerGene;

public class FileLoader {
	
	public static ArrayList<BinaryRecord> loadBitFileToArrayList(String fileLocation, int arraySize, String lineSplit) {
		ArrayList<BinaryRecord> records = new ArrayList<>();
		File file = new File(fileLocation);

		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line;

			while ((line = br.readLine()) != null) {
				String[] split = line.split(lineSplit);
				BinaryRecord newRecord = new BinaryRecord();

				int[] intArr = new int[arraySize];
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

	public static ArrayList<ContraRecord> loadContraFileToArrayList(String fileLocation, int arraySize, String lineSplit) {
		ArrayList<ContraRecord> records = new ArrayList<>();
		File file = new File(fileLocation);

		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line;

			while ((line = br.readLine()) != null) {
				String[] split = line.split(lineSplit);
				ContraRecord newRecord = new ContraRecord();
				ArrayList<Integer> intArr = new ArrayList<Integer>();
				
				for (int i = 0; i < split.length-1; i++) {
					intArr.add(Integer.parseInt(split[i]));
				}
				
				newRecord.setInput(intArr);
				newRecord.setOutput(Integer.parseInt(split[split.length-1]));
				records.add(newRecord);
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return records;
	}
	
	public static ArrayList<FloatRecord> loadBitFileToArrayListFloat(String fileLocation, int inputSize) {
		ArrayList<FloatRecord> records = new ArrayList<>();
		File file = new File(fileLocation);
		
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line;

			while ((line = br.readLine()) != null) {
				String[] split = line.split(" ");
				FloatRecord newRecord = new FloatRecord();
				
				ArrayList<Float> input = new ArrayList<Float>();
				for (int i = 0; i < inputSize; i++) {
					input.add(Float.parseFloat(split[i]));
				}

				newRecord.setInput(input);
				newRecord.setOutput(Integer.parseInt(split[split.length-1]));
				records.add(newRecord);
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return records;
	}
	
	public static ArrayList<Gene> geneLoader(String fileLocation){
		ArrayList<Gene> genes = new ArrayList<Gene>();
		
		File file = new File(fileLocation);
		
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line = br.readLine();
			String[] split = line.split(",");
			for (String string : split) {
				Float value = Float.parseFloat(string);
				FloatGene newGene = new FloatGene(value );
				genes.add(newGene);
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return genes;
	}
	
	public static ArrayList<Gene> geneLoaderBinary(String fileLocation){
		ArrayList<Gene> genes = new ArrayList<Gene>();
		
		File file = new File(fileLocation);
		
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line = br.readLine();
			String[] split = line.split(",");
			for (String string : split) {
				int value = Integer.parseInt(string);
				IntegerGene newGene = new IntegerGene(value);
				genes.add(newGene);
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return genes;
	}
}
