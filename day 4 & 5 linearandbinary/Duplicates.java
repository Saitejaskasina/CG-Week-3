import java.util.HashSet;
public class Duplicates {
    public static String remove(String input) {
        StringBuilder stringBuilder = new StringBuilder();
        HashSet<Character> seen = new HashSet<>();
        for (char c : input.toCharArray()) {
            if (!seen.contains(c)) {
                stringBuilder.append(c);
                seen.add(c);
            }
        }   
        return stringBuilder.toString();
    }
    public static void main(String[] args) {
        String input = "rickbrentonbhiiam";
        String result = remove(input);
        System.out.println("String without duplicates: " + result);
    }
}