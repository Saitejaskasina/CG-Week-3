import java.util.*;
public class twosum {
 public static int[] findTwoSum(int[] nums, int target) {
  Map<Integer, Integer> map = new HashMap<>();
  for (int i = 0; i < nums.length; i++) {
   int diff = target - nums[i];
   if (map.containsKey(diff)) {
    return new int[]{map.get(diff), i};
   }
   map.put(nums[i], i);
  }
  return new int[]{-1, -1};
 }
 public static void main(String[] args) {
  int[] nums = {2, 7, 11, 15};
  int target = 9;
  int[] result = findTwoSum(nums, target);
  System.out.println(Arrays.toString(result));
 }
}
