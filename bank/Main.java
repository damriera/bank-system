import javax.swing.JFrame;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame loadingScreen = new JFrame();
            loadingScreen.setSize(780, 560);
            loadingScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            loadingScreen.setUndecorated(true);

            // Create a progress bar
            JProgressBar progressBar = new JProgressBar(0, 100);
            progressBar.setStringPainted(true);

            loadingScreen.add(progressBar, BorderLayout.CENTER);

            loadingScreen.setVisible(true);

            // Start loading files
            new Thread(() -> {
                List<String[]> dataArray = new ArrayList<>(); // ArrayList to hold arrays of strings

                try (BufferedReader br = new BufferedReader(new FileReader("data/testFile.txt"))) {
                    long totalSize = 0; // Total size of the file
                    String line;
                    while ((line = br.readLine()) != null) {
                        // Split the line by colon (:)
                        String[] parts = line.split(" : ");
                        // Add the split parts to the array list
                        dataArray.add(parts);
                        // Update total size
                        totalSize += line.length();
                    }

                    // Process data here (replace with your actual processing logic)

                    // Simulate loading progress
                    int lastPercentage = 0;
                    for (int bytesRead = 0; bytesRead < totalSize; bytesRead += 1024) {
                        // Calculate percentage
                        int percentage = (int) ((bytesRead * 100) / totalSize);

                        // Update progress if changed significantly
                        if (percentage > lastPercentage + 5) {
                            progressBar.setValue(percentage);
                            lastPercentage = percentage;
                        }

                        // Sleep to simulate file loading time
                        try {
                            Thread.sleep(50); // Adjust sleep time as needed
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }

                progressBar.setValue(100);
                // Loading completed, you can close the loading screen or switch to another screen here
                loadingScreen.dispose(); // Close the loading screen
            }).start();
        });
    }
}
