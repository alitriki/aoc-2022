package com.atriki.aoc2022.solutions.day3;

import com.atriki.aoc2022.Day;

import java.util.List;
import java.util.stream.Collectors;

public class Day3_1 extends Day<Integer> {

    public Day3_1() {
        super(3);
    }

    @Override
    public Integer process() {

        return allLines().stream()
                .map(
                        line -> {
                            Character commonItem = getCommonItem(line);
                            return getItemPriority(commonItem);
                        })
                .reduce(0, Integer::sum);
    }

    private static Character getCommonItem(String line) {
        int middle = line.length() / 2;
        List<Character> firstCompartment = line.substring(0, middle)
                .chars()
                .mapToObj(e -> (char) e)
                .collect(Collectors.toList());
        List<Character> secondCompartment = line.substring(middle)
                .chars()
                .mapToObj(e -> (char) e)
                .toList();
        firstCompartment.retainAll(secondCompartment);
        return firstCompartment.get(0);
    }

    private static int getItemPriority(Character item) {
        int itemValue = item;
        if (item >= 'A' && item <= 'Z') {
            return itemValue - 38;
        } else if (item >= 'a' && item <= 'z') {
            return itemValue - 96;
        } else {
            throw new IllegalArgumentException("nani ??");
        }
    }
}
