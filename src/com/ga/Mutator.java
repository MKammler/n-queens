package com.ga;

import java.util.List;
import java.util.Random;

public class Mutator {

  private static final Random random = new Random();

  private Mutator() {}

  public static void mutate(List<IndividualSolution> population) {
    population.forEach(
        v -> {
          if (random.nextDouble() < Configuration.MUTATION_PROBABILITY) {
            int positionToMutate = random.nextInt(Configuration.BOARD_SIZE);
            int newQueenPosition = random.nextInt(Configuration.BOARD_SIZE);
            v.getChessBoard().set(positionToMutate, newQueenPosition);
          }
        });
  }
}
