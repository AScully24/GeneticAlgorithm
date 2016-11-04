package com.ga;

import java.util.Calendar;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ga.environments.GAEnvironmentRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestGAEnvironmentRunner {
	
	@Autowired
	GAEnvironmentRunner runner;
	
	@Test
	public void testRunnerArraysExist(){
		runner.printEnvironments();
	}
	
	@Test
	public void testRunAllGenerations(){
		System.out.println(Calendar.getInstance().getTime());
		runner.runAllEnvironments();
		System.out.println(Calendar.getInstance().getTime());
	}
	
}
