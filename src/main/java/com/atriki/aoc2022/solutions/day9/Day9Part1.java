package com.atriki.aoc2022.solutions.day9;

import com.atriki.aoc2022.Day;
import org.apache.commons.lang3.tuple.Pair;

import java.util.*;

import static java.lang.Math.abs;

public class Day9Part1 extends Day<Integer> {

    public Day9Part1() {
        super(9);
    }

    Set<Pair<Integer, Integer>> visitedPositions = new HashSet<>();
    int headX = 0;
    int headY = 0;
    int tailX = 0;
    int tailY = 0;


    int totalSizesOfSmallDirectories = 0;

    @Override
    public Integer process() {
        visitedPositions.add(Pair.of(0, 0));
        for (String line : allLines()) {
            String[] instruction = line.split(" ");
            for (int i = 1; i <= Integer.parseInt(instruction[1]); i++) {
                moveHead(instruction[0]);
                moveTail();
            }

        }
        return visitedPositions.size();
    }

    void moveTail() {
        if ((abs(headX - tailX) > 1 && headY != tailY) || (abs(headY - tailY) > 1 && headX != tailX)) {
            moveDiagonally();
        } else {
            moveStraight();
        }
        visitedPositions.add(Pair.of(tailX, tailY));
    }

    void moveHead(String movement) {
        switch (movement) {
            case "R" -> headX++;
            case "L" -> headX--;
            case "U" -> headY++;
            case "D" -> headY--;
            default -> throw new IllegalArgumentException("【・_・?】");
        }
    }

    void moveDiagonally() {
        if (headX > tailX) {
            tailX++;
        } else {
            tailX--;
        }
        if (headY > tailY) {
            tailY++;
        } else {
            tailY--;
        }
    }

    void moveStraight() {
        if (abs(headX - tailX) > 1) {
            if (headX > tailX) {
                tailX++;
            } else {
                tailX--;
            }
        }
        if (abs(headY - tailY) > 1) {
            if (headY > tailY) {
                tailY++;
            } else {
                tailY--;
            }
        }
    }


}
