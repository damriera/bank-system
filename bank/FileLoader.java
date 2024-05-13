import java.io.*;
import java.util.*;

public class FileLoader {


    private long totalSize = 0;
    public void main(String[] args) {
        // List of files to load
        List<String> fileNames = Arrays.asList("testFile.txt");

        // Calculate total size
        this.totalSize = getTotalSize(fileNames);

        // Initialize progress variables
        long bytesRead = 0;
        int lastPercentage = 0;

        // Read files
        for (String fileName : fileNames) {
            try (FileInputStream fis = new FileInputStream(fileName);
                 BufferedInputStream bis = new BufferedInputStream(fis)) {

                int bufferSize = 4096;
                byte[] buffer = new byte[bufferSize];
                int bytesReadFromFile;

                while ((bytesReadFromFile = bis.read(buffer)) != -1) {
                    // Process buffer
                    // Here, you can write the buffer to another file or do other processing

                    bytesRead += bytesReadFromFile;

                    // Calculate percentage
                    int percentage = (int) ((bytesRead * 100) / totalSize);

                    // Update progress if changed significantly
                    if (percentage > lastPercentage + 5) {
                        System.out.println("Loading: " + percentage + "%");
                        lastPercentage = percentage;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Loading completed!");
    }

    private long getTotalSize(List<String> fileNames) {
        long totalSize = 0;
        for (String fileName : fileNames) {
            File file = new File(fileName);
            totalSize += file.length();
        }
        return totalSize;
    }

    public long getTot() {
        main(null);
        return this.totalSize;
    }
}
