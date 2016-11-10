package com.ga.individuals;

import java.util.ArrayList;

import org.assertj.core.data.Percentage;

import com.ga.FileLoader;
import com.ga.data.FloatRecord;

public class FloatIndividualTester {
	private static ArrayList<FloatRecord> testRecords = FileLoader.loadBitFileToArrayListFloat("test-data.txt", 6);
	
	public static int testDataPerformance(FloatIndividual fittestsIndividual) {
		int score = 0;
		for (FloatRecord record : testRecords) {
			int targetOutput = record.getOutput();
			int indOutput = fittestsIndividual.catergoriseFloatingRecord(record);
			if (targetOutput == indOutput) {
				score++;
			}
		}
		return score;
	}
	
	
	//TODO: Extract some of the FloatIndividual comparisons to this class to make it a little cleaner.
	public static Percentage testDataPerformancePercentage(FloatIndividual fittestsIndividual) {
		int score = testDataPerformance(fittestsIndividual);
		float value = ((float)score/(float)testRecords.size()) * 100;
		Percentage percentage = Percentage.withPercentage(value);
		
		return percentage;
	}
}
