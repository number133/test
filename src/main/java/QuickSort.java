/**
 * Created by Abylay.Sabirgaliyev
 * Created: 01.06.2016 15:29
 * Copyright © LLP JazzSoft
 */
public class QuickSort<T extends Comparable<T>> extends Thread {
    T[] doubleArray;
    int low, high;

    public QuickSort(T[] arr){
        this.doubleArray = arr;
        this.low = 0;
        this.high = doubleArray.length - 1;
    }

    public void run() {
        System.out.println("Starting thread " + this.getName());
        quickSort(doubleArray, low, high);
    }

    public <T extends Comparable<T>> int partition(T[] arr, int a, int b) {
        int left = a;
        int right = b;
        T x = arr[(left + right) / 2];

        while (left <= right) {
            while (arr[left].compareTo(x) < 0) left++;
            while (x.compareTo(arr[right]) < 0) right--;

            if (left <= right) {
                T tmp = arr[left];
                arr[left] = arr[right];
                arr[right] = tmp;
                left++;
                right--;
            }
        }
        return left;
    }

    public <T extends Comparable<T>> void quickSort(T[] arr, int left, int right) {
        int index = partition(arr, left, right);
        if (left < index - 1)
            quickSort(arr, left, index - 1);
        if (index < right)
            quickSort(arr, index, right);
    }
}