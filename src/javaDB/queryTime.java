package javaDB;

public class queryTime {
    public static long startTime;
    public static long stopTime;
    public static long elapsedTime;

    public static long getStartTime() {
        startTime=System.nanoTime();
        return startTime;
    }

    public static long getStopTime() {
        stopTime=System.nanoTime();
        return stopTime;
    }

    public static long getElapsedTime() {
        elapsedTime=stopTime - startTime;
        return elapsedTime;
    }
}
