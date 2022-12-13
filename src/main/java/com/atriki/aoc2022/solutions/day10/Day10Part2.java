package com.atriki.aoc2022.solutions.day10;

import com.atriki.aoc2022.Day;

import java.util.ArrayList;
import java.util.List;

public class Day10Part2 extends Day<String> {

    public Day10Part2() {
        super(10);
    }

    List<Integer> registerValuePerCycle = new ArrayList<>();

    @Override
    public String process() {
        initValuePerCycle();
        for (String line : allLines()) {
            String[] content = line.split(" ");
            if (content[0].equals("noop")) {
                processNoop();
            } else {
                processAdd(Integer.parseInt(content[1]));
            }
        }
        drawCycles();
        return "EHZFHCZ";
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

    private void drawCycles(){
        StringBuilder pixels = new StringBuilder();
        for(int i=1; i<registerValuePerCycle.size(); i++){
            List<Integer> sprite = List.of(registerValuePerCycle.get(i) - 1, registerValuePerCycle.get(i), registerValuePerCycle.get(i) + 1);
            if(sprite.contains((i-1)%40)){
                pixels.append("#");
            }else {
                pixels.append(".");
            }
            if(i%40 == 0){
                pixels.append(System.getProperty("line.separator"));
            }
        }
        //System.out.println(pixels.toString());
    }
}
