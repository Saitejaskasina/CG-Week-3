
import java.util.Stack;
class QueueUsingStacks {
 Stack<Integer> stack1 = new Stack<>();
 Stack<Integer> stack2 = new Stack<>();
 public void enqueue(int value) {
  stack1.push(value);
 }
 public int dequeue() {
  if (stack2.isEmpty()) {
   while (!stack1.isEmpty()) {
    stack2.push(stack1.pop());
   }
  }
  if (stack2.isEmpty()) {
   throw new RuntimeException("Queue is empty");
  }
  return stack2.pop();
 }
 public int peek() {
  if (stack2.isEmpty()) {
   while (!stack1.isEmpty()) {
    stack2.push(stack1.pop());
   }
  }
  if (stack2.isEmpty()) {
   throw new RuntimeException("Queue is empty");
  }
  return stack2.peek();
 }
 public boolean isEmpty() {
  return stack1.isEmpty() && stack2.isEmpty();
 }
}
public class QueueUsingStack {
 public static void main(String[] args) {
  QueueUsingStacks queue = new QueueUsingStacks();
  queue.enqueue(10);
  queue.enqueue(20);
  queue.enqueue(30);
  System.out.println(queue.dequeue());
  System.out.println(queue.peek());
  queue.enqueue(40);
  System.out.println(queue.dequeue());
  System.out.println(queue.dequeue());
  System.out.println(queue.dequeue());
 }
}

