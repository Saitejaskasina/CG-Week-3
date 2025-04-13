
import java.util.Arrays;
import java.util.HashSet;

public class CombinedSearch {
    public static int findMissingPositive(int[] arr) {
        HashSet<Integer> set = new HashSet<>();
        for (int num : arr) {
            if (num > 0) set.add(num);
        }
        int i = 1;
        while (true) {
            if (!set.contains(i)) return i;
            i++;
        }
    }
    public static int binarySearch(int[] arr, int target) {
        Arrays.sort(arr);
        int left = 0, right = arr.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (arr[mid] == target) return mid;
            else if (arr[mid] < target) left = mid + 1;
            else right = mid - 1;
        }
        return -1; 
    }
    public static void main(String[] args) {
        int[] arr1 = {3, 4, -1, 1};
        System.out.println("First missing positive: " + findMissingPositive(arr1));
        int[] arr2 = {1, 2, 3, 4, 5};
        int target = 3;
        int result = binarySearch(arr2, target);
        if (result != -1) {
            System.out.println("Target found at index: " + result);
        } else {
            System.out.println("Target not found");
        }
    }
}

