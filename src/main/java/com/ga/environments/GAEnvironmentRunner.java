package com.ga.environments;


import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GAEnvironmentRunner {
	
	@Autowired
	List<GAEnvironment> environments;
	
	
	public void runAllEnvironments(){
		System.out.println(Calendar.getInstance().getTime());		
		for (GAEnvironment environment : environments) {
			environment.multipleRuns(1, 5000);
		}
		System.out.println(Calendar.getInstance().getTime());
	}
	
	public void printEnvironments(){
		for (GAEnvironment environment : environments) {
			System.out.println(environment);
		}
	}
	
}
