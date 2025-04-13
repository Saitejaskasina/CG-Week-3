
import java.util.*;
public class ZeroSumSubarrays {
 public static List<int[]> findZeroSumSubarrays(int[] arr) {
  Map<Integer, List<Integer>> map = new HashMap<>();
  List<int[]> result = new ArrayList<>();
  int sum = 0;
  map.put(0, new ArrayList<>(List.of(-1)));
  for (int i = 0; i < arr.length; i++) {
   sum += arr[i];
   if (map.containsKey(sum)) {
    for (int index : map.get(sum)) {
     result.add(new int[]{index + 1, i});
    }
   }
   map.computeIfAbsent(sum, k -> new ArrayList<>()).add(i);
  }
  return result;
 }

 public static void main(String[] args) {
  int[] arr = {3, 4, -7, 3, 1, 3, 1, -4, -2, -2};
  List<int[]> subarrays = findZeroSumSubarrays(arr);
  for (int[] sub : subarrays) {
   System.out.println("[" + sub[0] + ", " + sub[1] + "]");
  }
 }
}