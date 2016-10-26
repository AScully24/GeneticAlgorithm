package com.ga;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.ga.data.FloatingRecord;
import com.ga.data.Record;

public class FileLoader {
	
	public static ArrayList<Record> loadBitFileToArrayList(String fileLocation, int arraySize) {
		ArrayList<Record> records = new ArrayList<>();
		File file = new File(fileLocation);

		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line;

			while ((line = br.readLine()) != null) {
				String[] split = line.split(" ");
				Record newRecord = new Record();

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
	
	public static ArrayList<FloatingRecord> loadBitFileToArrayListDouble(String fileLocation, int arraySize) {
		ArrayList<FloatingRecord> records = new ArrayList<>();
		File file = new File(fileLocation);

		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line;

			while ((line = br.readLine()) != null) {
				String[] split = line.split(" ");
				FloatingRecord newRecord = new FloatingRecord();

				double[] intArr = new double[arraySize];
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