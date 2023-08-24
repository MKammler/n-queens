package com.ga;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AlgorithmExecutor {

  private boolean problemSolved = false;
  private int currentGeneration;

  public void execute() {

    List<IndividualSolution> population = createFirstGeneration();

    for (currentGeneration = 0;
        currentGeneration < Configuration.MAX_GENERATIONS;
        currentGeneration++) {
      if (currentGeneration % Configuration.PRINT_EVERY_N_GENERATIONS == 0) {
        printFitnessStatistic(population);
      }
      if (exitCriterionFulfilled(population)) {
        break;
      }
      population = Selector.selectIndividualsForCrossover(population);
      population = Reproducer.createNextGeneration(population);
      Mutator.mutate(population);
    }
    validateAndPrintBestSolution(population);
  }

  private boolean exitCriterionFulfilled(List<IndividualSolution> population) {
    // TODO: Given the population, return true if the problem is solved (optional, but saves time).
    return false;
  }

  private List<IndividualSolution> createFirstGeneration() {
    return Stream.generate(IndividualSolution::new)
        .limit(Configuration.POPULATION_SIZE)
        .collect(Collectors.toList());
  }

  private void printFitnessStatistic(List<IndividualSolution> population) {
    double average =
        population.stream()
            .mapToDouble(IndividualSolution::getFitness)
            .average()
            .orElse(Integer.MAX_VALUE);
    double best =
        population.stream()
            .mapToDouble(IndividualSolution::getFitness)
            .max()
            .orElse(Integer.MAX_VALUE);
    double worst =
        population.stream()
            .mapToDouble(IndividualSolution::getFitness)
            .min()
            .orElse(Integer.MIN_VALUE);
    System.out.println(
        "Generation: "
            + currentGeneration
            + ", worst: "
            + worst
            + ", average: "
            + average
            + ", best: "
            + best);
  }

  private void printSolution(IndividualSolution individual) {
    for (int i = 0; i < Configuration.BOARD_SIZE; i++) {
      List<Integer> board = individual.getChessBoard();
      for (int j = 0; j < Configuration.BOARD_SIZE; j++) {
        if (j == board.get(i)) {
          System.out.print(" X ");
        } else {
          System.out.print(" - ");
        }
      }
      System.out.println();
    }
  }

  private void printResult() {
    if (problemSolved) {
      System.out.println("Congratulation, problem solved :)");
    } else {
      System.out.println("That is not a valid solution :(");
    }
  }

  private void validateAndPrintBestSolution(List<IndividualSolution> population) {
    IndividualSolution bestSolution =
        population.stream()
            .max(Comparator.comparing(IndividualSolution::getFitness))
            .orElse(new IndividualSolution());
    printSolution(bestSolution);
    problemSolved = SolutionValidator.validate(bestSolution);
    printResult();
  }
}
