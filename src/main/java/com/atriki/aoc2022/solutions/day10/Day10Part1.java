package com.atriki.aoc2022.solutions.day10;

import com.atriki.aoc2022.Day;
import org.apache.commons.lang3.tuple.Pair;

import java.util.*;
import java.util.stream.IntStream;

import static java.lang.Math.abs;

public class Day10Part1 extends Day<Integer> {

    public Day10Part1() {
        super(10);
    }

    List<Integer> registerValuePerCycle = new ArrayList<>();

    @Override
    public Integer process() {
        initValuePerCycle();
        for (String line : allLines()) {
            String[] content = line.split(" ");
            if (content[0].equals("noop")) {
                processNoop();
            } else {
                processAdd(Integer.parseInt(content[1]));
            }
        }
        return calculateSignalStrength();
    }

    private void initValuePerCycle() {
        //added this first element to account for the cycle processing delay
        registerValuePerCycle.add(0);
        registerValuePerCycle.add(1);
    }

    private void processNoop() {
        registerValuePerCycle.add(registerValuePerCycle.get(registerValuePerCycle.size() - 1));
    }

    private void processAdd(int value) {
        int lastValue = registerValuePerCycle.get(registerValuePerCycle.size() - 1);
        registerValuePerCycle.add(lastValue);
        registerValuePerCycle.add(lastValue + value);
    }

    private int calculateSignalStrength() {
        registerValuePerCycle = registerValuePerCycle.subList(20, registerValuePerCycle.size());
        return IntStream.range(0, registerValuePerCycle.size())
                .filter(i -> i % 40 == 0)
                .map(i -> (i+20) * registerValuePerCycle.get(i))
                .sum();
    }


}
