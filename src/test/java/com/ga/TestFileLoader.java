package com.ga;

import java.util.ArrayList;

import org.junit.Test;

import com.ga.data.FloatRecord;

public class TestFileLoader {
	
	@Test
	public void testFloatingRecord(){
		ArrayList<FloatRecord> loadedRecords = FileLoader.loadBitFileToArrayListFloat("data3.txt", 6);
		for (FloatRecord floatingRecord : loadedRecords) {
			System.out.println(floatingRecord);
		}
	}
}
