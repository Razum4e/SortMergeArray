package org.example;

//        Задача
//
//        Функция сортирующая массив элементов A:
//        Sort(A,p,r)
//        1 if p < r
//        2    then           q := round_half_down((p+r)/2)
//        3                       Sort(A,p,q)
//        4                       Sort(A,q+1,r)
//        5                       Merge(A,p,q,r)
//
//        Пример массива:
//        A = (5,2,4,6,1,3,2,6)
//
//        Пример запуска:
//        Sort(A,1,length[A])
//        Необходимо:
//        Разработать алгоритм функции Merge(A,p,q,r)

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] ints = {5, 2, 4, 6, 1, 3, 2, 6};
        Array array = new Array(ints);
        array.printArray();
        array.sort();
        array.printArray();
    }
}

class Array {
    private final int[] ints;

    public Array(int[] ints) {
        this.ints = ints;
    }

    public void printArray() {
        System.out.println(Arrays.toString(ints));
    }

    public void sort() {
        int[] workInts = new int[ints.length];
        mergeSort(workInts, 0, ints.length - 1);
    }

    private void mergeSort(int[] workInts, int lower, int upper) {
        if (lower != upper) {
//            int middle = (lower + upper) / 2;
            int middle = BigDecimal.valueOf(lower + upper)
                    .divide(BigDecimal.valueOf(2), RoundingMode.HALF_DOWN)
                    .intValue();
            mergeSort(workInts, lower, middle);
            mergeSort(workInts, middle + 1, upper);
            merge(workInts, lower, middle + 1, upper);
        }
    }

    private void merge(int[] workInts, int lower, int middle, int upper) {
        int count = 0;
        int low = lower;
        int mid = middle - 1;
        int quantity = upper - lower + 1;
        while (lower <= mid && middle <= upper)
            if (ints[lower] < ints[middle])
                workInts[count++] = ints[lower++];
            else
                workInts[count++] = ints[middle++];
        while (lower <= mid)
            workInts[count++] = ints[lower++];
        while (middle <= upper)
            workInts[count++] = ints[middle++];
        for (count = 0; count < quantity; count++)
            ints[low + count] = workInts[count];
    }
}
