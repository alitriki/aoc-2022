package com.atriki.aoc2022.solutions.day4;

import com.atriki.aoc2022.Day;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day4Part1 extends Day<Integer> {

    public Day4Part1() {
        super(4);
    }

    @Override
    public Integer process() {

        return allLines().stream()
                .map(line -> line.split(","))
                .map(
                        sections ->
                                isOneAssignmentRangeFullyContainedInTheOther(
                                        getElfSectionIds(sections[0]),
                                        getElfSectionIds(sections[1])
                                ) ? 1 : 0
                )
                .reduce(0, Integer::sum);
    }

    private static boolean isOneAssignmentRangeFullyContainedInTheOther(Set<Integer> firstElfSectionIds, Set<Integer> secondElfSectionIds) {
        return firstElfSectionIds.containsAll(secondElfSectionIds) || secondElfSectionIds.containsAll(firstElfSectionIds);
    }

    private static Set<Integer> getElfSectionIds(String sectionRange) {
        String[] rangeLimits = sectionRange.split("-");
        return IntStream.range(Integer.parseInt(rangeLimits[0]), Integer.parseInt(rangeLimits[1]) + 1)
                .boxed()
                .collect(Collectors.toSet());
    }
}
