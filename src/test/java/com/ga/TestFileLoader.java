package com.ga;

import java.util.ArrayList;

import org.junit.Test;

import com.ga.data.FloatingRecord;

public class TestFileLoader {
	
	@Test
	public void testFloatingRecord(){
		ArrayList<FloatingRecord> loadedRecords = FileLoader.loadBitFileToArrayListFloat("data3.txt", 6);
		for (FloatingRecord floatingRecord : loadedRecords) {
			System.out.println(floatingRecord);
		}
	}
}
