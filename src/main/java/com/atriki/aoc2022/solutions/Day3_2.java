package com.atriki.aoc2022.solutions;

import com.atriki.aoc2022.Day;

import java.util.List;
import java.util.stream.Collectors;

public class Day3_2 extends Day<Integer> {

    public Day3_2() {
        super(3);
    }

    @Override
    public Integer process() {
        int totalPriorities = 0;
        for (int i = 0;
             i < allLines().size();
             i += 3) {
            String firstElfInGroup = allLines().get(i);
            String secondElfInGroup = allLines().get(i + 1);
            String thirdElfInGroup = allLines().get(i + 2);
            Character commonItem = getCommonItemInGroup(firstElfInGroup, secondElfInGroup, thirdElfInGroup);
            totalPriorities += getItemPriority(commonItem);
        }

        return totalPriorities;
    }

    private static Character getCommonItemInGroup(String firstElfInGroup, String secondElfInGroup, String thirdElfInGroup) {
        List<Character> firstRucksack = firstElfInGroup.chars().mapToObj(e -> (char) e).collect(Collectors.toList());
        List<Character> secondRucksack = secondElfInGroup.chars().mapToObj(e -> (char) e).toList();
        List<Character> thirdRucksack = thirdElfInGroup.chars().mapToObj(e -> (char) e).toList();
        firstRucksack.retainAll(secondRucksack);
        firstRucksack.retainAll(thirdRucksack);
        return firstRucksack.get(0);
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
