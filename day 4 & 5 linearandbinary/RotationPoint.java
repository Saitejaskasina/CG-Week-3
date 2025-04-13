
public class RotationPoint {
    public static int findRotation(int[] arr) {
        int left = 0, right = arr.length - 1;
        while (left < right) {
            int mid = (left + right) / 2;
            if (arr[mid] > arr[right]) 
                left = mid + 1;
            else 
                right = mid;
        }
        return left;
    }
}

