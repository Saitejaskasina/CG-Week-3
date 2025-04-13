public class CompareBufferBuilder {
    public static void main(String[] args) {
        int n = 1000000;
        String word = "hello";

        long start1 = System.nanoTime();
        StringBuffer sbuf = new StringBuffer();
        for (int i = 0; i < n; i++) sbuf.append(word);
        long end1 = System.nanoTime();

        long start2 = System.nanoTime();
        StringBuilder sbld = new StringBuilder();
        for (int i = 0; i < n; i++) sbld.append(word);
        long end2 = System.nanoTime();

        System.out.println("StringBuffer Time: " + (end1 - start1) + " ns");
        System.out.println("StringBuilder Time: " + (end2 - start2) + " ns");
    }
}