
import java.io.*;
public class LargeFileReadingEfficiency {
    public static void main(String[] args) {
        String filePath = "largefile.txt"; 
        long fileReaderStartTime = System.nanoTime();
        readWithFileReader(filePath);
        long fileReaderEndTime = System.nanoTime();
        long fileReaderTime = fileReaderEndTime - fileReaderStartTime;
        long inputStreamReaderStartTime = System.nanoTime();
        readWithInputStreamReader(filePath);
        long inputStreamReaderEndTime = System.nanoTime();
        long inputStreamReaderTime = inputStreamReaderEndTime - inputStreamReaderStartTime;
        System.out.println("FileReader Time: " + fileReaderTime / 1000000.0 + " ms");
        System.out.println("InputStreamReader Time: " + inputStreamReaderTime / 1000000.0 + " ms");
    }
    public static void readWithFileReader(String filePath) {
        try (FileReader fr = new FileReader(filePath)) {
            int i;
            while ((i = fr.read()) != -1) {
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void readWithInputStreamReader(String filePath) {
        try (InputStreamReader isr = new InputStreamReader(new FileInputStream(filePath))) {
            int i;
            while ((i = isr.read()) != -1) {
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

