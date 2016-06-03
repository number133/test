import java.util.Arrays;

/**
 * Created by Abylay.Sabirgaliyev
 * Created: 17.05.2016 11:33
 * Copyright © LLP JazzSoft
 */
public class Conc {
    public static void main(String[] args) throws InterruptedException {
        Object lock = new Object();
        Double[] array = {68.37, 141.12, 156.68, 180.96, 107.19, 30.63, 159.67, 104.41, 177.12, 161.04, 40.1, 96.5, 143.64, 87.17, 154.42, 65.02, 100.66, 69.64, 142.32, 155.4, 23.15, 70.32, 146.41, 59.71, 129.02, 82.04, 54.06, 32.17, 182.95, 110.62, 128.26, 38.95, 34.04, 95.01, 167.15, 35.43, 160.66, 38.08, 176.5, 97.19, 73.42, 56.73, 187.13, 30.88, 59.57, 78.53, 52.86, 124.74, 147.2, 163.55, 67.52, 12.7, 56.83, 91.79, 113.99, 102.63, 115.68, 171.9, 134.46, 162.47, 114.57, 195.5, 41.34, 119.76, 23.14, 59.35, 39.27, 128.44, 92.01, 71.5, 121.2, 22.73, 125.34, 96.46, 91.37, 99.04, 60.88, 107.55, 65.51, 97.15, 72.84, 8.01, 187.08, 198.32, 72.16, 90.92, 108.76, 57.53, 181.46, 24.01, 113.9, 61.47, 185.42, 62.08, 78.94, 82.29, 94.72, 82.01, 165.92, 8.86};
        long start = System.nanoTime();
        ParallelQuickSortNonLock sort = new ParallelQuickSortNonLock(array);
//        QuickSort sort = new QuickSort(array);
        sort.start();
        sort.join();
        long time = System.nanoTime() - start;
        System.out.println("\nTime is: " + time);
        System.out.println(Arrays.asList(array));
    }
}
