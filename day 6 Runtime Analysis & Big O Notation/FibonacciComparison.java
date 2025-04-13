import java.util.HashMap;
public class FibonacciComparison {
    public static void main(String[] args) {
        int n = 30;
        long recursiveStartTime = System.nanoTime();
        int recursiveResult = fibonacciRecursiveMemo(n, new HashMap<>());
        long recursiveEndTime = System.nanoTime();
        long recursiveTime = recursiveEndTime - recursiveStartTime;     
        long iterativeStartTime = System.nanoTime();
        int iterativeResult = fibonacciIterative(n);
        long iterativeEndTime = System.nanoTime();
        long iterativeTime = iterativeEndTime - iterativeStartTime;
        System.out.println("Recursive Result (Memoization): " + recursiveResult);
        System.out.println("Recursive Time (Memoization): " + recursiveTime / 1000000.0 + " ms");
        System.out.println("Iterative Result: " + iterativeResult);
        System.out.println("Iterative Time: " + iterativeTime / 1000000.0 + " ms");
    }
    public static int fibonacciRecursiveMemo(int n, HashMap<Integer, Integer> memo) {
        if (n <= 1) return n;
        if (memo.containsKey(n)) return memo.get(n);
        int result = fibonacciRecursiveMemo(n - 1, memo) + fibonacciRecursiveMemo(n - 2, memo);
        memo.put(n, result);
        return result;
    }
    public static int fibonacciIterative(int n) {
        int a = 0, b = 1, sum;
        if (n == 0) return a;
        if (n == 1) return b;
        for (int i = 2; i <= n; i++) {
            sum = a + b;
            a = b;
            b = sum;
        }
        return b;
    }
}
