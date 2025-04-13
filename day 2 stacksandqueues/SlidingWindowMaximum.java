import java.util.Deque;
import java.util.LinkedList;

public class SlidingWindowMaximum {
 public static int[] maxSlidingWindow(int[] nums, int k) {
  int n = nums.length;
  if (n * k == 0) return new int[0];
  if (k == 1) return nums;
  int[] result = new int[n - k + 1];
  Deque<Integer> deque = new LinkedList<>();
  for (int i = 0; i < n; i++) {
   if (!deque.isEmpty() && deque.peekFirst() < i - k + 1) {
    deque.pollFirst();
   }
   while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
    deque.pollLast();
   }
   deque.offerLast(i);
   if (i >= k - 1) {
    result[i - k + 1] = nums[deque.peekFirst()];
   }
  }
  return result;
 }

 public static void main(String[] args) {
  int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
  int k = 3;
  int[] result = maxSlidingWindow(nums, k);

  for (int val : result) {
   System.out.print(val + " ");
  }
 }
}
