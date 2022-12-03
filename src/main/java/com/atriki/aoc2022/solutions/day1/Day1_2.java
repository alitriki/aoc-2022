package com.atriki.aoc2022.solutions.day1;

import com.atriki.aoc2022.Day;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day1_2 extends Day<Integer> {

    public Day1_2() {
        super(1);
    }

    @Override
    public Integer process() {
        List<Integer> caloriesByElf = getCaloriesPerElf(allLines());
        caloriesByElf.sort(Integer::compareTo);
        Collections.reverse(caloriesByElf);
        return caloriesByElf.stream()
                .limit(3)
                .reduce(0, Integer::sum);
    }

    private List<Integer> getCaloriesPerElf(List<String> allCalories){
        List<Integer> caloriesByElf = new ArrayList<>();
        int index = 0;
        for (String calorie : allCalories) {
            if (calorie.equals("")) {
                index++;
            } else if (caloriesByElf.size() > index) {
                caloriesByElf.set(index, caloriesByElf.get(index) + Integer.parseInt(calorie));
            }else {
                caloriesByElf.add(index, Integer.parseInt(calorie));
            }
        }
        return caloriesByElf;
    }
}
