package com.ga.environments;

import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class GAEnvironmentRunner {

	@Autowired
	List<GAEnvironment> environments;

	@Value("${simple.data.problem-name}")
	String simpleProblemName;

	@PostConstruct
	public void init() {
		for (int i = 0; i < environments.size(); i++) {
			if (environments.get(i).getProblemName().equalsIgnoreCase(simpleProblemName)) {
				environments.remove(i);
			}

		}
	}

	public void runAllEnvironments() {
		System.out.println("Starting all runs:" + Calendar.getInstance().getTime());

		for (GAEnvironment environment : environments) {
			System.out.println(environment.getProblemName());
			environment.getPopulation();
			environment.multipleRuns(5, 10000);
		}

		System.out.println("All Run end time:" + Calendar.getInstance().getTime());
	}

	public void printEnvironments() {
		for (GAEnvironment environment : environments) {
			System.out.println(environment);
		}
	}
}
