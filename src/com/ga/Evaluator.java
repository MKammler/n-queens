package com.ga;

import java.util.List;

public class Evaluator {
  private Evaluator() {}

  public static void evaluate(List<IndividualSolution> population) {
    population.forEach(IndividualSolution::evaluateAndSetFitness);
  }
}
