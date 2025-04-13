import java.util.Scanner;
import java.util.Arrays;

public class AgeSorter {
    public static void countingSort(int[] ages) {
        int max = 18;
        int min = 10;
        int range = max - min + 1;
        int[] count = new int[range];
        for (int age : ages) {
            count[age - min]++;
        }
        for (int i = 1; i < range; i++) {
            count[i] += count[i - 1];
        }
        int[] output = new int[ages.length];
        for (int i = ages.length - 1; i >= 0; i--) {
            int age = ages[i];
            output[count[age - min] - 1] = age;
            count[age - min]--;
        }    
        System.arraycopy(output, 0, ages, 0, ages.length);
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of students: ");
        int n = scanner.nextInt();   
        int[] ages = new int[n];
        System.out.println("Enter age");
        for (int i = 0; i < n; i++) {
            ages[i] = scanner.nextInt();
        }
        countingSort(ages);
        System.out.println("Sorted student ages: " + Arrays.toString(ages));
    }
}