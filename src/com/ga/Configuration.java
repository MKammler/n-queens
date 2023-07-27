package com.ga;

public class Configuration {

  private Configuration() {}

  static final int BOARD_SIZE = 10;
  static final int POPULATION_SIZE = 500; // must be an even number
  static final int MAX_GENERATIONS = 50000;
  static final int PRINT_EVERY_N_GENERATIONS = 50;
  static final double MUTATION_PROBABILITY =
      0.01; // probability of each individual mutating in each generation
}
