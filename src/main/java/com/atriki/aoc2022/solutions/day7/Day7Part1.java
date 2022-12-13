package com.atriki.aoc2022.solutions.day7;

import com.atriki.aoc2022.Day;

import java.util.ArrayList;
import java.util.List;

public class Day7Part1 extends Day<Integer> {

    public Day7Part1() {
        super(7);
    }

    Directory root = new Directory("/");
    int totalSizesOfSmallDirectories = 0;

    @Override
    public Integer process() {
        readTerminal(allLines());
        calculateSizes(root);
        return totalSizesOfSmallDirectories;
    }

    private int calculateSizes(Directory current) {
        int directFileSizes = current.files.stream().map(File::size).reduce(0, Integer::sum);
        int childDirectoriesFileSizes =0;
        for (Directory child : current.subDirectories) {
            childDirectoriesFileSizes += calculateSizes(child);
        }
        int totalFilesSize = directFileSizes + childDirectoriesFileSizes;
        if(totalFilesSize < 100000){
            totalSizesOfSmallDirectories += totalFilesSize;
        }
        return directFileSizes + childDirectoriesFileSizes;
    }

    private void readTerminal(List<String> allLines) {
        Directory current = root;
        for (int i = 1; i < allLines.size(); i++) {
            if (allLines.get(i).startsWith("$ cd ")) {
                String directoryName = allLines.get(i).split("cd ")[1];
                if (!directoryName.equals("..") && current.subDirectories.stream().noneMatch(subdirectory -> subdirectory.getName().equals(directoryName))) {
                    current.subDirectories.add(new Directory(directoryName, current));
                }
                current = current.navigate(directoryName);
            } else if (allLines.get(i).startsWith("$ ls")) {
                List<String> contents = allLines.subList(i + 1, allLines.size()).stream()
                        .takeWhile(item -> !item.startsWith("$"))
                        .toList();
                current.fillContents(contents);
                i += contents.size();
            }
        }
    }

    public interface FileSystem {}

    public class Directory implements FileSystem {
        private String name;
        private Directory parentDirectory;
        private List<Directory> subDirectories = new ArrayList<>();
        private List<File> files = new ArrayList<>();

        public Directory() {
        }

        public Directory(String name) {
            this.name = name;
        }

        public Directory(String name, Directory parentDirectory) {
            this.name = name;
            this.parentDirectory = parentDirectory;
        }

        public Directory(String name, Directory parentDirectory, List<Directory> subDirectories, List<File> files) {
            this.name = name;
            this.parentDirectory = parentDirectory;
            this.subDirectories = subDirectories;
            this.files = files;
        }

        public String getName() {
            return name;
        }

        public Directory getParentDirectory() {
            return parentDirectory;
        }

        public List<Directory> getSubDirectories() {
            return subDirectories;
        }

        public List<File> getFiles() {
            return files;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setParentDirectory(Directory parentDirectory) {
            this.parentDirectory = parentDirectory;
        }

        public void setSubDirectories(List<Directory> subDirectories) {
            this.subDirectories = subDirectories;
        }

        public void setFiles(List<File> files) {
            this.files = files;
        }

        public Directory navigate(String name) {
            if (name.equals("..")) {
                return parentDirectory;
            } else {
                return subDirectories.stream()
                        .filter(subdirectory -> subdirectory.getName().equals(name))
                        .findFirst().orElseThrow(IllegalArgumentException::new);
            }
        }

        public void fillContents(List<String> contents) {
            for (String content : contents) {
                String[] fileOrFolder = content.split(" ");
                if (fileOrFolder[0].equals("dir")) {
                    subDirectories.add(new Directory(fileOrFolder[1], this));
                } else {
                    files.add(new File(Integer.parseInt(fileOrFolder[0]), fileOrFolder[1]));
                }
            }
        }
    }

    private record File(int size, String name) implements FileSystem {}
}
