package com.ga.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.ga.environments.GAEnvironment;
import com.ga.populations.BinaryPopulation;
import com.ga.populations.FloatPopulation;
import com.ga.populations.SimplePopulation;

@Configuration
@ComponentScan("com.ga.populations")
public class GAEnvironmentGenerator {

	@Autowired
	FloatPopulation floatPopulation;

	@Autowired
	SimplePopulation simplePopulation;
	
	@Autowired
	@Qualifier("createSixBitPopulation")
	BinaryPopulation sixBitPopulation;

	@Autowired
	@Qualifier("createFiveBitPopulation")
	BinaryPopulation fiveBitPopulation;

	
	@Bean
	public GAEnvironment simpleGAEnvironment() {
		return new GAEnvironment(simplePopulation,simplePopulation.getProblemName(), simplePopulation.getGeneSize());
	}

	@Bean
	public GAEnvironment sixBitGAEnvironment() {
		return new GAEnvironment(sixBitPopulation, sixBitPopulation.getProblemName(), sixBitPopulation.getTrainingRecords().size());
	}
	
	@Bean
	public GAEnvironment fiveBitGAEnvironment() {
		return new GAEnvironment(fiveBitPopulation, fiveBitPopulation.getProblemName(), fiveBitPopulation.getTrainingRecords().size());
	}
	
	@Bean
	public GAEnvironment floatGAEnvironment() {
		return new GAEnvironment(floatPopulation, floatPopulation.getProblemName(), floatPopulation.getTrainingRecords().size());
	}
	
	
	// Load populations for Five Bit and Six Bit problems.
	@Bean
	@ConfigurationProperties(prefix = "data1")
	public BinaryPopulation createFiveBitPopulation(){
		return new BinaryPopulation();
	}
	
	@Bean
	@ConfigurationProperties(prefix = "data2")
	public BinaryPopulation createSixBitPopulation(){
		return new BinaryPopulation();
	}
}
