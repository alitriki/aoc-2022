package com.atriki.aoc2022.solutions.day9;

import com.atriki.aoc2022.Day;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.lang.Math.abs;

public class Day9Part2 extends Day<Integer> {

    public Day9Part2() {
        super(9);
    }

    Set<Pair<Integer, Integer>> visitedPositions = new HashSet<>();

    List<Pair<Integer, Integer>> knotPositions = new ArrayList<>(10);


    int totalSizesOfSmallDirectories = 0;

    @Override
    public Integer process() {
        initialisePositions();
        visitedPositions.add(Pair.of(0, 0));
        for (String line : allLines()) {
            String[] instruction = line.split(" ");
            for (int i = 1; i <= Integer.parseInt(instruction[1]); i++) {
                moveHead(instruction[0]);
                moveKnots();
            }

        }
        return visitedPositions.size();
    }

    private void initialisePositions() {
        for (int i = 0; i < 10; i++) {
            knotPositions.add(Pair.of(0, 0));
        }
    }

    void moveKnots() {
        for (int i = 1; i < knotPositions.size(); i++) {
            int relativeHeadX = knotPositions.get(i - 1).getLeft();
            int relativeHeadY = knotPositions.get(i - 1).getRight();
            int currentKnotX = knotPositions.get(i).getLeft();
            int currentKnotY = knotPositions.get(i).getRight();
            if ((abs(relativeHeadX - currentKnotX) > 1 && relativeHeadY != currentKnotY) || (abs(relativeHeadY - currentKnotY) > 1 && relativeHeadX != currentKnotX)) {
                moveDiagonally(i);
            } else {
                moveStraight(i);
            }
        }
        visitedPositions.add(Pair.of(knotPositions.get(9).getRight(), knotPositions.get(9).getLeft()));
    }

    void moveHead(String movement) {
        int headX =knotPositions.get(0).getLeft();
        int headY =knotPositions.get(0).getRight();
        switch (movement) {
            case "R" -> headX++;
            case "L" -> headX--;
            case "U" -> headY++;
            case "D" -> headY--;
            default -> throw new IllegalArgumentException("【・_・?】");
        }
        knotPositions.set(0, Pair.of(headX,headY));
    }

    void moveDiagonally(int i) {
        int relativeHeadX = knotPositions.get(i - 1).getLeft();
        int relativeHeadY = knotPositions.get(i - 1).getRight();
        int currentKnotX = knotPositions.get(i).getLeft();
        int currentKnotY = knotPositions.get(i).getRight();

        if (relativeHeadX > currentKnotX) {
            currentKnotX++;
        } else {
            currentKnotX--;
        }
        if (relativeHeadY > currentKnotY) {
            currentKnotY++;
        } else {
            currentKnotY--;
        }
        knotPositions.set(i, Pair.of(currentKnotX, currentKnotY));
    }

    void moveStraight(int i) {
        int relativeHeadX = knotPositions.get(i - 1).getLeft();
        int relativeHeadY = knotPositions.get(i - 1).getRight();
        int currentKnotX = knotPositions.get(i).getLeft();
        int currentKnotY = knotPositions.get(i).getRight();
        if (abs(relativeHeadX - currentKnotX) > 1) {
            if (relativeHeadX > currentKnotX) {
                currentKnotX++;
            } else {
                currentKnotX--;
            }
        }
        if (abs(relativeHeadY - currentKnotY) > 1) {
            if (relativeHeadY > currentKnotY) {
                currentKnotY++;
            } else {
                currentKnotY--;
            }
        }
        knotPositions.set(i, Pair.of(currentKnotX, currentKnotY));
    }


}
