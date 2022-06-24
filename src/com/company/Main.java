package com.company;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static double distanceToOrigin(double xCoordinate, double yCoordinate) {
        return Math.sqrt(Math.pow(xCoordinate, 2) + Math.pow(yCoordinate, 2));
    }

    private static void siftDown(double[][] data, int k, int n) {
        double value = distanceToOrigin(data[k][0], data[k][1]);
        double valueX = data[k][0];
        double valueY = data[k][1];
        while (true) {  // пока у a[k] есть потомки в пирамиде размера n
            int childIndex = 2 * k + 1;
            // останавливаемся, если в пирамиде размером n у [k] нет потомков
            if (childIndex >= n) {
                break;
            }
            //  выбираем большего потомка
            if (childIndex + 1 < n && distanceToOrigin(data[childIndex + 1][0], data[childIndex + 1][1]) > distanceToOrigin(data[childIndex][0], data[childIndex][1])) {
                childIndex++;
            }
            // останавливаетмся, если добавляемый элемент больше очередного потомка
            if (value > distanceToOrigin(data[childIndex][0], data[childIndex][1])) {
                break;
            }
            // иначе
            data[k][0] = data[childIndex][0];
            data[k][1] = data[childIndex][1];// переносим потомка наверх
            k = childIndex;
        }
        data[k][0] = valueX;
        data[k][1] = valueY;// устанавливаем "просеиваемый" элемен на свое место
    }

    public static void Heapsort(double[][] data) {
        int heapSize = data.length;

        // начальное построение пирамиды:
        // начинаем с heapSize / 2, т.к. это последний элемент в пирамиде,
        // у которого могут быть потомки
        for (int i = heapSize / 2; i >= 0; i--) {
            siftDown(data, i, heapSize);
        }

        // собственно сортировка:
        // в цикле обмен [0] c последним элементом и просеивание [0]
        while (heapSize > 1) {
            // перенос (обмен) вершины стека [0] в конец сортируемой части массива [heapSize - 1]
            double tmp1 = data[heapSize - 1][0];
            double tmp2 = data[heapSize - 1][1];
            data[heapSize - 1][0] = data[0][0];
            data[heapSize - 1][1] = data[0][1];
            data[0][0] = tmp1;
            data[0][1] = tmp2;
            // уменьшаем размер пирамиды
            heapSize--;
            // просеиваем элемент [0]
            siftDown(data, 0, heapSize);
        }
    }
    public static double[][] arraysSort(double[][] points, int amount) throws Exception {
        if (amount > points.length) {
            throw new Exception("Fewer points than entered");
        }
        double[] distanceArray = new double[points.length];
        for (int i = 0; i < distanceArray.length; i++) {
            distanceArray[i] = distanceToOrigin(points[i][0], points[i][1]);
        }
        double[] distanceArrayID = new double[points.length];
        for (int i = 0; i < distanceArray.length; i++) {
            distanceArrayID[i] = distanceToOrigin(points[i][0], points[i][1]) + i;
        }
        Arrays.sort(distanceArray);
        Arrays.sort(distanceArrayID);

        double[] array = Arrays.copyOfRange(distanceArray, 0, amount);
        double[] arrayID = Arrays.copyOfRange(distanceArrayID, 0, amount);
        double[][] res = new double[amount][2];
        for (int i = 0; i < amount; i++) {
            res[i][0] = points[(int) (arrayID[i] - array[i])][0];
            res[i][1] = points[(int) (arrayID[i] - array[i])][1];
        }
        return res;
    }

    public static void main(String[] args) throws Exception {

        Test.testArraysSort(new double[][]{{3, 4}, {6, 0}, {9, 1}, {3, 6}, {4, 7}}, new double[][]{{3, 4}, {6, 0}, {3, 6}}, 3);
        Test.testArraysSort(new double[][]{{0, 0}, {1, 0}, {2, 0}, {3, 0}, {3, 0}}, new double[][]{{0, 0}, {1, 0}}, 2);
        Test.testArraysSort(new double[][]{{3, 4}}, new double[][]{{3, 4}}, 1);
        Test.testArraysSort(new double[][]{{12, 8}, {-6, 0}, {8, 1}, {3, 9}, {4, 7}}, new double[][]{{-6, 0}, {8, 1}, {4, 7}}, 3);
        Test.testHeapSort(new double[][]{{3, 4}, {6, 0}, {9, 1}, {3, 6}, {4, 7}}, new double[][]{{3, 4}, {6, 0}, {3, 6}}, 3);
        Test.testHeapSort(new double[][]{{0, 0}, {1, 0}, {2, 0}, {3, 0}, {3, 0}}, new double[][]{{0, 0}, {1, 0}}, 2);
        Test.testHeapSort(new double[][]{{3, 4}}, new double[][]{{3, 4}}, 1);
        Test.testHeapSort(new double[][]{{12, 8}, {-6, 0}, {8, 1}, {3, 9}, {4, 7}}, new double[][]{{-6, 0}, {8, 1}, {4, 7}}, 3);
    }
}
