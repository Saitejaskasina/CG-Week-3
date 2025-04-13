import java.io.*;
public class ConsoleToFile {
    public static void main(String[] args) throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        FileWriter fw = new FileWriter("output.txt");
        String input;
        while (!(input = br.readLine()).equals("exit")) {
            fw.write(input + "\n");
        }
        fw.close();
    }
}