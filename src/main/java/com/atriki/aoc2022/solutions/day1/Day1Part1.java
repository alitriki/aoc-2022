package com.atriki.aoc2022.solutions.day1;

import com.atriki.aoc2022.Day;

import java.util.ArrayList;
import java.util.List;

public class Day1Part1 extends Day<Integer> {

    public Day1Part1() {
        super(1);
    }

    @Override
    public Integer process() {
        List<Integer> caloriesByElf = getCaloriesPerElf(allLines());
        return caloriesByElf.stream()
                .max(Integer::compareTo)
                .orElseThrow(IllegalArgumentException::new);
    }

    private List<Integer> getCaloriesPerElf(List<String> allCalories) {
        List<Integer> caloriesByElf = new ArrayList<>();
        int index = 0;
        for (String calorie : allCalories) {
            if (calorie.equals("")) {
                index++;
            } else if (caloriesByElf.size() > index) {
                caloriesByElf.set(index, caloriesByElf.get(index) + Integer.parseInt(calorie));
            } else {
                caloriesByElf.add(index, Integer.parseInt(calorie));
            }
        }
        return caloriesByElf;
    }
}
