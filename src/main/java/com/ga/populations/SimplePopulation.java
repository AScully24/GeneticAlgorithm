package com.ga.populations;

import java.util.ArrayList;

import javax.annotation.PostConstruct;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.ga.individuals.Individual;
import com.ga.individuals.SimpleIndividual;

@Component
@ConfigurationProperties(prefix = "simple")
public class SimplePopulation extends AbstractPopulation{

	@Override
	@PostConstruct
	public void generateNewRandomPopulation() {
		ArrayList<Individual> newPop = new ArrayList<Individual>();
		for (int i = 0; i < populationSize; i++) {
			newPop.add(new SimpleIndividual(geneSize));
		}
		currentPopulation = newPop;
	}

}
