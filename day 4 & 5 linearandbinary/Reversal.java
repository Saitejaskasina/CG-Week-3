import java.util.Scanner;
public class Reversal {
    public static String reverse(String input) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(input);
        return stringBuilder.reverse().toString();
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a string: ");
        String input = scanner.nextLine();
        String reversed = reverse(input);
        System.out.println("Reversed string: " + reversed);
     }
}
