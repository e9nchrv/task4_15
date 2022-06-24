package com.company;

import java.util.Arrays;

import static com.company.Main.Heapsort;
import static com.company.Main.arraysSort;

public class Test {
    public static void testArraysSort(double[][] points, double[][] answer, int amount) throws Exception {

        double[][] myRes = arraysSort(points, amount);

        if (Arrays.deepEquals(myRes, answer)) {
            System.out.print("Test Passed; ");
        } else {
            System.out.print("Test Failed; ");
        }
        System.out.println("Correct answer is " +  Arrays.deepToString(answer) + ", my answer is " + Arrays.deepToString(myRes));
    }

    public static void testHeapSort(double[][] points, double[][] answer, int amount) throws Exception {
        if (amount > points.length) {
            throw new Exception("Fewer points than entered");
        }
        Heapsort(points);
        double[][] resultArray = Arrays.copyOfRange(points, 0, amount);

        if (Arrays.deepEquals(resultArray, answer)) {
            System.out.print("Test Passed; ");
        } else {
            System.out.print("Test Failed; ");
        }
        System.out.println("Correct answer is " +  Arrays.deepToString(answer) + ", my answer is " + Arrays.deepToString(resultArray));
    }
}
