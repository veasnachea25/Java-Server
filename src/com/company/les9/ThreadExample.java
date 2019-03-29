package com.company.les9;

public class ThreadExample {
    public static void main(String[] args) {
        Thread child = new Thread(){
            @Override
            public void run() {
                while (true) {
                    System.out.println("Child");
                }
            }
        };
        child.setPriority(Thread.MAX_PRIORITY);

        child.start();
        for (int i = 0; i < 100; i++) {
            System.out.println("Main");
        }
        child.stop();
    }
}
