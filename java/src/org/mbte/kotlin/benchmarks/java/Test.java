package org.mbte.kotlin.benchmarks.java;

public class Test implements Runnable{
    @Override
    public void run() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) { //
        }
    }
}
