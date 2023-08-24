package com.ga;

import java.util.List;
import java.util.Random;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Mutator {

  private static final Random random = new Random();

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
