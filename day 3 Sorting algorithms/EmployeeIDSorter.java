import java.util.Scanner;
import java.util.Arrays;

public class EmployeeIDSorter {
 public static void insertionSort(int[] ids) {
  int n = ids.length;
  for (int i = 1; i < n; i++) {
   int key = ids[i];
   int j = i - 1;
   while (j >= 0 && ids[j] > key) {
    ids[j + 1] = ids[j];
    j--;
   }
   ids[j + 1] = key;
  }
 }

 public static void main(String[] args) {
  Scanner scanner = new Scanner(System.in);
  System.out.print("Enter number of employees: ");
  int n = scanner.nextInt();
  int[] ids = new int[n];

  System.out.println("Enter " + n + " employee IDs:");
  for (int i = 0; i < n; i++) {
   ids[i] = scanner.nextInt();
  }

  insertionSort(ids);
  System.out.println("Sorted employee IDs: " + Arrays.toString(ids));
 }
}