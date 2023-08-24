package com.ga;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Selector {
  private static final Random random = new Random();

  public static List<IndividualSolution> selectIndividualsForCrossover(
      List<IndividualSolution> population) {

    double sumOfFitness = population.stream().mapToDouble(IndividualSolution::getFitness).sum();

    return IntStream.range(0, Configuration.POPULATION_SIZE)
        .mapToObj(v -> selectIndividualBasedOnFitness(population, sumOfFitness))
        .collect(Collectors.toList());
  }

  private static IndividualSolution selectIndividualBasedOnFitness(
      List<IndividualSolution> population, double sumOfFitness) {

    double randomValue = random.nextDouble() * sumOfFitness;
    double cumulativeSum = 0d;

    for (IndividualSolution individual : population) {
      cumulativeSum += individual.getFitness();
      if (cumulativeSum >= randomValue) {
        return individual;
      }
    }
    return population.get(population.size() - 1);
  }
}
