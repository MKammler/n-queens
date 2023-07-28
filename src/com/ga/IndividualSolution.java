package com.ga;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.collections4.ListUtils;

@ToString
public class IndividualSolution {

  private final Random random = new Random();

  @Getter @Setter private List<Integer> chessBoard;
  @Getter private double fitness = 0d;

  public IndividualSolution() {
    chessBoard =
        Stream.generate(() -> random.nextInt(Configuration.BOARD_SIZE))
            .limit(Configuration.BOARD_SIZE)
            .collect(Collectors.toList());
    evaluateAndSetFitness();
  }

  public IndividualSolution(List<Integer> parentBoardPart1, List<Integer> parentBoardPart2) {
    chessBoard = ListUtils.union(parentBoardPart1, parentBoardPart2);
    evaluateAndSetFitness();
  }

  public void evaluateAndSetFitness() {
    // TODO: Determine and set the fitness value for this individual solution. The higher the
    // fitness, the more likely it is to be chosen for crossover.
    this.fitness = Double.MAX_VALUE;
  }
}
