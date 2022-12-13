package com.atriki.aoc2022.solutions.day2;

import com.atriki.aoc2022.Day;

public class Day2Part1 extends Day<Integer> {

    public Day2Part1() {
        super(2);
    }

    @Override
    public Integer process() {
        return allLines().stream().map(line -> {
            String[] input = line.split(" ");
            return countRoundPoints(Shape.getShape(input[0]), Shape.getShape(input[1]));
        }).reduce(0, Integer::sum);
    }

    private int countRoundPoints(Shape opponentShape, Shape myShape) {
        return myShape.getShapePoints() + myShape.getDuelPoints(opponentShape);
    }

    enum Shape {
        ROCK(1, "SCISSORS"),
        PAPER(2, "ROCK"),
        SCISSORS(3, "PAPER"),
        ;

        private final int shapePoints;
        private final String defeats;


        Shape(int shapePoints, String defeats) {
            this.shapePoints = shapePoints;
            this.defeats = defeats;
        }

        int getShapePoints() {
            return shapePoints;
        }

        static Shape getShape(String input) {
            return switch (input) {
                case "A", "X" -> ROCK;
                case "B", "Y" -> PAPER;
                case "C", "Z" -> SCISSORS;
                default -> throw new IllegalArgumentException("wuuuuut ?");
            };
        }

        int getDuelPoints(Shape otherPlayersShape) {
            int result = 0;
            if (this.defeats.equals(otherPlayersShape.name())) {
                result = 6;
            } else if (this.equals(otherPlayersShape)) {
                result = 3;
            }
            return result;
        }
    }
}
