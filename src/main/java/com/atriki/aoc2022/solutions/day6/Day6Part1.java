package com.atriki.aoc2022.solutions.day6;

import com.atriki.aoc2022.Day;

public class Day6Part1 extends Day<Integer> {

    public Day6Part1() {
        super(6);
    }

    @Override
    public Integer process() {
        return findFirstFourContiguousCharacters(allLines().get(0));
    }

    public int findFirstFourContiguousCharacters(String input) {
        for (int i = 0; i < input.length(); i++) {
            boolean charactersAreDifferent = checkAllCharactersAreDifferent(input.substring(i, i + 4));
            if (charactersAreDifferent) {
                return i + 4;
            }
        }
        throw new IllegalArgumentException("¿qué pasa?");
    }

    private boolean checkAllCharactersAreDifferent(String characters) {
        return characters.length() == characters.chars().distinct().count();
    }
}
