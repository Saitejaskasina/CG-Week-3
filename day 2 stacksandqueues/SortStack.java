import java.util.Stack;
public class SortStack {
 public static void sort(Stack<Integer> stack) {
  if (!stack.isEmpty()) {
   int temp = stack.pop();
   sort(stack);
   insertInSortedOrder(stack, temp);
  }
 }
 private static void insertInSortedOrder(Stack<Integer> stack, int value) {
  if (stack.isEmpty() || stack.peek() <= value) {
   stack.push(value);
  } else {
   int temp = stack.pop();
   insertInSortedOrder(stack, value);
   stack.push(temp);
  }
 }
 public static void main(String[] args) {
  Stack<Integer> stack = new Stack<>();
  stack.push(30);
  stack.push(-5);
  stack.push(18);
  stack.push(14);
  stack.push(-3);
  sort(stack);
  while (!stack.isEmpty()) {
   System.out.println(stack.pop());
  }
 }
}