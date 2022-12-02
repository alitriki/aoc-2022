package com.atriki.aoc2022.solutions;

import com.atriki.aoc2022.Day;

public class Day2_2 extends Day<Integer> {

    public Day2_2() {
        super(2);
    }

    @Override
    public Integer process() {
        return allLines().stream().map(line -> {
            String[] input = line.split(" ");
            Shape opponentShape = Shape.getShape(input[0]);
            Shape myShape = deduceMyShape(opponentShape, input[1]);

            return myShape.getShapePoints() + getExpectedDuelPoints(input[1]);
        }).reduce(0, Integer::sum);
    }

    private Shape deduceMyShape(Shape opponentShape, String action) {
        return switch (action) {
            case "X" -> Shape.valueOf(opponentShape.getDefeats());
            case "Y" -> opponentShape;
            case "Z" -> Shape.valueOf(opponentShape.getLoosesTo());
            default -> throw new IllegalArgumentException("wuuuuut ?");

        };

    }

    int getExpectedDuelPoints(String input) {
        return switch (input) {
            case "X" -> 0;
            case "Y" -> 3;
            case "Z" -> 6;
            default -> throw new IllegalArgumentException("wuuuuut ?");
        };
    }

    enum Shape {
        ROCK(1, "SCISSORS", "PAPER"),
        PAPER(2, "ROCK", "SCISSORS"),
        SCISSORS(3, "PAPER", "ROCK"),
        ;

        private final int shapePoints;
        private final String defeats;
        private final String loosesTo;

        Shape(int shapePoints, String defeats, String loosesTo) {
            this.shapePoints = shapePoints;
            this.defeats = defeats;
            this.loosesTo = loosesTo;
        }

        public String getDefeats() {
            return defeats;
        }

        public String getLoosesTo() {
            return loosesTo;
        }

        int getShapePoints() {
            return shapePoints;
        }


        static Shape getShape(String input) {
            return switch (input) {
                case "A" -> ROCK;
                case "B" -> PAPER;
                case "C" -> SCISSORS;
                default -> throw new IllegalArgumentException("wuuuuut ?");
            };
        }
    }
}
