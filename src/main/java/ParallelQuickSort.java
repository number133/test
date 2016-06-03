/**
 * Created by Abylay.Sabirgaliyev
 * Created: 01.06.2016 15:53
 * Copyright © LLP JazzSoft
 */
public class ParallelQuickSort<T extends Comparable<T>> extends Thread {
    T[] doubleArray;
    int low, high;
    Object parent;

    public ParallelQuickSort(T[] arr, Object parent){
        this.parent = parent;
        this.doubleArray = arr;
        this.low = 0;
        this.high = doubleArray.length - 1;
    }

    public ParallelQuickSort(T[] arr, int low, int high, Object parent){
        this.parent = parent;
        this.doubleArray = arr;
        this.low = low;
        this.high = high;
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
        synchronized (parent) {
            int index = partition(arr, left, right);
            if (left < index - 1) {
                ParallelQuickSort sort = new ParallelQuickSort(arr, left, index - 1, this);
                sort.start();
            }
            if (index < right) {
                ParallelQuickSort sort = new ParallelQuickSort(arr, index, right, this);
                sort.start();
            }
        }
    }
}