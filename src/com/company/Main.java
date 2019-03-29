package com.company;

import java.io.File;

public class Main {

    public static void main(String[] args) {

        File folder = new File("C:\\Users\\DELL\\IdeaProjects\\Server\\src\\com\\company\\les9\\files");
        for (File file : folder.listFiles()) {
            System.out.println(file.getName());
        }

    }
}
