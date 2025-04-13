import java.util.Scanner;
import java.util.Arrays;
public class ExamScoreSorter {
 public static void selectionSort(int[] scores) {
  int n = scores.length;
  for (int i = 0; i < n - 1; i++) {
   int minIndex = i;
   for (int j = i + 1; j < n; j++) {
    if (scores[j] < scores[minIndex]) {
     minIndex = j;
    }
   }
   int temp = scores[i];
   scores[i] = scores[minIndex];
   scores[minIndex] = temp;
  }
 }
 public static void main(String[] args) {
  Scanner scanner = new Scanner(System.in);
  System.out.print("Enter number of students: ");
  int n = scanner.nextInt();
  int[] scores = new int[n];
  System.out.println("Exam scores:");
  for (int i = 0; i < n; i++) {
   scores[i] = scanner.nextInt();
  }
  selectionSort(scores);
  System.out.println("Sorted exam scores: " + Arrays.toString(scores));
 }
}