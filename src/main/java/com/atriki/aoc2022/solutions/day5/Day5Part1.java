package com.atriki.aoc2022.solutions.day5;

import com.atriki.aoc2022.Day;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Day5Part1 extends Day<String> {

    public Day5Part1() {
        super(5);
    }

    Matcher matcher;
    List<Stack<Character>> allStacks = new ArrayList<>();

    @Override
    public String process() {
        List<String> stackLines = allLines().stream()
                .filter(line -> line.contains("["))
                .collect(Collectors.toList());
        Collections.reverse(stackLines);

        String regex = "\\[([^\\]]+)\\]|\\s\\s\\s\\s";
        Pattern pattern = Pattern.compile(regex);

        matcher = pattern.matcher(stackLines.get(0));
        while (matcher.find()) {
            allStacks.add(new Stack<>());
        }

        fillCrateStacks(stackLines, pattern);

        String instructionsRegex = "(\\d+)";
        Pattern instructionsPattern = Pattern.compile(instructionsRegex);
        allLines().stream()
                .filter(line -> line.startsWith("move"))
                .map(line -> getInstruction(instructionsPattern, line))
                .forEach(this::executeInstruction);

        return allStacks.stream()
                .map(Stack::peek)
                .map(Object::toString)
                .collect(Collectors.joining());
    }

    private Instruction getInstruction(Pattern instructionsPattern, String line) {
        matcher = instructionsPattern.matcher(line);
        matcher.find();
        int number = Integer.parseInt(matcher.group());
        matcher.find();
        int from = Integer.parseInt(matcher.group()) - 1;
        matcher.find();
        int to = Integer.parseInt(matcher.group()) - 1;
        return new Instruction(number, from, to);
    }

    private void fillCrateStacks(List<String> stackLines, Pattern pattern) {
        for (String stack : stackLines) {
            int i = 0;
            matcher = pattern.matcher(stack);
            while (matcher.find()) {
                if (matcher.group().charAt(1) != ' ') {
                    allStacks.get(i).push(matcher.group().charAt(1));
                }
                i++;
            }
        }
    }

    private void executeInstruction(Instruction instruction) {
        for (int i = 0;
             i < instruction.number;
             i++) {
            Character crate = allStacks.get(instruction.from).pop();
            allStacks.get(instruction.to).push(crate);
        }
    }

    private record Instruction(int number, int from, int to) {
    }
}
