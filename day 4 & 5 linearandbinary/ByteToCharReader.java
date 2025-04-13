import java.io.*;
public class ByteToCharReader {
    public static void readBinary(String path) throws IOException {
        FileInputStream fis = new FileInputStream(path);
        InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
        BufferedReader br = new BufferedReader(isr);
        String line;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }
        br.close();
    }
    public static void main(String[] args) {
        try {
            readBinary("your-file.txt"); 
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}