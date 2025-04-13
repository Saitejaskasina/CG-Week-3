import java.util.Arrays;
public class SortingPerformance {
    public static void main(String[] args) {
        int[] datasetSizes = {1000, 10000, 1000000};          
        for (int size : datasetSizes) {
            int[] dataset = generateDataset(size); 
            int[] bubbleSortDataset = Arrays.copyOf(dataset, dataset.length);
            long bubbleSortStartTime = System.nanoTime();
            bubbleSort(bubbleSortDataset);
            long bubbleSortEndTime = System.nanoTime();
            long bubbleSortTime = bubbleSortEndTime - bubbleSortStartTime;
            int[] mergeSortDataset = Arrays.copyOf(dataset, dataset.length);
            long mergeSortStartTime = System.nanoTime();
            mergeSort(mergeSortDataset, 0, mergeSortDataset.length - 1);
            long mergeSortEndTime = System.nanoTime();
            long mergeSortTime = mergeSortEndTime - mergeSortStartTime;
            int[] quickSortDataset = Arrays.copyOf(dataset, dataset.length);
            long quickSortStartTime = System.nanoTime();
            quickSort(quickSortDataset, 0, quickSortDataset.length - 1);
            long quickSortEndTime = System.nanoTime();
            long quickSortTime = quickSortEndTime - quickSortStartTime;
            System.out.println("Dataset Size: " + size);
            System.out.println("Bubble Sort Time: " + bubbleSortTime / 1000000.0 + " ms");
            System.out.println("Merge Sort Time: " + mergeSortTime / 1000000.0 + " ms");
            System.out.println("Quick Sort Time: " + quickSortTime / 1000000.0 + " ms");
            System.out.println();
        }
    }
    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }
    public static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }
    public static void merge(int[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;
        int[] leftArr = new int[n1];
        int[] rightArr = new int[n2];
        System.arraycopy(arr, left, leftArr, 0, n1);
        System.arraycopy(arr, mid + 1, rightArr, 0, n2);
        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (leftArr[i] <= rightArr[j]) {
                arr[k] = leftArr[i];
                i++;
            } else {
                arr[k] = rightArr[j];
                j++;
            }
            k++;
        }
        while (i < n1) {
            arr[k] = leftArr[i];
            i++;
            k++;
        }
        while (j < n2) {
            arr[k] = rightArr[j];
            j++;
            k++;
        }
    }
    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }
        public static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;
        
        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }
    public static int[] generateDataset(int size) {
        int[] dataset = new int[size];
        for (int i = 0; i < size; i++) {
            dataset[i] = (int) (Math.random() * 10000);
        }
        return dataset;
    }
}
