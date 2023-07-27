package com.ga;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Reproducer {

  private static final Random random = new Random();

  private Reproducer() {}

  public static List<IndividualSolution> createNextGeneration(List<IndividualSolution> population) {

    List<IndividualSolution> nextGeneration = new ArrayList<>(Configuration.POPULATION_SIZE);

    for (int i = 0; i < Configuration.POPULATION_SIZE; i += 2) {
      IndividualSolution parent1 = population.get(i);
      IndividualSolution parent2 = population.get(i + 1);
      int indexToCut = random.nextInt(Configuration.BOARD_SIZE - 1) + 1;

      IndividualSolution child1 =
          new IndividualSolution(
              parent1.getChessBoard().subList(0, indexToCut),
              parent2.getChessBoard().subList(indexToCut, Configuration.BOARD_SIZE));
      IndividualSolution child2 =
          new IndividualSolution(
              parent2.getChessBoard().subList(0, indexToCut),
              parent1.getChessBoard().subList(indexToCut, Configuration.BOARD_SIZE));

      nextGeneration.add(child1);
      nextGeneration.add(child2);
    }
    return nextGeneration;
  }
}
