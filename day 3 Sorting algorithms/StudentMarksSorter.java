import java.util.Scanner;
import java.util.Arrays;
public class StudentMarksSorter {
 public static void bubbleSort(int[] marks) {
  int n = marks.length;
  boolean swapped;
  for (int i = 0; i < n - 1; i++) {
   swapped = false;
   for (int j = 0; j < n - i - 1; j++) {
    if (marks[j] > marks[j + 1]) {
     int temp = marks[j];
     marks[j] = marks[j + 1];
     marks[j + 1] = temp;
     swapped = true;
    }
   }
   if (!swapped) break;
  }
 }
 public static void main(String[] args) {
  Scanner scanner = new Scanner(System.in);
  System.out.print("Enter number of students: ");
  int n = scanner.nextInt();
  int[] marks = new int[n];
  System.out.println("Enter marks of " + n + " students:");
  for (int i = 0; i < n; i++) {
   marks[i] = scanner.nextInt();
  }
  bubbleSort(marks);
  System.out.println("Sorted student marks: " + Arrays.toString(marks));
 }
}