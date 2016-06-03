/**
 * Created by Abylay.Sabirgaliyev
 * Created: 01.06.2016 16:25
 * Copyright © LLP JazzSoft
 */
public class ParallelQuickSortNonLock<T extends Comparable<T>> extends Thread {
    T[] doubleArray;
    int low, high;

    public ParallelQuickSortNonLock(T[] arr){
        this.doubleArray = arr;
        this.low = 0;
        this.high = doubleArray.length - 1;
    }

    public ParallelQuickSortNonLock(T[] arr, int low, int high){
        this.doubleArray = arr;
        this.low = low;
        this.high = high;
    }

    public void run() {
        System.out.println("Starting thread " + this.getName());
        try {
            quickSort(doubleArray, low, high);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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

    public <T extends Comparable<T>> void quickSort(T[] arr, int left, int right) throws InterruptedException {
        int index = partition(arr, left, right);
        if (left < index - 1) {
            ParallelQuickSortNonLock sort = new ParallelQuickSortNonLock(arr, left, index - 1);
            sort.start();
            sort.join();
        }
        if (index < right) {
            ParallelQuickSortNonLock sort = new ParallelQuickSortNonLock(arr, index, right);
            sort.start();
            sort.join();
        }
    }
}
