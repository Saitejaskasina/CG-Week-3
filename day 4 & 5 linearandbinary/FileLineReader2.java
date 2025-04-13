import java.io.*;
public class FileLineReader2 {
    public static void readFile(String path) throws IOException {
        FileReader fr = new FileReader(path);
        BufferedReader br = new BufferedReader(fr);
        String line;

        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }

        br.close();
    }
    public static void main(String[] args) {
        try {
            readFile("your-file.txt"); 
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}