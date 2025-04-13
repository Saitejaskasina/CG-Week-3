public class FirstLastOccurrence {
    public static int[] findOccurrences(int[] arr, int target) {
        int first = -1, last = -1;
        int l = 0, r = arr.length - 1;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (arr[mid] < target) l = mid + 1;
            else {
                if (arr[mid] == target) first = mid;
                r = mid - 1;
            }
        }
        l = 0; r = arr.length - 1;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (arr[mid] > target) r = mid - 1;
            else {
                if (arr[mid] == target) last = mid;
                l = mid + 1;
            }
        }
        return new int[]{first, last};
    }
    public static void main(String[] args) {
        int[] arr = {1, 2, 2, 2, 3, 4, 5};
        int target = 2;
        int[] result = findOccurrences(arr, target);
        System.out.println("First occurrence: " + result[0]);
        System.out.println("Last occurrence: " + result[1]);
    }
}