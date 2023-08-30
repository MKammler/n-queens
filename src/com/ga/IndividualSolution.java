package com.ga;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
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

  private void evaluateAndSetFitness() {
    long numberOfCollisions =
        IntStream.range(0, Configuration.BOARD_SIZE)
            .flatMap(
                i ->
                    IntStream.range(i + 1, Configuration.BOARD_SIZE)
                        .filter(
                            j ->
                                Math.abs(chessBoard.get(i) - chessBoard.get(j)) == j - i
                                    || chessBoard.get(i).equals(chessBoard.get(j))))
            .count();
    this.fitness =
        (double) Configuration.BOARD_SIZE * (Configuration.BOARD_SIZE - 1) - numberOfCollisions;
  }
}
