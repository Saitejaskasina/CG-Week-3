import java.util.Arrays;

public class SearchPerformance {
    public static void main(String[] args) {
        int[] datasetSizes = {1000, 10000, 1000000};
        int target = 500;  
        for (int size : datasetSizes) {
            int[] dataset = generateDataset(size);  
            long linearSearchStartTime = System.nanoTime();
            linearSearch(dataset, target);
            long linearSearchEndTime = System.nanoTime();
            long linearSearchTime = linearSearchEndTime - linearSearchStartTime;

            long binarySearchStartTime = System.nanoTime();
            Arrays.sort(dataset); 
            binarySearch(dataset, target);
            long binarySearchEndTime = System.nanoTime();
            long binarySearchTime = binarySearchEndTime - binarySearchStartTime;
            System.out.println("Dataset Size: " + size);
            System.out.println("Linear Search Time: " + linearSearchTime / 1000000.0 + " ms");
            System.out.println("Binary Search Time: " + binarySearchTime / 1000000.0 + " ms");
            System.out.println();
        }
    }
    public static int linearSearch(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return i;
            }
        }
        return -1;
    }
    public static int binarySearch(int[] arr, int target) {
        int left = 0, right = arr.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }
    public static int[] generateDataset(int size) {
        int[] dataset = new int[size];
        for (int i = 0; i < size; i++) {
            dataset[i] = i + 1;  
        }
        return dataset;
    }
}

