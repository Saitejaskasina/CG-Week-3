
public class PeakElement {
    public static int findPeak(int[] arr) {
        int left = 0, right = arr.length - 1;
        while (left < right) {
            int mid = (left + right) / 2;
            if (arr[mid] < arr[mid + 1]) 
                left = mid + 1;
            else 
                right = mid;
        }
        return arr[left];
    }
}

