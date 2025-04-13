import java.io.*;
public class Challenge {
    public static void main(String[] args) throws IOException {
        String word = "hello";
        int n = 1000000;
        long startSB = System.nanoTime();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) sb.append(word);
        long endSB = System.nanoTime();
        long startBF = System.nanoTime();
        StringBuffer sf = new StringBuffer();
        for (int i = 0; i < n; i++) sf.append(word);
        long endBF = System.nanoTime();
        System.out.println("StringBuilder Time: " + (endSB - startSB));
        System.out.println("StringBuffer Time: " + (endBF - startBF));
        FileReader fr = new FileReader("largefile.txt");
        BufferedReader br = new BufferedReader(fr);
        int wordCount = 0;
        String line;
        while ((line = br.readLine()) != null) {
            wordCount += line.split("\\s+").length;
        }
        br.close();
        System.out.println("Word count: " + wordCount);
    }
}